package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.converter;

import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.Dictionary;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictionaryConverter {

    DictionaryConverter INSTANCE = Mappers.getMapper(DictionaryConverter.class);

    Dictionary toDomain(DictionaryPo po);

    DictionaryPo toPo(Dictionary domain);
}