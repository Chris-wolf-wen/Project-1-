package com.mmall.controller;

import com.mmall.beans.PageQuery;
import com.mmall.common.JsonData;
import com.mmall.param.AclModuleParam;
import com.mmall.param.AclParam;
import com.mmall.service.SysAclService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/acl")
@Api(tags="角色表")
@Slf4j
public class SysAclController {
    @Resource
    private SysAclService sysAclService;

    @ApiOperation(value = "保存权限模块信息", notes = "保存权限模块信息")
    @PostMapping("/save.json")
    @ResponseBody
    public JsonData saveAclModule(AclParam param) {
        sysAclService.save(param);
        return JsonData.success();
    }


    @ApiOperation(value = "更新权限模块信息", notes = "更新权限模块信息")
    @PostMapping("/update.json")
    @ResponseBody
    public JsonData updateAclModule(AclParam param) {
        sysAclService.update(param);
        return JsonData.success();
    }


    @ApiOperation(value = "分页更新权限信息", notes = "分页更新权限信息")
    @PostMapping("/updateList.json")
    @ResponseBody
    public JsonData list(@RequestParam("aclModuleId") Integer aclModuleId, PageQuery page    ) {
        sysAclService.getPageByAclModuleId(aclModuleId, page);
        return JsonData.success();
    }

}
