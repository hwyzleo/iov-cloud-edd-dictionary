package net.hwyz.iov.cloud.edd.dictionary.service.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.hwyz.iov.cloud.framework.mysql.po.BasePo;

/**
 * <p>
 * 数据字典 数据对象
 * </p>
 *
 * @author hwyz_leo
 * @since 2024-10-25
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_dictionary")
public class DictionaryPo extends BasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典代码
     */
    @TableField("code")
    private String code;

    /**
     * 字典分类代码
     */
    @TableField("category_code")
    private String categoryCode;

    /**
     * 所选列
     */
    @TableField("select_column")
    private String selectColumn;

    /**
     * 指定条件
     */
    @TableField("where_condition")
    private String whereCondition;

    /**
     * 是否启用
     */
    @TableField("enable")
    private Boolean enable;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
}
