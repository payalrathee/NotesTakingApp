package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import beans.Note;
import connections.SessionFactoryProvider;

public class UpdateNoteServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		SessionFactory factory=SessionFactoryProvider.provideFactory();
		Session session=factory.openSession();
		Transaction t=session.beginTransaction();
		
		Note note=(Note)session.get(Note.class, id);
		note.setTitle(title);
		note.setContent(content);
		note.setDate(new Date());
		
		t.commit();
		session.close();
		response.sendRedirect("showNotes.jsp");
	}

}
