package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.example.demo.model.DepartmentPojo;
import com.example.demo.model.EmployeePojo;
import com.example.demo.service.DepartmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	@Autowired
	DepartmentService service;
	
	public static final Logger LOG=LoggerFactory.getLogger(DepartmentController.class);
	
	@GetMapping("")
	public List<DepartmentPojo>getAllDepts()
	{
		LOG.info("in getAllDepts()");
		return service.getAlldepts();
	}
	
	@GetMapping("/{did}")
	@CircuitBreaker(name="ciremp", fallbackMethod = "empFallBack")
	public DepartmentPojo getDepartment(@PathVariable("did")long deptId)
	{
		LOG.info("in getDepartment()");
		DepartmentPojo deptPojo=service.getDepartment(deptId);
		RestClient restClient = RestClient.create();
		List<EmployeePojo> allEmps = restClient
		.get()
		.uri("http://employee-sr:8082/api/employees/departments/"+deptId)
		.retrieve()
		.body(List.class);
         deptPojo.setAllEmployees(allEmps);
		return deptPojo;
	}
	
	public DepartmentPojo empFallBack()
	{
		return new DepartmentPojo(0,"fallback",null);
	}
	
	
	@PostMapping("")
	public DepartmentPojo addDepartmentPojo(@RequestBody DepartmentPojo newDepartmentPojo)
	{
		LOG.info("in addDepartmentPojo()");
		service.addDepartmentPojo(newDepartmentPojo);
		return newDepartmentPojo;
	}
	
	@PutMapping()
	public DepartmentPojo updateDeptPojo(@RequestBody DepartmentPojo eDepartmentPojo)
	{
		LOG.info("in updateDeptPojo()");
		service.updateDeptPojo(eDepartmentPojo);
		return eDepartmentPojo;
	}
	
	@DeleteMapping("{did}")
	public void deleteDept(@PathVariable("did")long deptId)
	{
		LOG.info("in deleteDept()");
		service.deleteDept(deptId);
	}

}
