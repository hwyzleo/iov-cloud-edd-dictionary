package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.request;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.bean.BaseRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DictionaryRequest extends BaseRequest {

    private Long id;

    private String name;

    private String code;

    private String categoryCode;

    private String selectColumn;

    private String whereCondition;

    private Boolean enable;

    private Integer sort;

}