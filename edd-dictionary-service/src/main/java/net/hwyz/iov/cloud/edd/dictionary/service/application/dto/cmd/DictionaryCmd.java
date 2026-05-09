package net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryCmd {

    private String name;
    private String code;
    private String categoryCode;
    private String selectColumn;
    private String whereCondition;
}