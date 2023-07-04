package com.amano.jpademo.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @className: UserRole
 * @package com.amano.jpademo.entity
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@Data
@Entity
@Table(name = "t_user_role")
@EntityListeners(AuditingEntityListener.class)
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    private Role role;
}
