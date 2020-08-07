package lk;

import lk.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith (SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Resource
    User user;

    Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void log() {
        log.trace("这是trace日志");
        log.debug("这是debug");
        log.warn("出现错误");
        System.out.println(1);
    }

    @Test
    public void getUser() {
        System.out.println(user);
    }

}