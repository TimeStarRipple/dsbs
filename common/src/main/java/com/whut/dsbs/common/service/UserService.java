package com.whut.dsbs.common.service;

import com.whut.dsbs.common.dto.User;

import java.util.List;

/**
 * 用户功能的服务接口
 *
 * Created by zyb on 2017-04-28.
 */
public interface UserService {

    List<User> selectAll();

    List<User> selectByPage(int page, String filter);

    User selectById(int id);

    /**
     * 验证登录的方法
     * @param user
     * @return
     */
    User selectByUser(User user);

    User update(User user);
}
