package com.dao;

import java.util.List;

import com.bean.EmployeeReg;

public interface EmployeeRegDao {
	void add(EmployeeReg empReg);
	List<EmployeeReg> list();
}
