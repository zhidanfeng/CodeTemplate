package com.zhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.core.entity.User;
import com.zhi.dao.TestDao;
import com.zhi.dao.UserDao;
import com.zhi.service.TestService;
import com.zhi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> listAll() {
        return super.list();
    }
}
