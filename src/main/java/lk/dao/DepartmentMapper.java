package lk.dao;

import lk.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author likeLove
 * @time 2020-08-08  14:26
 */
@Mapper
@Repository
public interface DepartmentMapper {

    public Department getDepById(Integer id);

    public int delDepById(Integer id);


    public boolean insertDep(Department department);


    public int updateDep(Department department);
}