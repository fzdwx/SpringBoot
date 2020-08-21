package lk.dao;

import lk.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author likeLove
 * @time 2020-08-08  14:26
 */
@Mapper
@Repository
public interface DepartmentMapper {
    @Cacheable(cacheNames = "dep")
    List<Department> getAll();
    
    public Department getDepById(Integer id);

    public int delDepById(Integer id);

    public boolean insertDep(Department department);

    public int updateDep(Department department);
}