package com.mmall.controller;

import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.common.JsonData;
import com.mmall.model.SysUser;
import com.mmall.param.DeptParam;
import com.mmall.param.UserParam;
import com.mmall.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Controller
@RequestMapping("/sys/user")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @ApiOperation(value = "保存用户信息", notes = "保存用户信息")
    @PostMapping ("/save.json")
    @ResponseBody
    public JsonData saveDept(UserParam param)  {

        sysUserService.save(param);
        return JsonData.success();
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @PostMapping ("/update.json")
    @ResponseBody
    public JsonData update(UserParam param) {
        sysUserService.update(param);
        return JsonData.success();
    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    @GetMapping ("/page.json")
    @ResponseBody
    public JsonData page(@RequestParam("deptId") int deptId, PageQuery pageQuery) {
        PageResult<SysUser> result = sysUserService.getPageByDeptId(deptId, pageQuery);
        return JsonData.success(result);
    }

}
