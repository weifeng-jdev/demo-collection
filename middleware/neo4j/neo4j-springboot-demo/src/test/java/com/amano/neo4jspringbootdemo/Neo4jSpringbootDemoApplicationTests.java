package com.amano.neo4jspringbootdemo;

import com.amano.neo4jspringbootdemo.entity.UserNode;
import com.amano.neo4jspringbootdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
@Slf4j
class Neo4jSpringbootDemoApplicationTests {
	@Autowired
	private UserService userService;
	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateUserNode() {
		UserNode userNode = userService.createUserNode(UserNode.builder().uname("wangwu").realName("王五").build());
		log.info("create userNode:{}", userNode);
	}

	@Test
	public void testDeleteUserById() {
		boolean b = userService.deleteUserById(12L);
		log.info("delete result:{}", b);
	}

	@Test
	public void testGetUserById() {
		UserNode userNode = userService.getUserById(13L);
		log.info("userNode:{}", userNode);
	}

	@Test
	public void testGetUserByName() {
		UserNode user = userService.getUserByName("张三");
		log.info("user:{}", user);
	}

	@Test
	public void testGetUserByName2() {
		UserNode user = userService.findFriend("李四2", "张三");
		log.info("user:{}", user);
	}

	@Test
	public void testUpdateUserById() {
		UserNode userNode = userService.getUserById(13L);
		if (Objects.nonNull(userNode)) {
			userNode.setRealName("李四2");
			userService.updateUserById(userNode);
		}
		log.info("userNode:{}", userNode);
	}

	@Test
	public void testRelateUser() {
		UserNode userNode2 = userService.getUserById(10L);
		userNode2.setFriends(Lists.newArrayList(userService.getUserById(13L), userService.getUserById(14L)));
		userService.updateUserById(userNode2);
	}

	@Test
	public void testDeRelateUser() {
		UserNode userNode2 = userService.getUserById(10L);
		userNode2.setFriends(null);
		userService.updateUserById(userNode2);
	}
}
