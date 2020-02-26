package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.dto.DeptLevelDto;
import com.mmall.param.DeptParam;
import com.mmall.service.SysDeptService;
import com.mmall.service.SysTreeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("sys/dept")
@Slf4j
public class SysDeptController {
    @Resource
    private SysDeptService sysDeptService;
    @Resource
    private SysTreeService sysTreeService;

    @ApiOperation(value = "获取部门信息", notes = "获取部门信息")
    @GetMapping("/dept.page")
    public ModelAndView page() {
        return new ModelAndView("dept");
    }

    @ApiOperation(value = "保存部门信息", notes = "保存部门信息")
    @PostMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam param) {

        sysDeptService.save(param);
        return JsonData.success();
    }


    @ApiOperation(value = "部门树结构", notes = "部门树结构")
    @PostMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        List<DeptLevelDto> dtoList = sysTreeService.deptTree();
        return JsonData.success(dtoList);
    }



    @ApiOperation(value = "更新部门信息", notes = "更新部门信息")
    @PostMapping("/update.json")
    @ResponseBody
    public JsonData update(DeptParam param) {
        sysDeptService.update(param);
        return JsonData.success();
    }
}
