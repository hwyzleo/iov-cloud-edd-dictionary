package net.hwyz.iov.cloud.edd.dictionary.service.application.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryItemResult {

    private Map<String, Object> data;
}