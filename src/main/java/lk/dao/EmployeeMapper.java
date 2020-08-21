package lk.dao;

import lk.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author likeLove
 * @time 2020-08-08  16:04
 */
@Mapper
@Repository
public interface EmployeeMapper {

    @Cacheable(cacheNames = "emp")
    List<Employee> getAll();


    Employee get(int id);

    boolean update(Employee e);

    boolean delete(int id);

    boolean insert(Employee e);

}