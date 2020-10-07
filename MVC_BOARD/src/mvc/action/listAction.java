package mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.BoardDTO;
import mvc.service.BoardService;

public class listAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		
		List<BoardDTO> list = service.listBoardService(request);		
		request.setAttribute("list", list);
		
		forward.setRedirect(false);
		forward.setPath("/list.jsp");
		return forward;
	}

}
