package mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.BoardDTO;
import mvc.model.ReplyDTO;
import mvc.service.BoardService;



public class DetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		
		BoardDTO board = new BoardDTO();
		List<ReplyDTO> replys = null;
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		board = service.detailBoardService(seq);
		replys = service.listReplyService(seq);		
		request.setAttribute("board", board);
		request.setAttribute("replys", replys);		
		
		forward.setRedirect(false);
		forward.setPath("/detail.jsp");
		return forward;
	}

}
