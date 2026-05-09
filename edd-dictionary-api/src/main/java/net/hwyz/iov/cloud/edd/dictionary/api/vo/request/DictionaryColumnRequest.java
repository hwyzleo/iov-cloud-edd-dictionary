package net.hwyz.iov.cloud.edd.dictionary.api.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据字典列
 *
 * @author hwyz_leo
 * @since 2024-10-25
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryColumnRequest {

    /**
     * 列名称
     */
    @NotBlank(message = "列名称不能为空")
    private String name;

    /**
     * 列代码
     */
    @NotBlank(message = "列代码不能为空")
    private String code;

    /**
     * 列类型
     */
    @NotBlank(message = "列类型不能为空")
    private String type;

    /**
     * 列长度
     */
    private Integer len;

    /**
     * 列是否唯一
     */
    @NotNull(message = "列是否唯一不能为空")
    private Boolean uniq;

    /**
     * 列是否可为空
     */
    @NotNull(message = "列是否可为空不能为空")
    private Boolean nullable;

    /**
     * 值类型
     */
    @NotNull(message = "值类型不能为空")
    private Integer valueType;

    /**
     * 值范围
     */
    private String valueRange;

    /**
     * 排序
     */
    private Integer sort;
}
