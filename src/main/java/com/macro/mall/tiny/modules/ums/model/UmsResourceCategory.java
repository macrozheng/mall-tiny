package com.macro.mall.tiny.modules.ums.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源分类表
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_resource_category")
@Schema(title ="UmsResourceCategory对象", description="资源分类表")
public class UmsResourceCategory implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "分类名称")
    private String name;

    @Schema(title = "排序")
    private Integer sort;


}
