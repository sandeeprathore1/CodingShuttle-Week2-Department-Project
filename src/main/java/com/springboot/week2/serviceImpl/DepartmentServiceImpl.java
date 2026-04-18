package com.springboot.week2.serviceImpl;

import com.springboot.week2.entity.Department;
import com.springboot.week2.repository.DepartmentRepository;
import com.springboot.week2.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department putDepartment(Department newDetails) {
        Department updateDepartment = departmentRepository.findById(newDetails.getId()).orElseThrow(() -> {
            return new RuntimeException("Department Id does not exist");
        });

        updateDepartment.setTitle(newDetails.getTitle());
        if(newDetails.isActive()!=updateDepartment.isActive()) {
            updateDepartment.setActive(newDetails.isActive());
        }

        return departmentRepository.save(updateDepartment);
    }

    @Override
    public boolean deleteDepartment(Department department) {
        Department deleteDepartment = departmentRepository.findById(department.getId()).orElseThrow(() -> {
            return new RuntimeException("Department Id does not exist");
        });

        departmentRepository.delete(deleteDepartment);

        return true;
    }

    @Override
    public Department getDepartmentById(Long id) {

        return departmentRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Department Id does not exist");
        });

    }
}
