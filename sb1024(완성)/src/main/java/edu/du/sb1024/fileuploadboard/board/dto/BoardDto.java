package edu.du.sb1024.fileuploadboard.board.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class BoardDto {
	
	private int boardIdx;
	
	private String title;
	
	private String contents;
	
	private int hitCnt;
	
	private String creatorId;
	
	private String createdDatetime;
	
	private String updaterId;
	
	private String updatedDatetime;
	
	private List<BoardFileDto> fileList;
}
