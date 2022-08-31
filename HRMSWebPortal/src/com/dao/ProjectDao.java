package com.dao;

import java.util.List;

import com.bean.Project;

public interface ProjectDao {
	void add(Project project);
	void updateProjectAccept(int id, char accept);
	List<Project> list();
	List<Project> getProjectByEmployee(int id);
}