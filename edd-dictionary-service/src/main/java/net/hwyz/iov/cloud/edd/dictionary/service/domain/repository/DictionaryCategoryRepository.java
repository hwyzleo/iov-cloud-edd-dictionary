package net.hwyz.iov.cloud.edd.dictionary.service.domain.repository;

import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;

import java.util.List;
import java.util.Optional;

public interface DictionaryCategoryRepository {

    void save(DictionaryCategory category);

    void saveWithColumns(DictionaryCategory category, List<DictionaryColumn> columns, List<String> uniqueColumns);

    Optional<DictionaryCategory> findByCode(String code);

    void createTable(String tableName, String tableDesc, List<DictionaryColumn> columns, List<String> uniqueColumns);
}