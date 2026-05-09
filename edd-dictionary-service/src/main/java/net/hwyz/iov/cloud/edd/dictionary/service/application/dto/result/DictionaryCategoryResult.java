package net.hwyz.iov.cloud.edd.dictionary.service.application.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryCategoryResult {

    private Long id;
    private String name;
    private String code;
    private String type;
}