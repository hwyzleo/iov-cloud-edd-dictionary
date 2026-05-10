package net.hwyz.iov.cloud.edd.dictionary.service.domain.repository;

import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DictionaryCategoryRepository {

    void save(DictionaryCategory category);

    void saveWithColumns(DictionaryCategory category, List<DictionaryColumn> columns, List<String> uniqueColumns);

    Optional<DictionaryCategory> findByCode(String code);

    void createTable(String tableName, String tableDesc, List<DictionaryColumn> columns, List<String> uniqueColumns);

    List<Map<String, Object>> selectTableList(String tableName, Map<String, String> columns);

    List<Map<String, Object>> selectTableListWithWhere(String tableName, Map<String, String> columns, String whereClause);

    Map<String, Object> selectTableById(String tableName, Map<String, String> columns, Long id);

    void insertTableData(String tableName, Map<String, Object> data);

    void updateTableData(String tableName, Long id, Map<String, Object> data);

    void deleteTableData(String tableName, Long id);

    DictionaryCategory findById(Long id);

    List<DictionaryCategory> findAll();

    List<DictionaryCategory> findByMap(Map<String, Object> map);

    int update(DictionaryCategory category);

    int deleteById(Long id);

    int batchDelete(Long[] ids);

}