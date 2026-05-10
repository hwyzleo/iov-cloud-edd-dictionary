package net.hwyz.iov.cloud.edd.dictionary.service.domain.repository;

import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.Dictionary;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DictionaryRepository {

    void save(Dictionary dictionary);

    Optional<Dictionary> findByCode(String code);

    List<Map<String, Object>> queryTableData(String tableName, Map<String, String> columns, Map<String, Object> whereConditions, String conditionSymbol);

    Dictionary findById(Long id);

    List<Dictionary> findAll();

    List<Dictionary> findByMap(Map<String, Object> map);

    int update(Dictionary dictionary);

    int deleteById(Long id);

    int batchDelete(Long[] ids);

}