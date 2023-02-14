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
 * 后台菜单表
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_menu")
@Schema(title ="UmsMenu对象", description="后台菜单表")
public class UmsMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "父级ID")
    private Long parentId;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "菜单名称")
    private String title;

    @Schema(title = "菜单级数")
    private Integer level;

    @Schema(title = "菜单排序")
    private Integer sort;

    @Schema(title = "前端名称")
    private String name;

    @Schema(title = "前端图标")
    private String icon;

    @Schema(title = "前端隐藏")
    private Integer hidden;


}
