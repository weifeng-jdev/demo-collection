package com.amano.neo4jspringbootdemo.repository;

import com.amano.neo4jspringbootdemo.entity.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @className: UserRepository
 * @package com.amano.neo4jspringbootdemo.repository
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/7/30
 **/
@Repository
public interface UserRepository extends Neo4jRepository<UserNode, Long> {
    @Query("MATCH (n:UserNode) WHERE n.realName = $realName RETURN n")
    UserNode findByUname(String realName);

    @Query("MATCH (u1:UserNode)-[r:ACTED_IN]->(u2:UserNode) WHERE u1.realName=$realName1 and u2.realName=$realName2 RETURN u1")
    UserNode findFriend(String realName1, String realName2);
}
