package com.amano.jpademo.dao;

import com.amano.jpademo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @className: RoleRepository
 * @package com.amano.jpademo.dao
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
