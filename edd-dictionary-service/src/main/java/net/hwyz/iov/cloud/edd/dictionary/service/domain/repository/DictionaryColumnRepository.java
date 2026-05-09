package net.hwyz.iov.cloud.edd.dictionary.service.domain.repository;

import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;

import java.util.List;

public interface DictionaryColumnRepository {

    void saveAll(List<DictionaryColumn> columns);

    List<DictionaryColumn> findByCategoryId(Long categoryId);
}