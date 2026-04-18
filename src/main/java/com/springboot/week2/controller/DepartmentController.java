package com.springboot.week2.controller;


import com.springboot.week2.dto.DepartmentDto;
import com.springboot.week2.entity.Department;
import com.springboot.week2.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService, ModelMapper modelMapper) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;

    // GetById
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getAllDepartment(@PathVariable Long id) {

        Department department = departmentService.getDepartmentById(id);

        return ResponseEntity.ok(modelMapper.map(department, DepartmentDto.class));

    }

    // Get
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<Department> list = departmentService.getAllDepartments();

        List<DepartmentDto> departmentDtosList = list.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class)).toList();

        return ResponseEntity.ok(departmentDtosList);
    }

    // Post
    @PostMapping
    public ResponseEntity<DepartmentDto> postDepartment(@RequestBody DepartmentDto departmentDto) {

        Department department = departmentService.saveDepartment(modelMapper.map(departmentDto, Department.class));

        return ResponseEntity.ok(modelMapper.map(department, DepartmentDto.class));

    }

    // Delete
    @DeleteMapping
    public ResponseEntity<DepartmentDto> deleteDepartment(@RequestBody DepartmentDto departmentDto) {

        boolean isDeleted = departmentService.deleteDepartment(modelMapper.map(departmentDto, Department.class));

        if (isDeleted) {
            return ResponseEntity.ok(departmentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Put
     @PutMapping
    public ResponseEntity<DepartmentDto> putDepartment(@RequestBody DepartmentDto departmentDto) {
        Department department = departmentService.putDepartment(modelMapper.map(departmentDto, Department.class));

        return ResponseEntity.ok(modelMapper.map(department, DepartmentDto.class));
     }


}
