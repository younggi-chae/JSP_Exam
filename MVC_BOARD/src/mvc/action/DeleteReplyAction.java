package mvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.service.BoardService;

public class DeleteReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		
		int r_no = Integer.parseInt(request.getParameter("r_no"));
		service.deleteReplyService(r_no);
		
		forward.setRedirect(false);
		forward.setPath("/detail.jsp");
		
		return forward;
	}

}
