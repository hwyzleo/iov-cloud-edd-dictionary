package net.hwyz.iov.cloud.edd.dictionary.service.application.dto.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DictionaryColumnQuery {

    private Long categoryId;

    private String name;

    private String code;

}