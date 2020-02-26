package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.param.AclModuleParam;
import com.mmall.service.SysAclModuleService;
import com.mmall.service.SysTreeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/aclModule")
public class SysAclModuleController {
    @Resource
    private SysAclModuleService sysAclModuleService;
    @Resource
    private SysTreeService sysTreeService;

    @ApiOperation(value = "查询权限模块信息", notes = "查询权限模块信息")
    @GetMapping("/acl.page")
    public ModelAndView page() {
        return new ModelAndView("acl");
    }


    @ApiOperation(value = "保存权限模块信息", notes = "保存权限模块信息")
    @PostMapping("/save.json")
    @ResponseBody
    public JsonData saveAclModule(AclModuleParam param) {
        sysAclModuleService.save(param);
        return JsonData.success();
    }

    @ApiOperation(value = "更新权限模块树信息", notes = "更新权限模块树信息")
    @PostMapping("/updateAclModule.json")
    @ResponseBody
    public JsonData updateAclModule(AclModuleParam param) {
        sysAclModuleService.update(param);
        return JsonData.success();
    }



    @ApiOperation(value = "权限模块树信息", notes = "权限模块树信息")
    @PostMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {

        return JsonData.success(sysTreeService.aclModuleTree());
    }

}
