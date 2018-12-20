package com.baizhi.cmfz.service.Impl;

import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.mapper.MenuMapper;
import com.baizhi.cmfz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> queryAllMenu() {
        return menuMapper.queryAllMenu();
    }
}
