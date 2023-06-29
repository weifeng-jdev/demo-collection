package com.amano.mybatisdemo.controller;

import com.amano.common.entity.ResponseEntity;
import com.amano.mybatisdemo.entity.User;
import com.amano.mybatisdemo.entity.dto.UserCreateDTO;
import com.amano.mybatisdemo.entity.dto.UserPageQuery;
import com.amano.mybatisdemo.entity.dto.UserUpdateDTO;
import com.amano.mybatisdemo.entity.vo.UserVO;
import com.amano.mybatisdemo.service.IUserService;
import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @className: UserController
 * @package com.amano.mybatisdemo.controller
 * @description: 基于mybatis的基本增删改查，演示mybatis+mysql基本用法
 * @author: amano
 * @date: 2023/6/28
 **/
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listAllUser() {
        return ResponseEntity.ok(userService.listAllUser());
    }

    /**
     * PageHelper插件实现分页
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<Page<UserVO>> listUserPage(UserPageQuery query) {
        return ResponseEntity.ok(userService.listUserPage(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserVO> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping
    public ResponseEntity<UserVO> updateUserById(@Validated @RequestBody UserUpdateDTO userVO) {
        userService.updateUserById(userVO);
        return ResponseEntity.ok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok();
    }

    @PostMapping
    public ResponseEntity<UserVO> createUser(@Valid @RequestBody UserCreateDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

}