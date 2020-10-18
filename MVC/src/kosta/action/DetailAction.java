package kosta.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.model.BoardService;
import kosta.model.Reply;

public class DetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
			
		int seq = Integer.parseInt(request.getParameter("seq"));
		List<Reply> replys = service.listReplyService(seq);
		
		Board board = service.detailBoardService(seq);		
		request.setAttribute("board", board);
		request.setAttribute("replys", replys);
		
		forward.setRedirect(false);
		forward.setPath("/detail.jsp");
		return forward;
	}
}
