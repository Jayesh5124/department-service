package com.example.demo.service;

import java.util.List;

import com.example.demo.model.DepartmentPojo;

public interface DepartmentService {

	List<DepartmentPojo> getAlldepts();
	
	DepartmentPojo getDepartment(long deptId);
	
	DepartmentPojo addDepartmentPojo(DepartmentPojo newDepartmentPojo);
	
	DepartmentPojo updateDeptPojo(DepartmentPojo eDepartmentPojo);
	
	void deleteDept(long deptId);

	
	

	
}
