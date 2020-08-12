package lk.controller;

import lk.dao.DepartmentMapper;
import lk.pojo.Department;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author likeLove
 * @time 2020-08-08  14:31
 */
@RestController
@RequestMapping ("/dep")
public class DepartmentController {
    final
    DepartmentMapper departmentMapper;

    public DepartmentController(DepartmentMapper departmentMapper) {this.departmentMapper = departmentMapper;}

    @GetMapping ("/{id}")
    public Department getDepartment(@PathVariable Integer id) {
        System.out.println(id);
        return departmentMapper.getDepById(id);
    }

    @GetMapping ("/")
    public boolean insertDepartment(Department department) {
        return departmentMapper.insertDep(department);
    }
}
