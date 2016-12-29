package com.nitt.collaborationbackend.DAO;

import java.util.List;

import com.nitt.collaborationbackend.model.Blog;

public interface BlogDAO {
	
	public boolean save(Blog blog);
	
	public Blog get(String id);
	
	public boolean Delete(Blog blog);
	
	public boolean Update(Blog blog);
	
	public List<Blog> list();

}
