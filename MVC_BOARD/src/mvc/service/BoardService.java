package mvc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.model.BoardDAO;
import mvc.model.BoardDTO;
import mvc.model.ListModel;
import mvc.model.ReplyDTO;
import mvc.model.Search;

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
		
		String uploadPath = request.getSession().getServletContext().getRealPath("upload");
		int size = 20 * 1024 * 1024;
		
		MultipartRequest multi = 
				new MultipartRequest(request, uploadPath, size, 
						"utf-8", new DefaultFileRenamePolicy());
		
		BoardDTO board = new BoardDTO();
		board.setTitle(multi.getParameter("title"));
		board.setWriter(multi.getParameter("writer"));
		board.setContents(multi.getParameter("contents"));
		board.setFname("");
		
		if(multi.getFilesystemName("fname") != null) {
			String fname = (String)multi.getFilesystemName("fname");
			board.setFname(fname);
		}
		
		return dao.insertBoard(board);
	}
	
	public ListModel listBoardService(HttpServletRequest request) throws Exception {
		
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
			System.out.println(session.getAttribute("search"));
			search = (Search)session.getAttribute("search");
		}
		
		int totalCount = dao.countBoard(search);
		int totalPageCount = totalCount / PAGE_SIZE;
		if(totalCount % PAGE_SIZE > 0) {
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
		
		List<BoardDTO> list = dao.listBoard(search, startRow);
		ListModel listModel = new ListModel(list, requestPage, totalPageCount, startPage, endPage);
		
		return listModel;
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
