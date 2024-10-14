package edu.du.sb1014_2.repository;

import edu.du.sb1014_2.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query("SELECT b.boardIdx, b.title, b.hitCnt, b.createdDatetime " +
            "FROM Board b WHERE b.deletedYn = 'N' ORDER BY b.boardIdx DESC")
    List<Board> selectBoardList();
}
