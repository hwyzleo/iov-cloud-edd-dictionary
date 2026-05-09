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

    /**
     * 创建表
     *
     * @param tableName     表名
     * @param tableDesc     表描述
     * @param columns       字段列表
     * @param uniqueColumns 唯一索引列表
     * @return 创建结果
     */
    int createTable(String tableName, String tableDesc, List<DictionaryColumnPo> columns, List<String> uniqueColumns);

    /**
     * 查询表
     *
     * @param tableName       表名
     * @param columns         字段列表
     * @param whereConditions 条件列表
     * @param conditionSymbol 条件符号
     * @return 查询结果
     */
    List<Map<String, Object>> selectTable(String tableName, Map<String, String> columns, Map<String, Object> whereConditions, String conditionSymbol);

}
