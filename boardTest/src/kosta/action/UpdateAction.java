package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.service.BoardService;

public class UpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		
		Board board = new Board();
		int seq = Integer.parseInt(request.getParameter("seq"));
		board.setSeq(seq);		
		board.setTitle(request.getParameter("title"));
		board.setContents(request.getParameter("contents"));
		service.updateBoardService(board);
		
		forward.setRedirect(true);
		forward.setPath("listAction.do");
		return forward;
	}

}
