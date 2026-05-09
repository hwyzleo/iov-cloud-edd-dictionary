package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.converter;

import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
import net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po.DictionaryCategoryPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictionaryCategoryConverter {

    DictionaryCategoryConverter INSTANCE = Mappers.getMapper(DictionaryCategoryConverter.class);

    DictionaryCategory toDomain(DictionaryCategoryPo po);

    DictionaryCategoryPo toPo(DictionaryCategory domain);
}