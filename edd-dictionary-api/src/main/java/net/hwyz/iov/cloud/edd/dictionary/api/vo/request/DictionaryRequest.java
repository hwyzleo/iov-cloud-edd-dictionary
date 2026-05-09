package net.hwyz.iov.cloud.edd.dictionary.api.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据字典
 *
 * @author hwyz_leo
 * @since 2024-10-25
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典代码
     */
    private String code;

    /**
     * 字典分类代码
     */
    private String categoryCode;

    /**
     * 所选列
     */
    private String selectColumn;

    /**
     * 指定条件
     */
    private String whereCondition;

}
