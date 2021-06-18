package com.zhi.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.crypto.digest.DigestUtil;
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

    public static void main(String[] args) {
        getDataDigest();
    }

    private static void getDataDigest() {
        String token = "0be2f1dfae1e18ee585fb19a42847760";
        String xml = "<GetProductList>\n" +
                "    <User>maggie.zheng@vantop.com</User>\n" +
                "    <RequestTime>2021-05-11 15:45:44</RequestTime>\n" +
                "    <PageNumber>1</PageNumber>\n" +
                "    <ItemsPerPage>200</ItemsPerPage>\n" +
                "    <GetProductListRequest/>\n" +
                "</GetProductList>";
        String leftToken = token.substring(0, 16);
        String rightToken = token.substring(16);
        String sign = DigestUtil.md5Hex(leftToken + xml + rightToken);
        System.out.println(sign);
    }

    private static void getDataDigest2() {
        String token = "1341a610bff0840f16f0de15b43f4b1d";
        String xml = "<GetProductList>\n" +
                "    <User>295544925@qq.com</User>\n" +
                "    <RequestTime>2021-05-12 14:22:31</RequestTime>\n" +
                "    <PageNumber>1</PageNumber>\n" +
                "    <ItemsPerPage>10</ItemsPerPage>\n" +
                "</GetProductList>";
        String leftToken = token.substring(0, 16);
        String rightToken = token.substring(16);
        String sign = DigestUtil.md5Hex(leftToken + xml + rightToken);
        System.out.println(sign);
    }
}
