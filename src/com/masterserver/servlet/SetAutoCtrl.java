package com.masterserver.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.masterserver.core.MasterServer;

/**
 * Servlet implementation class SetAutoCtrl
 */
@WebServlet("/SetAutoCtrl")
public class SetAutoCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetAutoCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("offhour") != null && !request.getParameter("offhour").equals("") && request.getParameter("offmin") != null && !request.getParameter("offmin").equals("")){
			MasterServer.instance.setOffMin(Integer.valueOf(request.getParameter("offmin")));
			MasterServer.instance.setOffHour(Integer.valueOf(request.getParameter("offhour")));
		}
		
		if(request.getParameter("onhour") != null && !request.getParameter("onhour").equals("") && request.getParameter("onmin") != null && !request.getParameter("onmin").equals("")){
			MasterServer.instance.setOnMin(Integer.valueOf(request.getParameter("onmin")));
			MasterServer.instance.setOnHour(Integer.valueOf(request.getParameter("onhour")));
		}
		response.sendRedirect("/masterServerWeb/serverStatus.jsp");
	}

}
