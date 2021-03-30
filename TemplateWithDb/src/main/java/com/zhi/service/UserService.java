package com.zhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhi.core.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listAll();
    boolean addUser(User user);
}
