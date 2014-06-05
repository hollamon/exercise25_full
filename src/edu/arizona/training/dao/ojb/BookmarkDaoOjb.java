package edu.arizona.training.dao.ojb;

import java.util.Collection;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport;

import edu.arizona.training.bo.Bookmark;
import edu.arizona.training.dao.BookmarkDao;

@Transactional
public class BookmarkDaoOjb extends PersistenceBrokerDaoSupport implements BookmarkDao {

	public Collection<Bookmark> findAll() {
		Criteria criteria = new Criteria();
		Query query = QueryFactory.newQuery(Bookmark.class, criteria);
		return getPersistenceBrokerTemplate().getCollectionByQuery(query);
	}
	
	@Override
	public Bookmark findBookmark(int id) {
		Criteria criteria = new Criteria();
		criteria.addEqualTo("id", id);
		Query query = QueryFactory.newQuery(Bookmark.class, criteria);
		return (Bookmark)getPersistenceBrokerTemplate().getObjectByQuery(query);
	}

	@Override
	public void updateBookmark(Bookmark bookmark) {
		getPersistenceBrokerTemplate().store(bookmark);
	}

	@Override
	public void deleteBookmark(int id) {
		Criteria criteria = new Criteria();
		criteria.addEqualTo("id", id);
		Query query = QueryFactory.newQuery(Bookmark.class, criteria);
		getPersistenceBrokerTemplate().deleteByQuery(query);
	}

}
