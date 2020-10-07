package mvc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mvc.model.BoardDAO;
import mvc.model.BoardDTO;
import mvc.model.ReplyDTO;
import mvc.model.Search;

public class BoardService {
	private static BoardService service = new BoardService();
	private static BoardDAO dao;
	public static BoardService getInstance() {
		dao = BoardDAO.getInstance();
		return service;
	}
	
	public int insertBoardService(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		BoardDTO board = new BoardDTO();
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContents(request.getParameter("contents"));
		
		return dao.insertBoard(board);
	}
	
	public List<BoardDTO> listBoardService(HttpServletRequest request) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		Search search = new Search();
		HttpSession session = request.getSession();
	
		if(request.getParameterValues("area") != null) {
			session.removeAttribute("search");
			search.setArea(request.getParameterValues("area"));
			search.setSearchKey("%"+request.getParameter("searchKey")+"%");
			session.setAttribute("search", search);
		}
	
		else if(session.getAttribute("search") != null) {
			search = (Search)session.getAttribute("search");
		}	
		
		List<BoardDTO> list = dao.listBoard(search);
		return list;
	}
	
	public BoardDTO detailBoardService(int seq) throws Exception {
			   dao.cntBoard(seq);
		return dao.detailBoard(seq);
	}
	
	public int updateBoardService(BoardDTO board) throws Exception {
		return dao.updateBoard(board);
	}
	
	public int deleteBoardService(int seq) throws Exception {
		return dao.deleteBoard(seq);
	}
	
	public int insertReplyService(ReplyDTO reply) throws Exception {
		return dao.insertReply(reply);
	}
	
	public List<ReplyDTO> listReplyService(int seq) throws Exception {
		return dao.listReply(seq);
	}
	
	public int deleteReplyService(int r_no) throws Exception {
		return dao.deleteReply(r_no);
	}
	
	public int cntBoardService(int seq) throws Exception {
		return dao.cntBoard(seq);
	}
}
