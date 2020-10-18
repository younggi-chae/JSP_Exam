package kosta.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.action.Action;
import kosta.action.ActionForward;
import kosta.action.DeleteAction;
import kosta.action.DetailAction;
import kosta.action.InsertAction;
import kosta.action.InsertActionForm;
import kosta.action.ListAction;
import kosta.action.UpdateActionForm;
import kosta.action.UpdateAction;
import kosta.action.InsertReplyAction;

@WebServlet("/board/*") // *은 여러 개의 url 요청을 받기 위해
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyController() {
		super();
	}

	// get과 post 따로따로 만들어 주지 않기 위해 doProcess 생성
	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// URL 경로 식별
		// insertFormAction.do, insertAction.do, listAction.do, detailAction.do
		// http://localhost:8085/MVC/board/insertFormAction.do
		String requestURI = request.getRequestURI();
		// /MVC/board/insertFormAction.do
		String contextPath = request.getContextPath();
		// insertFormAction.do
		String command = requestURI.substring(contextPath.length() + 7);

		Action action = null;
		ActionForward forward = null;

		if(command.equals("insertActionForm.do")) {
    		action = new InsertActionForm();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}else if(command.equals("insertAction.do")) {
    		action = new InsertAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("listAction.do")) {
			action = new ListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("detailAction.do")) {
			action = new DetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("updateActionForm.do")) {
			action = new UpdateActionForm();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("updateAction.do")) {
			action = new UpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("deleteAction.do")) {
			action = new DeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if (command.equals("InsertReplyAction.do")) {
			action = new InsertReplyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);

			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
