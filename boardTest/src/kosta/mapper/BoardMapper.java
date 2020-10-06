package kosta.mapper;

import java.util.List;
import org.apache.ibatis.session.RowBounds;

import kosta.model.Board;
import kosta.model.Reply;

public interface BoardMapper {
	int insertBoard(Board board);
	List<Board> listBoard(RowBounds row);
	Board detailBoard(int seq);
	int updateBoard(Board board);
	int deleteBoard(int seq);
	
	int insertReply(Reply reply);
	List<Reply> listReply(int seq);
	int countBoard();
}











