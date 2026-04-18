package com.springboot.week2.service;


import com.springboot.week2.entity.Department;
import java.util.List;

public interface DepartmentService {

    public List<Department> getAllDepartments();

    public Department saveDepartment(Department department);

    public Department putDepartment(Department department);

    public boolean deleteDepartment(Department department);

    public Department getDepartmentById(Long Id);

}
