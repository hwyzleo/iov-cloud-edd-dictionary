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
import net.hwyz.iov.cloud.framework.web.util.PageUtil;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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

    @Override
    public List<Map<String, Object>> selectTableList(String tableName, Map<String, String> columns) {
        return dictionaryCategoryMapper.selectTableList(tableName, columns);
    }

    @Override
    public List<Map<String, Object>> selectTableListWithWhere(String tableName, Map<String, String> columns, String whereClause) {
        return dictionaryCategoryMapper.selectTableListWithWhere(tableName, columns, whereClause);
    }

    @Override
    public Map<String, Object> selectTableById(String tableName, Map<String, String> columns, Long id) {
        return dictionaryCategoryMapper.selectTableById(tableName, columns, id);
    }

    @Override
    public void insertTableData(String tableName, Map<String, Object> data) {
        dictionaryCategoryMapper.insertTableData(tableName, data);
    }

    @Override
    public void updateTableData(String tableName, Long id, Map<String, Object> data) {
        dictionaryCategoryMapper.updateTableData(tableName, id, data);
    }

    @Override
    public void deleteTableData(String tableName, Long id) {
        dictionaryCategoryMapper.deleteTableData(tableName, id);
    }

    @Override
    public DictionaryCategory findById(Long id) {
        return DictionaryCategoryConverter.INSTANCE.toDomain(dictionaryCategoryMapper.selectPoById(id));
    }

    @Override
    public List<DictionaryCategory> findAll() {
        List<DictionaryCategoryPo> poList = dictionaryCategoryMapper.selectPoByExample(null);
        return PageUtil.convert(poList, DictionaryCategoryConverter.INSTANCE::toDomain);
    }

    @Override
    public List<DictionaryCategory> findByMap(Map<String, Object> map) {
        List<DictionaryCategoryPo> poList = dictionaryCategoryMapper.selectPoByMap(map);
        return PageUtil.convert(poList, DictionaryCategoryConverter.INSTANCE::toDomain);
    }

    @Override
    public int update(DictionaryCategory category) {
        return dictionaryCategoryMapper.updatePo(DictionaryCategoryConverter.INSTANCE.toPo(category));
    }

    @Override
    public int deleteById(Long id) {
        return dictionaryCategoryMapper.physicalDeletePo(id);
    }

    @Override
    public int batchDelete(Long[] ids) {
        return dictionaryCategoryMapper.batchPhysicalDeletePo(ids);
    }

}