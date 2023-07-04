package com.amano.redisdemo;

import com.amano.redisdemo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = RedisDemoApplication.class)
@Slf4j
class RedisDemoApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {
    }

    @Test
    void testStringKey() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 模拟redis操作string，设置key10秒过期
        redisTemplate.opsForValue().set("user", new User("amano", 17));
        log.info("user: {}", redisTemplate.opsForValue().get("user"));
        CompletableFuture.supplyAsync(() -> {
            try {
                redisTemplate.expire("user", 10, TimeUnit.SECONDS);
                User o = (User) redisTemplate.opsForValue().get("user");
                log.info("user: {}", o);
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }).thenRun(() -> {
            User o = (User) redisTemplate.opsForValue().get("user");
            log.info("user: {}", o);
            countDownLatch.countDown();
        });
        countDownLatch.await();
    }

    @Test
    void testHash() {
        // 模拟redis操作hash
        redisTemplate.opsForHash().put("user", "name", "amano");
        redisTemplate.opsForHash().put("user", "age", 17);
        log.info("user1: {}", redisTemplate.opsForHash().entries("user"));
        redisTemplate.opsForHash().increment("user", "age", 1);
        log.info("user2: {}", redisTemplate.opsForHash().entries("user"));
        redisTemplate.opsForHash().put("user", "gender", "man");
        log.info("user3: {}", redisTemplate.opsForHash().multiGet("user", List.of("name", "age", "gender")));
        log.info("user_keys: {}", redisTemplate.opsForHash().keys("user"));
        log.info("user_values: {}", redisTemplate.opsForHash().values("user"));
        log.info("user_size: {}", redisTemplate.opsForHash().size("user"));
    }

    @Test
    void testSet() {
//        // 模拟redis操作set
//        redisTemplate.opsForSet().add("names", "amano", "zhangsan", "lisi", "wangwu", "go", "python");
//        log.info("names: {}, names_size: {}", redisTemplate.opsForSet().members("names"), redisTemplate.opsForSet().size("names"));
//        redisTemplate.opsForSet().add("names2", "amano", "张三", "李四", "王五", "java", "python");
//        // 对比多个set的差异、并集、交集
//        log.info("diff:{}", redisTemplate.opsForSet().difference("names", "names2"));
//        log.info("union:{}", redisTemplate.opsForSet().union("names", "names2"));
//        log.info("intersect:{}", redisTemplate.opsForSet().intersect("names", "names2"));
//        // 将两个set的差异、并集、交集存入新的set
//        redisTemplate.opsForSet().differenceAndStore("names", "names2", "diff");
//        redisTemplate.opsForSet().unionAndStore("names", "names2", "union");
//        redisTemplate.opsForSet().intersectAndStore("names", "names2", "intersect");
//        log.info("diff_:{}", redisTemplate.opsForSet().members("diff"));
//        log.info("union_:{}", redisTemplate.opsForSet().members("union"));
//        log.info("intersect_:{}", redisTemplate.opsForSet().members("intersect"));
//        log.info("random_access:{}", redisTemplate.opsForSet().distinctRandomMembers("names", 2));
        // set特性数据已存在插入失败
        log.info("add exists:{}", redisTemplate.opsForSet().add("names", "amano"));
    }

    @Test
    void testList() {
        // 模拟redis操作list
        redisTemplate.opsForList().leftPush("list", "amano");
        redisTemplate.opsForList().leftPush("list", "zhangsan");
        redisTemplate.opsForList().leftPush("list", "lisi");
        redisTemplate.opsForList().leftPush("list", "wangwu");
        redisTemplate.opsForList().leftPush("list", "go");
        redisTemplate.opsForList().leftPush("list", "python");
        log.info("list: {}, list_size: {}", redisTemplate.opsForList().range("list", 0, -1), redisTemplate.opsForList().size("list"));
        redisTemplate.opsForList().set("list", 0, "java");
        log.info("list: {}, list_size: {}", redisTemplate.opsForList().range("list", 0, -1), redisTemplate.opsForList().size("list"));
        redisTemplate.opsForList().move(ListOperations.MoveFrom.fromHead("list"), ListOperations.MoveTo.toTail("new_list"));
        log.info("new_list: {}, new_list_size: {}", redisTemplate.opsForList().range("new_list", 0, -1), redisTemplate.opsForList().size("new_list"));
        redisTemplate.opsForList().trim("list", 0, 2);
        log.info("list: {}, list_size: {}", redisTemplate.opsForList().range("list", 0, -1), redisTemplate.opsForList().size("list"));
        redisTemplate.opsForList().remove("list", 1, "java");
        log.info("list: {}, list_size: {}", redisTemplate.opsForList().range("list", 0, -1), redisTemplate.opsForList().size("list"));
        redisTemplate.opsForList().rightPopAndLeftPush("list", "new_list");
        log.info("new_list: {}, new_list_size: {}", redisTemplate.opsForList().range("new_list", 0, -1), redisTemplate.opsForList().size("new_list"));
    }

    @Test
    void testZset() {
        // 模拟redis操作zset
        redisTemplate.opsForZSet().add("zset", "amano", 1);
        redisTemplate.opsForZSet().add("zset", "zhangsan", 2);
        redisTemplate.opsForZSet().add("zset", "lisi", 3);
        redisTemplate.opsForZSet().add("zset", "wangwu", 4);
        redisTemplate.opsForZSet().add("zset", "go", 5);
        redisTemplate.opsForZSet().add("zset", "python", 6);
        log.info("zset: {}, zset_size: {}", redisTemplate.opsForZSet().range("zset", 0, -1), redisTemplate.opsForZSet().size("zset"));
        redisTemplate.opsForZSet().add("zset2", "amano", 1);
        redisTemplate.opsForZSet().add("zset2", "张三", 2);
        redisTemplate.opsForZSet().add("zset2", "李四", 3);
        redisTemplate.opsForZSet().add("zset2", "王五", 4);
        redisTemplate.opsForZSet().add("zset2", "java", 5);
        redisTemplate.opsForZSet().add("zset2", "python", 6);
        log.info("zset2: {}, zset2_size: {}", redisTemplate.opsForZSet().range("zset2", 0, -1), redisTemplate.opsForZSet().size("zset2"));
        // 对比多个zset的差异、并集、交集
        log.info("diff:{}", redisTemplate.opsForZSet().difference("zset", "zset2"));
        log.info("union:{}", redisTemplate.opsForZSet().unionAndStore("zset", "zset2", "union"));
        log.info("intersect:{}", redisTemplate.opsForZSet().intersectAndStore("zset", "zset2", "intersect"));
        // 将两个zset的差异、并集、交集存入新的zset
        redisTemplate.opsForZSet().differenceAndStore("zset", List.of("zset2"), "diff");
        // 通过score获取zset中的元素
        log.info("rangeByScore:{}", redisTemplate.opsForZSet().rangeByScore("zset", 1, 3));
        // 通过score获取zset中的元素并排序
        log.info("rangeByScoreWithScores:{}", redisTemplate.opsForZSet().rangeByScoreWithScores("zset", 1, 3));
    }

    @Test
    void testHyperLogLog() {
        String msgKey = "msg_1";
        Set<Integer> uids = Stream.generate(() -> new Random(1000000).nextInt()).limit(100000).collect(Collectors.toSet());
        redisTemplate.opsForHyperLogLog().add(msgKey, uids);
        log.info("count:{}", redisTemplate.opsForHyperLogLog().size(msgKey));
    }

    @Test
    void testBitMap() {
        String phone = "18511334455";
        String phone2 = "18511334452";
        Boolean aBoolean = redisTemplate.opsForValue().setBit(phone.substring(0, 3), Long.parseLong(phone.substring(3)), true);
        log.info("aBoolean:{}", aBoolean);
        Boolean exists = redisTemplate.opsForValue().getBit(phone.substring(0, 3), Long.parseLong(phone.substring(3)));
        Boolean exists2 = redisTemplate.opsForValue().getBit(phone2.substring(0, 3), Long.parseLong(phone2.substring(3)));
        log.info("exists:{}, exists2:{}", exists, exists2);
    }

    @Test
    void testRedisson() {
        RLock lock = redissonClient.getLock("lock");
        try {
            if (!lock.tryLock(1,30, TimeUnit.SECONDS)) {
                log.info("get lock failed");
                return;
            }
            // do something
            log.info("get lock");
        } catch (InterruptedException e) {
            log.info("get lock failed");
            throw new RuntimeException(e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
