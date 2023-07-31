package com.amano;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.neo4j.driver.Values.parameters;

/**
 * Hello world!
 */
public class App {
    private static final Driver driver;

    static {
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "Aa111111"));
    }

    public static void main(String[] args) {
        try {
            // 创建节点
//            create();
//            create("lisi");
//            queryPersonNames();
//            createRelation();
            delete();
        } finally {
            driver.close();
        }
    }

    /**
     * 创建节点
     */
    public static void create() {
        try (Session session = driver.session()) {
            int r = session.executeWrite(tx -> {
                Query query = new Query("CREATE (n:Person {name: 'Tom Hanks'}) return id(n)");
                Result result = tx.run(query);
                return result.single().get(0).asInt();
            });
            System.out.println(r);
        }
    }

    /**
     * 创建节点通过参数
     */
    public static void create(String name) {
        try (Session session = driver.session()) {
            int r = session.executeWrite(tx -> {
                Query query = new Query("CREATE (n:Person {name: $name}) return id(n)", parameters("name", name));
                Result result = tx.run(query);
                return result.single().get(0).asInt();
            });
            System.out.println(r);
        }
    }

    public static List<String> queryPersonNames() {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                Query query = new Query("MATCH (n:Person) RETURN n.name");
                Result result = tx.run(query);
                List<String> names = result.stream().map(r -> r.get(0).asString())
                        .collect(Collectors.toList());
                System.out.println(names);
                return names;
            });
        }
    }

    public static void createRelation() {
        try (Session session = driver.session()) {
            List<Object> r = session.executeWrite(tx -> {
                Query query = new Query("match (n1:Person), (n2:Person) where n1.name = 'zhangsan' and n2.name='lisi' create (n1)<-[r:Friend]-(n2) return n1.name, n2.name");
                Result result = tx.run(query);
                return result.single().get(0).asList();
            });
            System.out.println(r);
        }
    }

    public static void delete() {
        try (Session session = driver.session()) {
            int r = session.executeWrite(tx -> {
                Query query = new Query("match(p:Pet) delete p return id(p) ");
                Result result = tx.run(query);
                return result.single().get(0).asInt();
            });
            System.out.println(r);
        }
    }
}
