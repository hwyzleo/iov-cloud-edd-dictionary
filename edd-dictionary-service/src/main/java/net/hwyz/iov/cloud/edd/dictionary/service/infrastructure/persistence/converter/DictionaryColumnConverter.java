package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.converter;

import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryColumnPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictionaryColumnConverter {

    DictionaryColumnConverter INSTANCE = Mappers.getMapper(DictionaryColumnConverter.class);

    DictionaryColumn toDomain(DictionaryColumnPo po);

    DictionaryColumnPo toPo(DictionaryColumn domain);

    java.util.List<DictionaryColumn> toDomainList(java.util.List<DictionaryColumnPo> poList);

    java.util.List<DictionaryColumnPo> toPoList(java.util.List<DictionaryColumn> domainList);
}