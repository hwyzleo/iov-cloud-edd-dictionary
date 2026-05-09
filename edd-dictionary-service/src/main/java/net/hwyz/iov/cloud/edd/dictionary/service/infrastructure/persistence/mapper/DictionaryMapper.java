package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper;

import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据字典 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2024-10-25
 */
@Mapper
public interface DictionaryMapper extends BaseDao<DictionaryPo, Long> {

    /**
     * 根据code查询数据字典
     *
     * @param code 数据字典代码
     * @return 数据字典PO
     */
    DictionaryPo selectPoByCode(String code);

}
