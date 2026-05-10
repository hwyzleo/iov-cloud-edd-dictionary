package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.request;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.bean.BaseRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DictionaryCategoryRequest extends BaseRequest {

    private Long id;

    private Long pid;

    private String name;

    private String code;

    private String type;

    private Boolean enable;

    private Integer sort;

}