package edu.du.sb1014_2.service;


import edu.du.sb1014_2.entity.Board;
import edu.du.sb1014_2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public List<Board> selectBoardList() throws Exception {
		return boardRepository.selectBoardList();
	}
	
	@Override
	public void insertBoard(Board board) throws Exception {
//		boardRepository.insertBoard(board);
	}

	@Override
	public Board selectBoardDetail(int boardIdx) throws Exception{
//		Boardo board = boardRepository.selectBoardDetail(boardIdx);
//		boardRepository.updateHitCount(boardIdx);
		
//		return board;
		return null;
	}
	
	@Override
	public void updateBoard(Board board) throws Exception {
//		boardRepository.updateBoard(board);
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
//		boardRepository.deleteBoard(boardIdx);
	}
}	

