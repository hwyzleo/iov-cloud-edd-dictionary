package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.assembler;

import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.request.DictionaryColumnRequest;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response.DictionaryColumnResponse;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryColumnCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryColumn;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MptDictionaryColumnAssembler {

    MptDictionaryColumnAssembler INSTANCE = Mappers.getMapper(MptDictionaryColumnAssembler.class);

    DictionaryColumnResponse fromDomain(DictionaryColumn column);

    DictionaryColumnCmd toCmd(DictionaryColumnRequest request);

    List<DictionaryColumnResponse> fromDomainList(List<DictionaryColumn> columns);

}