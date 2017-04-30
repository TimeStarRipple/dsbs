package com.whut.dsbs.provider;

import com.github.pagehelper.PageHelper;
import com.whut.dsbs.common.dto.User;
import com.whut.dsbs.common.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		List<User> userList = userService.selectAll();
		System.out.println(userList.get(0).toString());
	}

	@Test
	public void testPageHelper(){
		PageHelper.startPage(1, 1);
		List<User> userList = userService.selectAll();
		System.out.println(userList.size());
	}

	@Test
	public void testMapper(){
		System.out.println(userService.selectById(1).toString());
	}

}
