package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryColumnResponse {

    private Long id;

    private Long categoryId;

    private String name;

    private String code;

    private String type;

    private Integer len;

    private Boolean nullable;

    private Boolean uniq;

    private Integer valueType;

    private String valueRange;

    private Integer sort;

    private Date createTime;

}