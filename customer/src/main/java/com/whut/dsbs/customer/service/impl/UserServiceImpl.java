package com.whut.dsbs.customer.service.impl;

import com.whut.dsbs.common.dto.User;
import com.whut.dsbs.common.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消费者用户业务逻辑层的实现
 *
 * Created by zyb on 2017-04-29.
 */
@Service("userServiceImpl")
public class UserServiceImpl implements com.whut.dsbs.customer.service.UserService{

    @Resource
    private UserService userService;


    @Override
    public List<User> getAllUser() {
        return userService.selectAll();
    }

    @Override
    public User login(User user) {
        return userService.selectByUser(user);
    }
}
