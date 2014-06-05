package edu.arizona.training.service.impl;

import java.util.ArrayList;
import java.util.List;

import edu.arizona.training.bo.Bookmark;
import edu.arizona.training.dao.BookmarkDao;
import edu.arizona.training.service.BookmarkService;

public class BookmarkServiceImpl implements BookmarkService {

	private BookmarkDao bookmarkDao;
	
	public Bookmark findBookmark(int id) {
		return bookmarkDao.findBookmark(id);
	}
	
	public List<Bookmark> findAll() {
		return new ArrayList<>(bookmarkDao.findAll());
	}
	
	@Override
	public Bookmark createBookmark() {
		return new Bookmark();
	}

	@Override
	public Bookmark createBookmark(String name, String url) {
		return new Bookmark(name, url);
	}

	@Override
	public void updateBookmark(Bookmark bookmark) {
		bookmarkDao.updateBookmark(bookmark);
	}

	@Override
	public void deleteBookmark(Bookmark bookmark) {
		bookmarkDao.deleteBookmark(bookmark.getId());
	}

	@Override
	public void deleteBookmark(int bookmarkId) {
		bookmarkDao.deleteBookmark(bookmarkId);
	}

	public void setBookmarkDao(BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}

}
