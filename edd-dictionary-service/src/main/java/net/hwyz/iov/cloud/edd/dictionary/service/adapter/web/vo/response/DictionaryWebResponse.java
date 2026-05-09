package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryWebResponse {

    private String code;
    private String name;
    private List<DictionaryItemWebResponse> items;
}