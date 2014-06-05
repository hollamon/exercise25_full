package edu.arizona.training.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.kuali.kfs.context.SpringContext;

import edu.arizona.training.bo.Bookmark;
import edu.arizona.training.service.BookmarkService;

public class BookmarkAction extends DispatchAction {

	private static final String FORWARD_DISPLAY = "display";
	
	public ActionForward display(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// we do this here just so a new set can be instantiated if hitting this for the first time
		List<Bookmark> bookmarks = getBookmarkService().findAll();
		request.setAttribute("bookmarks", bookmarks);
		return mapping.findForward(FORWARD_DISPLAY);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idToEdit = Integer.parseInt(request.getParameter("id"));
		Bookmark bookmarkToEdit = getBookmarkService().findBookmark(idToEdit);
		request.setAttribute("bookmarkToEdit", bookmarkToEdit);
		return mapping.findForward("edit");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idToDelete = Integer.parseInt(request.getParameter("id"));
		Bookmark bookmarkToDelete = getBookmarkService().findBookmark(idToDelete);
		request.setAttribute("bookmarkToDelete", bookmarkToDelete);
		return mapping.findForward("delete");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookmarkForm bForm = (BookmarkForm) form;
		BookmarkService bookmarkService = (BookmarkService)SpringContext.getBean("bookmarkService");

		String action = bForm.getAction();
		if ("add".equalsIgnoreCase(action)) {
			Bookmark newBookmark = bookmarkService.createBookmark(bForm.getName(), bForm.getUrl());
			bookmarkService.updateBookmark(newBookmark);
		}
		
		if ("edit".equalsIgnoreCase(action)) {
			int bookmarkIdToEdit = Integer.parseInt(bForm.getId());
			Bookmark bookmarkToEdit = bookmarkService.findBookmark(bookmarkIdToEdit);
			bookmarkToEdit.setName(bForm.getName());
			bookmarkToEdit.setUrl(bForm.getUrl());
			bookmarkService.updateBookmark(bookmarkToEdit);
		}

		if ("delete".equalsIgnoreCase(action)) {
			int bookmarkIdToDelete = Integer.parseInt(bForm.getId());
			bookmarkService.deleteBookmark(bookmarkIdToDelete);
		}
		
		bForm.reset(mapping, request);
		
		response.sendRedirect("bookmarks.do?methodToCall=display");
		return null;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.sendRedirect("bookmarks.do?methodToCall=display");
		return null;
	}

	private BookmarkService getBookmarkService() {
		return (BookmarkService)SpringContext.getBean("bookmarkService");
	}
	
	/*
	protected void saveBookmarks(List<Bookmark> bookmarks, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute(KEYNAME, bookmarks);
	}
	
	protected List<Bookmark> getBookmarks(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if ( session.getAttribute(KEYNAME) == null ) {
			List<Bookmark> bookmarks = new ArrayList<Bookmark>();
			bookmarks.add(new Bookmark(1, "Google","http://www.google.com"));
			bookmarks.add(new Bookmark(2, "Arizona","http://www.arizona.edu"));
			session.setAttribute(KEYNAME, bookmarks);
		}
		return (List<Bookmark>) session.getAttribute(KEYNAME);
	}

	protected Bookmark findBookmarkById(int id, List<Bookmark> bookmarks) {
		for (Bookmark bookmark : bookmarks) {
			if (id == bookmark.getId()) {
				return bookmark;
			}
		}
		return null;
	}
	*/
	
}
