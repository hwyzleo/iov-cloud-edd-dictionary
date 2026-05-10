package net.hwyz.iov.cloud.edd.dictionary.service.application.service;

import cn.hutool.core.util.ObjUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryCategoryCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.application.assembler.DictionaryAssembler;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.query.DictionaryCategoryQuery;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryCategoryRepository;
import net.hwyz.iov.cloud.framework.common.util.ParamHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictionaryCategoryAppService {

    private final DictionaryCategoryRepository dictionaryCategoryRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createCategory(DictionaryCategoryCmd cmd) {
        DictionaryCategory category = DictionaryAssembler.INSTANCE.toDomain(cmd);
        category.enable();
        category.updateSort(99);

        List<DictionaryColumn> columns = DictionaryAssembler.INSTANCE.toDomainList(cmd.getColumns());
        List<String> uniqueColumns = new ArrayList<>();
        columns.stream()
                .filter(DictionaryColumn::isUnique)
                .forEach(column -> uniqueColumns.add(column.getCode()));

        dictionaryCategoryRepository.saveWithColumns(category, columns, uniqueColumns);
        dictionaryCategoryRepository.createTable(cmd.getCode(), cmd.getName(), columns, uniqueColumns);
    }

    public List<DictionaryCategory> search(DictionaryCategoryQuery query) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", ParamHelper.fuzzyQueryParam(query.getName()));
        map.put("code", query.getCode());
        map.put("type", query.getType());
        map.put("beginTime", query.getBeginTime());
        map.put("endTime", query.getEndTime());
        return dictionaryCategoryRepository.findByMap(map);
    }

    public DictionaryCategory getById(Long id) {
        return dictionaryCategoryRepository.findById(id);
    }

    public DictionaryCategory getByCode(String code) {
        return dictionaryCategoryRepository.findByCode(code).orElse(null);
    }

    public Boolean checkCodeUnique(Long id, String code) {
        if (ObjUtil.isNull(id)) {
            id = -1L;
        }
        DictionaryCategory category = dictionaryCategoryRepository.findByCode(code).orElse(null);
        return !ObjUtil.isNotNull(category) || category.getId().longValue() == id.longValue();
    }

    public void create(DictionaryCategoryCmd cmd, String userId) {
        DictionaryCategory category = DictionaryAssembler.INSTANCE.toDomain(cmd);
        category.enable();
        category.updateSort(99);
        dictionaryCategoryRepository.save(category);
    }

    public int update(DictionaryCategoryCmd cmd, String userId) {
        DictionaryCategory category = DictionaryAssembler.INSTANCE.toDomain(cmd);
        return dictionaryCategoryRepository.update(category);
    }

    public int deleteByIds(Long[] ids) {
        return dictionaryCategoryRepository.batchDelete(ids);
    }

}