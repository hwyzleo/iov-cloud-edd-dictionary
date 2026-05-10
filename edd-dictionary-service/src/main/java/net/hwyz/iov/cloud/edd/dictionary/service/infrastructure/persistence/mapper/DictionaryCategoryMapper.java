package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper;

import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryCategoryPo;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryColumnPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典分类 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2024-10-25
 */
@Mapper
public interface DictionaryCategoryMapper extends BaseDao<DictionaryCategoryPo, Long> {

    DictionaryCategoryPo selectPoByCode(String code);

    int createTable(String tableName, String tableDesc, List<DictionaryColumnPo> columns, List<String> uniqueColumns);

    List<Map<String, Object>> selectTable(String tableName, Map<String, String> columns, Map<String, Object> whereConditions, String conditionSymbol);

    List<Map<String, Object>> selectTableList(String tableName, Map<String, String> columns);

    List<Map<String, Object>> selectTableListWithWhere(String tableName, Map<String, String> columns, String whereClause);

    Map<String, Object> selectTableById(String tableName, Map<String, String> columns, Long id);

    int insertTableData(String tableName, Map<String, Object> data);

    int updateTableData(String tableName, Long id, Map<String, Object> data);

    int deleteTableData(String tableName, Long id);

}
