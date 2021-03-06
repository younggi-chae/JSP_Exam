package mvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.ReplyDTO;
import mvc.service.BoardService;

public class InsertReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		
		ReplyDTO reply = new ReplyDTO();
		reply.setR_writer(request.getParameter("r_writer"));
		reply.setR_contents(request.getParameter("r_contents"));
		reply.setSeq(Integer.parseInt(request.getParameter("seq")));
		service.insertReplyService(reply);
		
		forward.setRedirect(true);
		forward.setPath("detailAction.do?seq=" + request.getParameter("seq"));
		return forward;
	}

}
