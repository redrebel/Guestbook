package kame.chap13.dao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class MessageDaoProviderInit extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		String dbms = config.getInitParameter("dbms");
		if(dbms != null) {
			MessageDaoProvider.getInstance().setDbms(dbms);
		}
	}
}