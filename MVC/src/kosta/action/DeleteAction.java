package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.BoardService;

public class DeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		service.deleteBoardService(seq);
		
		forward.setRedirect(true);
		forward.setPath("listAction.do");
		
		return forward;
	}

}
