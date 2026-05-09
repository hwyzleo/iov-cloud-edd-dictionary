package net.hwyz.iov.cloud.edd.dictionary.service.application.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.api.vo.request.DictionaryRequest;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.cache.CacheService;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.converter.DictionaryPoConverter;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper.DictionaryCategoryMapper;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper.DictionaryMapper;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryPo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictionaryAppService {

    private final CacheService cacheService;
    private final DictionaryMapper dictionaryMapper;
    private final DictionaryCategoryMapper dictionaryCategoryMapper;

    /**
     * 创建数据字典
     *
     * @param request 数据字典请求
     */
    public void createDictionary(DictionaryRequest request) {
        DictionaryPo dictionaryPo = DictionaryPoConverter.INSTANCE.fromDto(request);
        dictionaryPo.setEnable(true);
        dictionaryPo.setSort(99);
        dictionaryMapper.insertPo(dictionaryPo);
    }

    /**
     * 获取数据字典
     *
     * @param code 数据字典编码
     * @return 数据字典
     */
    public List<Map<String, Object>> getDictionary(String code) {
        return cacheService.getDictionary(code).orElseGet(() -> {
            DictionaryPo dictionaryPo = dictionaryMapper.selectPoByCode(code);
            if (dictionaryPo != null) {
                Map<String, String> columns = new HashMap<>();
                JSONUtil.parseArray(dictionaryPo.getSelectColumn()).forEach(item -> {
                    String targetColumn = ((JSONObject) item).getStr("targetColumn");
                    String aliasName = ((JSONObject) item).getStr("aliasName");
                    if (StrUtil.isBlank(aliasName)) {
                        aliasName = targetColumn;
                    }
                    columns.put(targetColumn, aliasName);
                });
                Map<String, Object> whereConditions = new HashMap<>();
                String conditionSymbol = "AND";
                List<Map<String, Object>> records = dictionaryCategoryMapper.selectTable(dictionaryPo.getCategoryCode(),
                        columns, whereConditions, conditionSymbol);
                cacheService.setDictionary(code, records);
                return records;
            }
            return null;
        });
    }

}
