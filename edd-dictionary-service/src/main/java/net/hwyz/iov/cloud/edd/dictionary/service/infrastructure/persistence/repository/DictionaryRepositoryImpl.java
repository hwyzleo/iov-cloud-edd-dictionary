package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.Dictionary;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.repository.DictionaryRepository;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.converter.DictionaryConverter;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper.DictionaryCategoryMapper;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper.DictionaryMapper;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryPo;
import net.hwyz.iov.cloud.framework.web.util.PageUtil;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DictionaryRepositoryImpl implements DictionaryRepository {

    private final DictionaryMapper dictionaryMapper;
    private final DictionaryCategoryMapper dictionaryCategoryMapper;

    @Override
    public void save(Dictionary dictionary) {
        DictionaryPo po = DictionaryConverter.INSTANCE.toPo(dictionary);
        dictionaryMapper.insertPo(po);
        dictionary.setId(po.getId());
    }

    @Override
    public Optional<Dictionary> findByCode(String code) {
        DictionaryPo po = dictionaryMapper.selectPoByCode(code);
        if (po == null) {
            return Optional.empty();
        }
        return Optional.of(DictionaryConverter.INSTANCE.toDomain(po));
    }

    @Override
    public List<Map<String, Object>> queryTableData(String tableName, Map<String, String> columns, Map<String, Object> whereConditions, String conditionSymbol) {
        return dictionaryCategoryMapper.selectTable(tableName, columns, whereConditions, conditionSymbol);
    }

    @Override
    public Dictionary findById(Long id) {
        return DictionaryConverter.INSTANCE.toDomain(dictionaryMapper.selectPoById(id));
    }

    @Override
    public List<Dictionary> findAll() {
        List<DictionaryPo> poList = dictionaryMapper.selectPoByExample(null);
        return PageUtil.convert(poList, DictionaryConverter.INSTANCE::toDomain);
    }

    @Override
    public List<Dictionary> findByMap(Map<String, Object> map) {
        List<DictionaryPo> poList = dictionaryMapper.selectPoByMap(map);
        return PageUtil.convert(poList, DictionaryConverter.INSTANCE::toDomain);
    }

    @Override
    public int update(Dictionary dictionary) {
        return dictionaryMapper.updatePo(DictionaryConverter.INSTANCE.toPo(dictionary));
    }

    @Override
    public int deleteById(Long id) {
        return dictionaryMapper.physicalDeletePo(id);
    }

    @Override
    public int batchDelete(Long[] ids) {
        return dictionaryMapper.batchPhysicalDeletePo(ids);
    }

}