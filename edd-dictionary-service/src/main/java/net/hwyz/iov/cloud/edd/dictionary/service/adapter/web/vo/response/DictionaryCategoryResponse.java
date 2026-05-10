package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.response;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryCategoryResponse {

    private Long id;

    private Long pid;

    private String name;

    private String code;

    private String type;

    private Boolean enable;

    private Integer sort;

    private Date createTime;

}