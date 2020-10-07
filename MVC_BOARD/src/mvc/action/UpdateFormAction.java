package mvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.BoardDTO;
import mvc.service.BoardService;


public class UpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		
		BoardDTO board = new BoardDTO();
		int seq = Integer.parseInt(request.getParameter("seq"));
		board = service.detailBoardService(seq);
		request.setAttribute("board", board);
		
		forward.setRedirect(false);
		forward.setPath("/updateForm.jsp");
		return forward;
	}

}
