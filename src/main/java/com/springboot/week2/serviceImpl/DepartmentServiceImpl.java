package com.springboot.week2.serviceImpl;

import com.springboot.week2.entity.Department;
import com.springboot.week2.exception.InvalidInputException;
import com.springboot.week2.exception.ResourceNotFoundException;
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
        if (department == null || department.getTitle() == null || department.getTitle().isEmpty()) {
            throw new InvalidInputException("Department title cannot be null or empty");
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department putDepartment(Department newDetails) {
        if (newDetails == null || newDetails.getId() == null) {
            throw new InvalidInputException("Department id cannot be null");
        }

        Department updateDepartment = departmentRepository.findById(newDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + newDetails.getId() + " does not exist"));

        if (newDetails.getTitle() != null && !newDetails.getTitle().isEmpty()) {
            updateDepartment.setTitle(newDetails.getTitle());
        }
        if (newDetails.isActive() != updateDepartment.isActive()) {
            updateDepartment.setActive(newDetails.isActive());
        }

        return departmentRepository.save(updateDepartment);
    }

    @Override
    public boolean deleteDepartment(Department department) {
        if (department == null || department.getId() == null) {
            throw new InvalidInputException("Department id cannot be null");
        }

        Department deleteDepartment = departmentRepository.findById(department.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + department.getId() + " does not exist"));

        departmentRepository.delete(deleteDepartment);

        return true;
    }

    @Override
    public Department getDepartmentById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Department id must be a positive number");
        }

        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + id + " does not exist"));
    }
}
