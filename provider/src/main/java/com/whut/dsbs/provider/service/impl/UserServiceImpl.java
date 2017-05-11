package com.whut.dsbs.provider.service.impl;

import com.whut.dsbs.common.dto.Role;
import com.whut.dsbs.common.dto.User;
import com.whut.dsbs.common.service.UserService;
import com.whut.dsbs.provider.dao.RoleDao;
import com.whut.dsbs.provider.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务的实现
 * Created by zyb on 2017-04-29.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public User selectById(int i) {
        return userDao.selectById(i);
    }

    @Override
    public User selectByUser(User user) {
        User result = userDao.selectByUser(user);
        Role resultRole = roleDao.getRoleById(result.getRoleId());
        result.setRole(resultRole);
        return result;
    }
}
