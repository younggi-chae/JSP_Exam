package kosta.model;

import java.io.File;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardService {
	private static  BoardService service = new BoardService();
	private static BoardDAO2 dao;
	private static final int PAGE_SIZE = 2;
	
	
	public static BoardService getInstance() {
		dao = BoardDAO2.getInstance();
		return service;
	}
	
	public int insertBoardService(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		//파일업로드(경로, 파일크기, 인코딩, 파일이름 중첩 정책)
		String uploadPath = request.getSession().getServletContext().getRealPath("upload");
		int size = 20 * 1024 * 1024; // 20MB
		
		MultipartRequest multi = 
				new MultipartRequest(request, uploadPath, size, 
						"utf-8", new DefaultFileRenamePolicy());		
		
		Board board = new Board();
		board.setTitle(multi.getParameter("title"));
		board.setWriter(multi.getParameter("writer"));
		board.setContents(multi.getParameter("contents"));
		board.setFname("");
		
		//파일업로드 DB(파일이름 저장)
		if(multi.getFilesystemName("fname") != null) {
			String fname = (String)multi.getFilesystemName("fname");
			board.setFname(fname);
			
			//썸네일 이미지(gif, jpg) => aa.gif , aa.jpg...		
			String pattern = fname.substring(fname.indexOf(".") +1); 
			String head = fname.substring(0, fname.indexOf("."));
			
			//원본 File 객체
			String imagePath = uploadPath + "\\" + fname;
			File src = new File(imagePath);
			
			//썸네일 File 객체
			String thumPath = uploadPath + "\\" + head + "_small." + pattern;
			File dest = new File(thumPath);
			
			if(pattern.equals("gif") || pattern.equals("jpg")) {
				ImageUtil.resize(src, dest, 100, ImageUtil.RATIO);
			}
			
		}
		return dao.insertBoard(board);
	}
	
	public ListModel listBoardService(HttpServletRequest request) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		Search search = new Search();
		HttpSession session = request.getSession();
		
		//검색할 경우
		if(request.getParameterValues("area") != null) {
			session.removeAttribute("search");
			search.setArea(request.getParameterValues("area"));
			search.setSearchKey("%" + request.getParameter("searchKey") + "%");
			session.setAttribute("search", search);
		}
		
		//검색 후 페이지를 클릭할 경우
		else if(session.getAttribute("search") != null) { // 검색한 이우, 새로운 검색x
			search = (Search)session.getAttribute("search");
		}
		
		
		//페이징 처리
		//페이지당 글갯수, 총 글갯수, 총 페이지 수, 현재 페이지
		
		//총 글갯수
		int totalCount = dao.countBoard(search);
		
		//총 페이지 수
		int totalPageCount = totalCount/PAGE_SIZE;
		if(totalCount % PAGE_SIZE > 0) {
			totalPageCount++;
		}		
		//현재 페이지
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}		
		int requestPage = Integer.parseInt(pageNum);
		
		// startPage = 현재 페이지 - (현재 페이지 -1) % 5  -> 5는 구간 페이지 수
		int startPage = requestPage - (requestPage -1) % 5;
		
		//endPage -> 총 페이지 수를 넘길 수 없음
		int endPage = startPage +4;
		if(endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		//startRow = (현재 페이지 -1) * 페이지당 글갯수,  페이지 안에 start와 end
		int startRow = (requestPage -1) * PAGE_SIZE;
		
		
		List<Board> list = dao.listBoard(search, startRow);
		ListModel listModel = new ListModel(list, requestPage, totalPageCount, startPage, endPage);		
		
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
