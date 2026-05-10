package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.assembler;

import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.request.DictionaryRequest;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response.DictionaryResponse;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.Dictionary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MptDictionaryAssembler {

    MptDictionaryAssembler INSTANCE = Mappers.getMapper(MptDictionaryAssembler.class);

    DictionaryResponse fromDomain(Dictionary dictionary);

    DictionaryCmd toCmd(DictionaryRequest request);

    List<DictionaryResponse> fromDomainList(List<Dictionary> dictionaries);

}