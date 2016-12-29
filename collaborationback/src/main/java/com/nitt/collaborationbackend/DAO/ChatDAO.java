package com.nitt.collaborationbackend.DAO;

import java.util.List;

import com.nitt.collaborationbackend.model.Chat;

public interface ChatDAO {
	
	public boolean save(Chat chat);
	
	public Chat get(String id);
	
	public boolean Delete(Chat chat);
		
	public List<Chat> list();

}
