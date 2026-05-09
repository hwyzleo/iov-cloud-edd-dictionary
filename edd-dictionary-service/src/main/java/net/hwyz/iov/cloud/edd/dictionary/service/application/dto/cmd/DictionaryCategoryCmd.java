package net.hwyz.iov.cloud.edd.dictionary.service.application.dto.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryCategoryCmd {

    private Long pid;
    private String name;
    private String code;
    private String type;
    private List<DictionaryColumnCmd> columns;
}