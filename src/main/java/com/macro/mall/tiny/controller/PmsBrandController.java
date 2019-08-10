package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.PmsBrand;
import com.macro.mall.tiny.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 品牌管理Controller
 * Created by macro on 2019/4/19.
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:create')")
    public CommonResult create(@RequestBody PmsBrand brand) {
        CommonResult commonResult;
        int count = brandService.create(brand);
        if (count == 1) {
            commonResult = CommonResult.success(brand);
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }

    @ApiOperation("修改指定品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:update')")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody PmsBrand brand) {
        CommonResult commonResult;
        int count = brandService.update(id, brand);
        if (count == 1) {
            commonResult = CommonResult.success(brand);
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }

    @ApiOperation("删除指定品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = brandService.delete(id);
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("获取指定品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<PmsBrand> detail(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.detail(id));
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<CommonPage<PmsBrand>> list(@RequestParam(value = "pageNum", defaultValue = "1")
                                                   @ApiParam("页码") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10")
                                                   @ApiParam("每页数量") Integer pageSize,
                                                   @RequestParam(value = "name",required = false)
                                                   @ApiParam("品牌名称") String name) {
        List<PmsBrand> brandList = brandService.list(pageNum, pageSize,name);
        return CommonResult.success(CommonPage.restPage(brandList));
    }
}
