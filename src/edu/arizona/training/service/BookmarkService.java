package edu.arizona.training.service;

import java.util.List;

import edu.arizona.training.bo.Bookmark;

public interface BookmarkService {

	public Bookmark createBookmark();
	
	public Bookmark createBookmark(String name, String url);
	
	public void updateBookmark(Bookmark bookmark);
	
	public void deleteBookmark(Bookmark bookmark);
	
	public void deleteBookmark(int bookmarkId);
	
	public Bookmark findBookmark(int id);
	
	public List<Bookmark> findAll();
	
}
