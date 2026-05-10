package net.hwyz.iov.cloud.edd.dictionary.service.domain.repository;

import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;

import java.util.List;

public interface DictionaryColumnRepository {

    void save(DictionaryColumn column);

    void saveAll(List<DictionaryColumn> columns);

    DictionaryColumn findById(Long id);

    List<DictionaryColumn> findByCategoryId(Long categoryId);

    int update(DictionaryColumn column);

    int deleteById(Long id);

    int batchDelete(Long[] ids);

}