package com.whut.dsbs.customer.controller;

import com.whut.dsbs.common.dto.User;
import com.whut.dsbs.customer.constants.JsonResult;
import com.whut.dsbs.customer.service.UserService;
import com.whut.dsbs.customer.utils.DecodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户控制层
 *
 * Created by zyb on 2017-04-28.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userServiceImpl;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUserById(){
        return new User(3, "tangtang", "tangtangdemima");
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public List<User> getAllUser(){
        return userServiceImpl.getAllUser();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public JsonResult login(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");

        //判定请求头中是否存在账号密码
        if(authorization == null || "".equals(authorization) || "Basic".equals(authorization)){
            throw new RuntimeException("请登录后再使用该系统！");
        }

        User user = DecodeUtil.decode(authorization);

        if(user.getUsername() == null || "".equals(user.getUsername())){
            throw new RuntimeException("账号不能为空！");
        }

        if(user.getPassword() == null || "".equals(user.getPassword())){
            throw new RuntimeException("密码不能为空！");
        }

        User result = userServiceImpl.login(user);
        if(result == null || result.getUsername() == null){
            throw new RuntimeException("账号或密码错误！");
        }

        return new JsonResult("200", "登录成功", result);
    }

}
