package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryColumnRepository;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.converter.DictionaryColumnConverter;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper.DictionaryColumnMapper;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryColumnPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DictionaryColumnRepositoryImpl implements DictionaryColumnRepository {

    private final DictionaryColumnMapper dictionaryColumnMapper;

    @Override
    public void saveAll(List<DictionaryColumn> columns) {
        List<DictionaryColumnPo> poList = DictionaryColumnConverter.INSTANCE.toPoList(columns);
        dictionaryColumnMapper.batchInsertPo(poList);
    }

    @Override
    public List<DictionaryColumn> findByCategoryId(Long categoryId) {
        List<DictionaryColumnPo> poList = dictionaryColumnMapper.selectListByCategoryId(categoryId);
        return DictionaryColumnConverter.INSTANCE.toDomainList(poList);
    }
}