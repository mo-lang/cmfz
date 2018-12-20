package com.baizhi.cmfz.service.Impl;

import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.mapper.AdminMapper;
import com.baizhi.cmfz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public void login(Admin a) {
        Admin admin = adminMapper.selectOne(a);
        if (admin == null) throw new RuntimeException("用户名或密码错误");
        a.setId(admin.getId());
    }
}
