package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentPojo {

	private long deptId;
	private String name;
	
	private List<EmployeePojo> AllEmployees;
	
	
	
	
}
