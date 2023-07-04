package com.amano.jpademo.entity;

import lombok.Data;
import lombok.experimental.Accessors;
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
 * @className: Department
 * @package com.amano.jpademo.entity
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/29
 **/
@Data
@Entity
@Table(name = "t_department")
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId;
    @CreatedDate
    private LocalDateTime createTime;
    @CreatedBy
    private Long createBy;
    @LastModifiedDate
    private LocalDateTime updateTime;
    @LastModifiedBy
    private Long updateBy;

    // 一对多属性配置级联属性，完成级联插入
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "department"
    )
    private Set<User> users;
}
