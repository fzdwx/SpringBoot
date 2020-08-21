package lk.controller;

import lk.dao.DepartmentMapper;
import lk.dao.EmployeeMapper;
import lk.pojo.Department;
import lk.pojo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author likeLove
 * @time 2020-08-06  15:13
 */
@Controller
@RequestMapping ("/emp")
public class EmployeeController {
    private DepartmentMapper departmentMapper;
    private EmployeeMapper employeeMapper;
    public EmployeeController(DepartmentMapper departmentMapper, EmployeeMapper employeeMapper) {
        this.departmentMapper = departmentMapper;
        this.employeeMapper = employeeMapper;
    }

    //获取员工列表
    @GetMapping ("/emps")
    public String list(Model model) {
        Collection<Employee> list = employeeMapper.getAll();
        model.addAttribute("list", list);
        return "emp/list";
    }

    //去添加页面
    @GetMapping ("")
    public String toAddPage(Model model) {
        //获取员工部门信息
        Collection<Department> list = departmentMapper.getAll();
        model.addAttribute("list", list);
        return "emp/add";
    }

    //添加员工，重定向到获取员工列表请求
    @PostMapping ("")
    public String addEmp(Employee employee) {
        System.out.println(employee);
        employeeMapper.insert(employee);
        return "redirect:/emp/emps";
    }

    //去修改页面
    @GetMapping ("/{id}")
    public String toEditPage(@PathVariable ("id") Integer id, Model model) {
        //获取员工信息
        Employee employee = employeeMapper.get(id);
        model.addAttribute("emp", employee);
        //获取员工部门信息
        Collection<Department> list = departmentMapper.getAll();
        model.addAttribute("list", list);
        return "emp/edit";
    }

    //修改员工，重定向到获取员工列表请求
    @PutMapping ("")
    public String editEmp(Employee employee) {
        System.out.println("正在修改:" + employee);
        employeeMapper.update(employee);
        return "redirect:/emp/emps";
    }

    //删除员工，重定向到获取员工列表请求
    @DeleteMapping ("/{id}")
    public String delEmp(@PathVariable Integer id) {
        employeeMapper.delete(id);
        return "redirect:/emp/emps";
    }
}
