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
 * 后台用户表
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_admin")
@Schema(title="UmsAdmin对象", description="后台用户表")
public class UmsAdmin implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    @Schema(title = "头像")
    private String icon;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "昵称")
    private String nickName;

    @Schema(title = "备注信息")
    private String note;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "最后登录时间")
    private Date loginTime;

    @Schema(title = "帐号启用状态：0->禁用；1->启用")
    private Integer status;


}
