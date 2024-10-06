package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DepartmentRepository;
import com.example.demo.entity.DepartmentEntity;

import com.example.demo.model.DepartmentPojo;;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository repo;
	
	@Override
	public List<DepartmentPojo>getAlldepts()
	{
		List<DepartmentEntity>alldeptsentity=repo.findAll();
		List<DepartmentPojo>alldepts=new ArrayList<>();
		alldeptsentity.stream().forEach((eachDepEntity)->
		{
			DepartmentPojo deptPojo=new DepartmentPojo();
			BeanUtils.copyProperties(eachDepEntity,deptPojo);
			alldepts.add(deptPojo);
			
			
		
		});
		return alldepts;
	}
	
	@Override
	public DepartmentPojo getDepartment(long deptId)
	{
		Optional<DepartmentEntity>de=repo.findById(deptId);
		DepartmentPojo dpojo=null;
		if(de.isPresent())
		{
			dpojo=new DepartmentPojo();
			BeanUtils.copyProperties(de.get(), dpojo);
		}
		return dpojo;
	}
		
	
	@Override
	public DepartmentPojo addDepartmentPojo(DepartmentPojo newDepartmentPojo)
	{
		DepartmentEntity deptentity=new DepartmentEntity();
		BeanUtils.copyProperties(newDepartmentPojo, deptentity);
		repo.saveAndFlush(deptentity);
		return newDepartmentPojo;
	}
	
	@Override
	public DepartmentPojo updateDeptPojo(DepartmentPojo eDepartmentPojo)
	{
		DepartmentEntity de=new DepartmentEntity();
		BeanUtils.copyProperties(eDepartmentPojo, de);
		repo.saveAndFlush(de);
		return eDepartmentPojo;
	}
	
	@Override
	public void deleteDept(long deptId)
	{
		repo.deleteById(deptId);
	}
	
	
	
}
