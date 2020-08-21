package lk;

import lk.dao.DepartmentMapper;
import lk.dao.EmployeeMapper;
import lk.pojo.Department;
import lk.pojo.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith (SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest
public class AppTest {
    @Resource
    DataSource dataSource;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    EmployeeMapper employeeMapper;

    @Test
    public void redis() {
        Employee employee = employeeMapper.get(10);
        redisTemplate.opsForValue().set("poss2",employee);
    }

    Logger log = LoggerFactory.getLogger(getClass());

    //log
    @Test
    public void log() {
        log.trace("这是trace日志");
        log.debug("这是debug");
        log.warn("出现错误");
        System.out.println(1);
    }

    @Test
    public void jdbc() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(conn);
        System.out.println(dataSource.getClass());
    }

    @Resource
    DepartmentMapper departmentMapper;

    @Test
    public void mapper() {
        System.out.println(departmentMapper.insertDep(new Department(3, "qwe")));
    }

    @Test
    public void mapper2() {
        System.out.println(departmentMapper.getDepById(1));
    }
}