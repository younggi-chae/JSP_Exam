package kosta.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import kosta.model.Board;
import kosta.model.BoardDAO;
import kosta.model.ListModel;
import kosta.model.Reply;

public class BoardService {
	private static BoardService service = new BoardService();
	private static BoardDAO dao;
	private static final int PAGE_SIZE = 2;
	
	public static BoardService getInstance() {
		 dao = BoardDAO.getInstance();
		return service;
	}
	
	public int insertBoardService(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		Board board = new Board();
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContents(request.getParameter("contents"));
		
		return dao.insertBoard(board);
	}
	
public ListModel listBoardService(HttpServletRequest request)throws Exception{	
		
		int totalCount = dao.countBoard();	
		
		int totalPageCount = totalCount/PAGE_SIZE;
		if(totalCount%PAGE_SIZE > 0) {
			totalPageCount++;
		}
		
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		int requestPage = Integer.parseInt(pageNum);		
		int startPage = requestPage - (requestPage -1) % 5;
		
		
		int endPage = startPage + 4;
		if(endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		
		int startRow = (requestPage - 1) * PAGE_SIZE;
				
		List<Board> list = dao.listBoard(startRow);
		ListModel listModel =
				new ListModel(list, requestPage, totalPageCount, startPage, endPage);
		
		return listModel;
	}
	
	public Board detailBoardService(int seq) throws Exception {
		return dao.detailBoard(seq);
	}
	
	public int updateBoardService(Board board) throws Exception {
		return dao.updateBoard(board);
	}
	
	public int deleteBoardService(int seq) throws Exception {
		return dao.deleteBoard(seq);
	}
	
	public int insertReplyService(Reply reply) throws Exception {
		return dao.insertReply(reply);
	}
	
	public List<Reply> listReplyService(int seq) throws Exception {
		return dao.listReply(seq);
	}
}
