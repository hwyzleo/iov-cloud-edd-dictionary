package net.hwyz.iov.cloud.edd.dictionary.service.application.assembler;

import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryColumnCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictionaryColumnAssembler {

    DictionaryColumnAssembler INSTANCE = Mappers.getMapper(DictionaryColumnAssembler.class);

    DictionaryColumn toDomain(DictionaryColumnCmd cmd);

}