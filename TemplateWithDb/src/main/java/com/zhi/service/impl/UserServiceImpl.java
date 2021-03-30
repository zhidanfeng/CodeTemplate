package com.zhi.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

    @Override
    public boolean addUser(User user) {
        if (this.checkIsExist(user.getUserName())) {
            throw new RuntimeException("用户名已存在");
        }
        ThreadUtil.sleep(500);
        user.setCreateId(1L);
        user.setCreateTime(DateUtil.current());
        user.setUpdateId(1L);
        user.setUpdateTime(user.getCreateTime());
        return super.save(user);
    }

    private boolean checkIsExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        List<User> list = super.list(queryWrapper);
        return CollectionUtil.isNotEmpty(list);
    }
}
