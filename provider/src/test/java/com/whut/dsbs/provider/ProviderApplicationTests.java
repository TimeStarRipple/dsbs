package com.whut.dsbs.provider;

import com.github.pagehelper.PageHelper;
import com.whut.dsbs.common.dto.User;
import com.whut.dsbs.common.service.UserService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderApplication.class})
public class ProviderApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(ProviderApplicationTests.class);

	@Autowired
	private UserService userService;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private TaskService taskService;


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

	@Test
	public void datasource() {
		System.out.println(dataSource.getClass());
		long count = taskService.createTaskQuery().taskAssignee("zzz").count();
		System.out.println(count);
		log.debug("条数[{}]", count);
	}


}
