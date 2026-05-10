package net.hwyz.iov.cloud.edd.dictionary.service.application.service;

import cn.hutool.core.util.ObjUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.edd.dictionary.service.application.assembler.DictionaryColumnAssembler;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryColumnCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryCategoryRepository;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryColumnRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictionaryColumnAppService {

    private final DictionaryColumnRepository dictionaryColumnRepository;
    private final DictionaryCategoryRepository dictionaryCategoryRepository;

    public List<DictionaryColumn> findByCategoryId(Long categoryId) {
        return dictionaryColumnRepository.findByCategoryId(categoryId);
    }

    public DictionaryColumn getById(Long id) {
        return dictionaryColumnRepository.findById(id);
    }

    public void create(DictionaryColumnCmd cmd, String userId) {
        DictionaryColumn column = DictionaryColumnAssembler.INSTANCE.toDomain(cmd);
        column.setSort(99);
        dictionaryColumnRepository.save(column);
    }

    public int update(DictionaryColumnCmd cmd, String userId) {
        DictionaryColumn column = DictionaryColumnAssembler.INSTANCE.toDomain(cmd);
        return dictionaryColumnRepository.update(column);
    }

    public int deleteByIds(Long[] ids) {
        return dictionaryColumnRepository.batchDelete(ids);
    }

    public void generateTable(Long categoryId) {
        DictionaryCategory category = dictionaryCategoryRepository.findById(categoryId);
        if (category == null) {
            throw new RuntimeException("字典分类不存在");
        }

        List<DictionaryColumn> columns = dictionaryColumnRepository.findByCategoryId(categoryId);
        if (columns.isEmpty()) {
            throw new RuntimeException("请先添加字段");
        }

        List<String> uniqueColumns = columns.stream()
                .filter(DictionaryColumn::isUnique)
                .map(DictionaryColumn::getCode)
                .collect(Collectors.toList());

        log.info("为字典分类[{}]创建数据表[{}]，包含{}个字段", category.getName(), category.getCode(), columns.size());
        dictionaryCategoryRepository.createTable(category.getCode(), category.getName(), columns, uniqueColumns);
    }

}