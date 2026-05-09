package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryItemWebResponse {

    private Map<String, Object> fields;

    @JsonAnyGetter
    public Map<String, Object> getFields() {
        return fields;
    }
}