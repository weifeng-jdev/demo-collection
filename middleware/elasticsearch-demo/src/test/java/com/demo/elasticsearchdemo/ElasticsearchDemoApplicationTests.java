package com.demo.elasticsearchdemo;

import com.demo.elasticsearchdemo.entity.Department;
import com.demo.elasticsearchdemo.entity.User;
import com.demo.elasticsearchdemo.repository.UserRepository;
import com.demo.elasticsearchdemo.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
@Slf4j
class ElasticsearchDemoApplicationTests {
	@Autowired
	private IUserService userService;
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testInsert() {
		User user = new User()
				.setId(1L)
				.setAge(10)
				.setName("amano")
				.setDepartment(
						new Department()
								.setId(1L)
								.setPId(0L)
								.setName("root")
				);
		userService.saveUser(user);
	}

	@Test
	void ListPageUser() {
		Page<User> users = userService.listPageUser(1, 10);
		log.info("users:{}", users);
	}

	@Test
	void testGetUser() {
		User userBydId = userService.getUserBydId(1L);
		log.info("user: {}", userBydId);
	}

	@Test
	void testListUser() {
		List<User> users = userService.listUser();
		log.info("users: {}", users);
	}

	@Test
	void testDeleteUser() {
		userService.deleteUser(1L);
	}

	@Test
	void testUpdateUser() {
		User user = new User()
				.setId(1L)
				.setAge(20)
				.setName("zhangsan")
				.setDepartment(
						new Department()
								.setId(2L)
								.setPId(1L)
								.setName("l1")
				);
		User user1 = userService.updateUserById(user);
		log.info("user1:{}", user1);
	}
}
