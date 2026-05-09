package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.mapper;

import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryColumnPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionaryColumnMapper extends BaseDao<DictionaryColumnPo, Long> {

    List<DictionaryColumnPo> selectListByCategoryId(Long categoryId);

}
