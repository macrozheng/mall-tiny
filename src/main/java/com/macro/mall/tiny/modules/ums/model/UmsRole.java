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
 * 后台用户角色表
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_role")
@Schema(title ="UmsRole对象", description="后台用户角色表")
public class UmsRole implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "描述")
    private String description;

    @Schema(title = "后台用户数量")
    private Integer adminCount;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "启用状态：0->禁用；1->启用")
    private Integer status;

    private Integer sort;


}
