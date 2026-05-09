package net.hwyz.iov.cloud.edd.dictionary.service.application.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.result.DictionaryResult;
import net.hwyz.iov.cloud.edd.dictionary.service.application.assembler.DictionaryAssembler;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.Dictionary;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryRepository;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.cache.CacheService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictionaryAppService {

    private final CacheService cacheService;
    private final DictionaryRepository dictionaryRepository;

    public void createDictionary(DictionaryCmd cmd) {
        Dictionary dictionary = DictionaryAssembler.INSTANCE.toDomain(cmd);
        dictionary.enable();
        dictionary.updateSort(99);
        dictionaryRepository.save(dictionary);
    }

    public DictionaryResult getDictionary(String code) {
        List<Map<String, Object>> cachedItems = cacheService.getDictionary(code).orElse(null);
        if (cachedItems != null) {
            return DictionaryResult.builder()
                    .code(code)
                    .items(cachedItems)
                    .build();
        }

        Dictionary dictionary = dictionaryRepository.findByCode(code).orElse(null);
        if (dictionary == null) {
            return null;
        }

        Map<String, String> columns = new HashMap<>();
        JSONUtil.parseArray(dictionary.getSelectColumn()).forEach(item -> {
            String targetColumn = ((JSONObject) item).getStr("targetColumn");
            String aliasName = ((JSONObject) item).getStr("aliasName");
            if (StrUtil.isBlank(aliasName)) {
                aliasName = targetColumn;
            }
            columns.put(targetColumn, aliasName);
        });

        Map<String, Object> whereConditions = new HashMap<>();
        String conditionSymbol = "AND";
        List<Map<String, Object>> items = dictionaryRepository.queryTableData(
                dictionary.getCategoryCode(),
                columns,
                whereConditions,
                conditionSymbol
        );

        cacheService.setDictionary(code, items);

        return DictionaryResult.builder()
                .code(code)
                .name(dictionary.getName())
                .items(items)
                .build();
    }
}