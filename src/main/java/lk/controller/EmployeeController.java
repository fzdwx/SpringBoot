package lk.controller;

import lk.dao.DepartmentDao;
import lk.dao.EmployeeDao;
import lk.pojo.Department;
import lk.pojo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * @author likeLove
 * @time 2020-08-06  15:13
 */
@Controller
@RequestMapping ("/emp")
public class EmployeeController {
    final
    EmployeeDao employeeDao;
    final
    DepartmentDao department;

    public EmployeeController(EmployeeDao employeeDao, DepartmentDao department) {
        this.employeeDao = employeeDao;
        this.department = department;
    }

    //获取员工列表
    @GetMapping ("/emps")
    public String list(Model model) {
        Collection<Employee> list = employeeDao.getAll();
        model.addAttribute("list", list);
        return "emp/list";
    }

    //去添加页面
    @GetMapping ("")
    public String toAddPage(Model model) {
        Collection<Department> list = department.getDepartments();
        model.addAttribute("depts", list);
        return "emp/add";
    }

    //添加
    @PostMapping ("")
    public String addEmp(Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emp/emps";
    }
}
