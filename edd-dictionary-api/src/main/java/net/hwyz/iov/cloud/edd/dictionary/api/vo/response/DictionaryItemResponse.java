package net.hwyz.iov.cloud.edd.dictionary.api.vo.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryItemResponse {

    private Map<String, Object> fields;

    @JsonAnyGetter
    public Map<String, Object> getFields() {
        return fields;
    }

    @JsonAnySetter
    public void setField(String key, Object value) {
        if (fields == null) {
            fields = new HashMap<>();
        }
        fields.put(key, value);
    }
}