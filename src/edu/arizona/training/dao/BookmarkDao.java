package edu.arizona.training.dao;

import java.util.Collection;

import edu.arizona.training.bo.Bookmark;

public interface BookmarkDao {

	public Collection<Bookmark> findAll();
	
	public Bookmark findBookmark(int id);
	
	public void updateBookmark(Bookmark bookmark);
	
	public void deleteBookmark(int id);
	
}
