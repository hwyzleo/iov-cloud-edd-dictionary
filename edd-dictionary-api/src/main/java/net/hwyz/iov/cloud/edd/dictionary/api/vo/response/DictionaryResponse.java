package net.hwyz.iov.cloud.edd.dictionary.api.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryResponse {

    private String code;
    private String name;
    private List<DictionaryItemResponse> items;
}