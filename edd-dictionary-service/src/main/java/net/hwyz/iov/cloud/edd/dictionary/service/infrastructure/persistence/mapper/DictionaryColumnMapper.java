package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper;

import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryColumnPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据字典结构 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2024-10-26
 */
@Mapper
public interface DictionaryColumnMapper extends BaseDao<DictionaryColumnPo, Long> {

}
