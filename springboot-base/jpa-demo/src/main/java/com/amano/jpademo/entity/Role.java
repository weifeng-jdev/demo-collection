package com.amano.jpademo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @className: Role
 * @package com.amano.jpademo.entity
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@Data
@Entity
@Table(name = "t_role")
@EntityListeners(AuditingEntityListener.class)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @CreatedDate
    private LocalDateTime createTime;
    @CreatedBy
    private Long createBy;
    @LastModifiedDate
    private LocalDateTime updateTime;
    @LastModifiedBy
    private Long updateBy;

//    private Integer deleted;
}
