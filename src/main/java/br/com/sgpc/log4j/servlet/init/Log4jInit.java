package br.com.sgpc.log4j.servlet.init;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Log4jInit extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		final String prexix = super.getServletContext().getRealPath( System.getProperty("file.separator") );
		super.doGet(req, resp);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3497980716863546711L;
	

	  /*public
	  void init() {
	    String prefix =  getServletContext().getRealPath("/");
	    String file = getInitParameter("log4j-init-file");
	    // if the log4j-init-file is not set, then no point in trying
	    if(file != null) {
	      PropertyConfigurator.configure(prefix+file);
	    }
	  }

	  public
	  void doGet(HttpServletRequest req, HttpServletResponse res) {
	  }*/
}
