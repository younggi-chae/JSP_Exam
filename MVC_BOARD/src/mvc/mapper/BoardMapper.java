package mvc.mapper;

import java.util.List;

import mvc.model.BoardDTO;
import mvc.model.ReplyDTO;
import mvc.model.Search;

public interface BoardMapper {
	int insertBoard(BoardDTO board);
	List<BoardDTO> listBoard(Search search);
	BoardDTO detailBoard(int seq);
	int updateBoard(BoardDTO board);
	int deleteBoard(int seq);
	
	int insertReply(ReplyDTO reply);
	List<ReplyDTO> listReply(int seq);
	int deleteReply(int r_no);
	
	int cntBoard(int seq);
}
