package com.amano.jpademo.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @className: Employ
 * @package com.amano.jpademo.entity
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/29
 **/
@Data
@Entity
@Table(name = "t_user")
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class User {
    @Id // 标记主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //标记主键生成策略
    private Long id;
    private String name;

    // 多对一关系，配置级联属性
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "DEPT_ID"
    )
    private Department department;
    @CreatedDate
    private LocalDateTime createTime;
    @CreatedBy
    private Long createBy;
    @LastModifiedDate
    private LocalDateTime updateTime;
    @LastModifiedBy
    private Long updateBy;

//    private Integer deleted;

    @ManyToMany
    @JoinTable(
            name = "T_USER_ROLE",
            joinColumns =
            @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    )
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private Set<Role> roles;
}
