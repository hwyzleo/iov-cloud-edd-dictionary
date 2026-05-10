package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryResponse {

    private Long id;

    private String name;

    private String code;

    private String categoryCode;

    private String selectColumn;

    private String whereCondition;

    private Boolean enable;

    private Integer sort;

    private Date createTime;

}