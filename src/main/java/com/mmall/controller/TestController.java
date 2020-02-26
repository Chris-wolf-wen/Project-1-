package com.mmall.controller;

import com.mmall.common.ApplicationContextHelper;
import com.mmall.common.JsonData;
import com.mmall.dao.SysAclModuleMapper;
import com.mmall.exception.ParamException;
import com.mmall.param.TestVo;
import com.mmall.util.BeanValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {
    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        return JsonData.success("hello ,permission");
    }

    @ApiOperation(value = "验证信息", notes = "验证信息")
    @ResponseBody
    @GetMapping ("/validate.json")
    public JsonData validate(TestVo vo){
        log.info("validate");
        try {
            Map<String, String> map = BeanValidator.validateObject(vo);
            if (map != null && map.entrySet().size() > 0){
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    log.info("{}->{}",entry.getKey(),entry.getValue());
                }
            }
        }catch (Exception e){
        }
        return JsonData.success("test validate");
    }


    @ApiOperation(value = "验证测试", notes = "验证测试")
    @GetMapping("/validationTest.json")
    @ResponseBody
    public JsonData validateTest(TestVo vo) throws ParamException
    {
        log.info("validateTest");
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        BeanValidator.check(vo);
        return JsonData.success("hello ,validateTest");
    }
}
