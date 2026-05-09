package net.hwyz.iov.cloud.edd.dictionary.api.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 数据字典分类
 *
 * @author hwyz_leo
 * @since 2024-10-25
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryCategoryRequest {

    /**
     * 上级分类ID
     */
    private Long pid;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;

    /**
     * 分类代码
     */
    @NotBlank(message = "分类代码不能为空")
    private String code;

    /**
     * 分类类型
     */
    @NotBlank(message = "分类类型不能为空")
    private String type;

    /**
     * 分类列
     */
    @NotEmpty(message = "分类列不能为空")
    private List<DictionaryColumnRequest> columns;

}
