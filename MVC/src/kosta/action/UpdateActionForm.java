package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.model.BoardService;

public class UpdateActionForm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
			
		int seq = Integer.parseInt(request.getParameter("seq"));
		   
	   		
		Board board = service.detailBoardService(seq);		
		request.setAttribute("board", board);		
		
		forward.setRedirect(false);
		forward.setPath("/updateForm.jsp");
		return forward;
	}
	
	
}
