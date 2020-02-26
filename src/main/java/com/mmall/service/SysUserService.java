package com.mmall.service;

import com.google.common.base.Preconditions;
import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.dao.SysUserMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysUser;
import com.mmall.param.UserParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.MD5Util;
import com.mmall.util.PasswordUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@RestController
public class SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    //    @Resource
//    private SysLogService sysLogService
    public void save(UserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话被占用");
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱被占用");
        }
        String password = PasswordUtil.randomPassword();
        password = "123456";
        MD5Util.encrypt(password);

        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");
        SysUser after=SysUser.builder().id(param.getId()).username(param.getUserName())
                .telephone(param.getTelephone()).mail(param.getMail())
                .deptId(param.getDeptId()).status(param.getStatus())
                .remark(param.getRemark()).build();
        after.setOperateIp("127.0.0.1");
        after.setOperator("system");
        after.setOperateTime(new Date());

    }

    public void update(UserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话被占用");
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱被占用");
        }

        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");
        SysUser user=SysUser.builder().id(param.getId()).username(param.getUserName())
                .telephone(param.getTelephone()).mail(param.getMail())
                .deptId(param.getDeptId()).status(param.getStatus())
                .remark(param.getRemark()).build();
        user.setOperateIp("127.0.0.1");
        user.setOperator("system");
        user.setOperateTime(new Date());
        //  TODO: SendEmail
        sysUserMapper.insertSelective(user);

    }

    private boolean checkEmailExist(String mail, Integer userId)
    {
        return sysUserMapper.countByMail(mail,userId)>0;
    }

    private boolean checkTelephoneExist(String telephone, Integer userId)
    {
        return sysUserMapper.countByTelephone(telephone,userId)>0;
    }

    public SysUser findByKeyword(String username) {

        return sysUserMapper.findByKeyword(username);
    }

    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery page) {
        BeanValidator.check(page);
        int count = sysUserMapper.countByDeptId(deptId);
        if (count>0) {
            List<SysUser> list = sysUserMapper.getPageByDeptId(deptId, page);
            return PageResult.<SysUser>builder().total(count).data(list).build();
        }
        return PageResult.<SysUser>builder().build();
    }
}
