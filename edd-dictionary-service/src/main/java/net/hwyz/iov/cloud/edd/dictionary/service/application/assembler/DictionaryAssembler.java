package net.hwyz.iov.cloud.edd.dictionary.service.application.assembler;

import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryCategoryCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryColumnCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.Dictionary;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictionaryAssembler {

    DictionaryAssembler INSTANCE = Mappers.getMapper(DictionaryAssembler.class);

    Dictionary toDomain(DictionaryCmd cmd);

    DictionaryCategory toDomain(DictionaryCategoryCmd cmd);

    DictionaryColumn toDomain(DictionaryColumnCmd cmd);

    List<DictionaryColumn> toDomainList(List<DictionaryColumnCmd> cmdList);
}