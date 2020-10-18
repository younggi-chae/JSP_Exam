package kosta.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import kosta.model.Board;
import kosta.model.Reply;
import kosta.model.Search;

public interface BoardMapper {
	int insertBoard(Board board);
	List<Board> listBoard(Search search, RowBounds row);
	Board detailBoard(int seq);
	int updateBoard(Board board);
	int deleteBoard(int seq);
	
	int insertReply(Reply reply);
	List<Reply> listReply(int seq);
	
	int countBoard(Search search);
}
