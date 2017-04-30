package com.whut.dsbs.customer.service;

import com.whut.dsbs.common.dto.User;

import java.util.List;

/**
 * 消费者的用户业务逻辑层，用于调用用户服务
 *
 * Created by zyb on 2017-04-29.
 */
public interface UserService {

    List<User> getAllUser();

    User login(User user);
}
