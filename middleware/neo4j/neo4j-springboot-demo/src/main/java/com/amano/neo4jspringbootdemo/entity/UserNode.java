package com.amano.neo4jspringbootdemo.entity;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

/**
 * @className: UserNode
 * @package com.amano.neo4jspringbootdemo.entity
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/7/30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Node
@Builder
@ToString
public class UserNode {
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String uname;

    @Property
    private String realName;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private List<UserNode> friends;
}
