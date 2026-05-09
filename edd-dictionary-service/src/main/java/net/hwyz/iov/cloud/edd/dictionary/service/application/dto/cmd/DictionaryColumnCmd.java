package net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryColumnCmd {

    private String name;
    private String code;
    private String type;
    private Integer len;
    private Boolean nullable;
    private Boolean uniq;
    private Integer valueType;
    private String valueRange;
    private Integer sort;
}