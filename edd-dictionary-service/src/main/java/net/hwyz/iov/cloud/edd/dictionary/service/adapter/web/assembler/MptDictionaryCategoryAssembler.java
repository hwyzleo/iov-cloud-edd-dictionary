package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.assembler;

import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.request.DictionaryCategoryRequest;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response.DictionaryCategoryResponse;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd.DictionaryCategoryCmd;
import net.hwyz.iov.cloud.edd.dictionary.service.domain.model.DictionaryCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MptDictionaryCategoryAssembler {

    MptDictionaryCategoryAssembler INSTANCE = Mappers.getMapper(MptDictionaryCategoryAssembler.class);

    DictionaryCategoryResponse fromDomain(DictionaryCategory category);

    DictionaryCategoryCmd toCmd(DictionaryCategoryRequest request);

    List<DictionaryCategoryResponse> fromDomainList(List<DictionaryCategory> categories);

}