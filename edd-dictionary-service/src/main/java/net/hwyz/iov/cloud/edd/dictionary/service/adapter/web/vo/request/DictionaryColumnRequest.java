package net.hwyz.iov.cloud.edd.dictionary.service.adapter.web.vo.request;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.bean.BaseRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DictionaryColumnRequest extends BaseRequest {

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

}