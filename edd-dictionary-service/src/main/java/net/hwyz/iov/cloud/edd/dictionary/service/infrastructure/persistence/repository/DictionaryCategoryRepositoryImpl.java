package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryCategoryRepository;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.converter.DictionaryCategoryConverter;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.converter.DictionaryColumnConverter;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper.DictionaryCategoryMapper;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper.DictionaryColumnMapper;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryCategoryPo;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryColumnPo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DictionaryCategoryRepositoryImpl implements DictionaryCategoryRepository {

    private final DictionaryCategoryMapper dictionaryCategoryMapper;
    private final DictionaryColumnMapper dictionaryColumnMapper;

    @Override
    public void save(DictionaryCategory category) {
        DictionaryCategoryPo po = DictionaryCategoryConverter.INSTANCE.toPo(category);
        dictionaryCategoryMapper.insertPo(po);
        category.setId(po.getId());
    }

    @Override
    public void saveWithColumns(DictionaryCategory category, List<DictionaryColumn> columns, List<String> uniqueColumns) {
        DictionaryCategoryPo categoryPo = DictionaryCategoryConverter.INSTANCE.toPo(category);
        dictionaryCategoryMapper.insertPo(categoryPo);
        category.setId(categoryPo.getId());

        List<DictionaryColumnPo> columnPoList = columns.stream()
                .map(column -> {
                    DictionaryColumnPo po = DictionaryColumnConverter.INSTANCE.toPo(column);
                    po.setCategoryId(categoryPo.getId());
                    return po;
                })
                .collect(Collectors.toList());
        dictionaryColumnMapper.batchInsertPo(columnPoList);

        columns.forEach(column -> {
            for (DictionaryColumnPo po : columnPoList) {
                if (po.getCode().equals(column.getCode())) {
                    column.setId(po.getId());
                    break;
                }
            }
        });
    }

    @Override
    public Optional<DictionaryCategory> findByCode(String code) {
        DictionaryCategoryPo po = dictionaryCategoryMapper.selectPoByCode(code);
        if (po == null) {
            return Optional.empty();
        }
        return Optional.of(DictionaryCategoryConverter.INSTANCE.toDomain(po));
    }

    @Override
    public void createTable(String tableName, String tableDesc, List<DictionaryColumn> columns, List<String> uniqueColumns) {
        List<DictionaryColumnPo> columnPoList = DictionaryColumnConverter.INSTANCE.toPoList(columns);
        dictionaryCategoryMapper.createTable(tableName, tableDesc, columnPoList, uniqueColumns);
    }
}