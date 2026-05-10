package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.assembler;

import net.hwyz.iov.cloud.edd.dictionary.api.vo.response.DictionaryResponse;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response.DictionaryItemWebResponse;
import net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response.DictionaryWebResponse;
import net.hwyz.iov.cloud.edd.dictionary.service.application.dto.result.DictionaryResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface DictionaryWebAssembler {

    DictionaryWebAssembler INSTANCE = Mappers.getMapper(DictionaryWebAssembler.class);

    default DictionaryWebResponse toWebResponse(DictionaryResult result) {
        if (result == null) {
            return null;
        }
        List<DictionaryItemWebResponse> items = result.getItems() == null ? null :
                result.getItems().stream()
                        .filter(item -> item != null)
                        .map(item -> DictionaryItemWebResponse.builder()
                                .fields(item)
                                .build())
                        .collect(Collectors.toList());
        return DictionaryWebResponse.builder()
                .code(result.getCode())
                .name(result.getName())
                .items(items)
                .build();
    }

    default DictionaryResponse toApiResponse(DictionaryResult result) {
        if (result == null) {
            return null;
        }
        return DictionaryResponse.builder()
                .code(result.getCode())
                .name(result.getName())
                .items(result.getItems())
                .build();
    }
}