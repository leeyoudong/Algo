package com.puercha.algo.challenge.vo;

import java.util.Date;

import lombok.Data;

/**
 * @author Yongmin
 * 도전문제 결과 VO
 * 
 */
@Data
public class ChallengeResultVO {

	//결과번호
	private long resultNum;
	
	//제출한답
	private String code;
	
	//메모리 사용량
	private long usingMemory;
	
	//수행시간
	private long processing_time;
	
	//도전과제번호
	private long cNum;
	
	//유저 번호
	private long userNum;
	
	//결과 상태
	private char status;
	
	//결과에 대한 코멘트
	private String resultComment;
	
	//생성 일시
	private Date createdAt;
	
	//수정 일시
	private Date updatedAt;
	
}
