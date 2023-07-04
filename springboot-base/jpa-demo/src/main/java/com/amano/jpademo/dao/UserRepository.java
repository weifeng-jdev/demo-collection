package com.amano.jpademo.dao;

import com.amano.jpademo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className: EmployRepository
 * @package com.amano.jpademo.dao
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/29
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
