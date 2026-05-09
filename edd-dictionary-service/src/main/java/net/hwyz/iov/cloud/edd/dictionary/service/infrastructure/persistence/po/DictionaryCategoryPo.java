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
 * 数据字典分类 数据对象
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
@TableName("tb_dictionary_category")
public class DictionaryCategoryPo extends BasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上级分类ID
     */
    @TableField("pid")
    private Long pid;

    /**
     * 分类名称
     */
    @TableField("name")
    private String name;

    /**
     * 分类代码
     */
    @TableField("code")
    private String code;

    /**
     * 分类类型
     */
    @TableField("type")
    private String type;

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
