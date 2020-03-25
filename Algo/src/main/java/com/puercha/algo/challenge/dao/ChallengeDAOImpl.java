package com.puercha.algo.challenge.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.puercha.algo.challenge.vo.ChallengeCaseVO;
import com.puercha.algo.challenge.vo.ChallengeResultVO;
import com.puercha.algo.challenge.vo.ChallengeVO;
/**
 * 도전과제 DAO 기능을 구현함
 * ChallengeDAO 인터페이스를 구현하는 클래스
 * @author Hyeonuk
 * 
 */
@Repository
public class ChallengeDAOImpl implements ChallengeDAO {
	private static final Logger logger = LoggerFactory.getLogger(ChallengeDAOImpl.class);
	
	@Inject
	SqlSession sqlSession;
	/* Create */	
	/**
	 * 도전과제 생성
	 * @param challenge 생성할 record의 VO
	 * @return 성공 시 1 반환
	 */
	@Override
	public int insertChallenge(ChallengeVO challenge) {
		logger.info("insertChallenge():"+challenge.toString());
		return sqlSession.insert("mappers.ChallengeDAO-mapper.insertChallenge",challenge);
	}

	
	
	/**
	 * 도전과제 결과 생성
	 * @param result 생성할 도전과제 결과 VO
	 * @return
	 */
	@Override
	public int insertChallengeResult(ChallengeResultVO result) {
		logger.info("insertChallengeResult(ChallengeResultVO result):"+result.toString());
		return sqlSession.insert("mappers.ChallengeDAO-mapper.insertChallengeResult",result);
	}

	/**
	 * 도전과제 테스트 케이스 생성
	 * @param testCase 새로운 테스트 케이스
	 * @return 성공시 1
	 */
	@Override
	public int insertTestCase(ChallengeCaseVO testCase) {
		logger.info("insertTestCase(ChallengeCaseVO testCase):"+testCase.toString());
		return sqlSession.insert("mappers.ChallengeDAO-mapper.insertTestCase", testCase);
	}



	/* Read */
	/**
	 * 도전과제 row 1개 열람
	 * @param cNum 도전과제 번호
	 * @return 도전과제 VO
	 */
	@Override
	public ChallengeVO selectOne(long cNum) {
		Map<String,Object> params = new HashMap<>();
		params.put("cNum", cNum);
		logger.info("selectOne(long cNum):"+cNum);
		return sqlSession.selectOne("mappers.ChallengeDAO-mapper.selectOne",params);
	}

	
	/**
	 * 도전과제 목록 검색
	 * @param startRowNum 시작 row
	 * @param endRowNum 끝 row
	 * @param type 검색 타입
	 * @param keyword 검색 keyword
	 * @return 도전과제VO의 리스트 객체
	 */
	@Override
	public List<ChallengeVO> selectAllChallenge(long startRowNum, long endRowNum, String type, String keyword) {
		logger.info("selectAllChallenge(long startRowNum, long endRowNum, String type, String keyword):"+startRowNum+", "+endRowNum+", "+ type+", "+keyword);
		Map<String,Object> params = new HashMap<>();
		params.put("startRowNum", startRowNum);
		params.put("endRowNum", endRowNum);
		params.put("type", type);
		params.put("keyword", keyword);
		return sqlSession.selectList("mappers.ChallengeDAO-mapper.selectAllChallenge", params);
	}
	
	
	
	/**
	  * 도전과제 결과 보기 
	  * @param resultNum 결과 번호
	  * @return 도전과제 결과VO
	  */
	@Override
	public ChallengeResultVO selectOneResult(long resultNum) {
		logger.info("selectOneResult(long resultNum):"+resultNum);
		return sqlSession.selectOne("mappers.ChallengeDAO-mapper.selectAllChallenge",resultNum);
	}
	


	/**
	  * 전체 도전과제 결과 보기
	  * @param startRowNum 시작 행
	  * @param endRowNum 끝 행
	  * @return 도전과제결과 List
	  */
	@Override
	public List<ChallengeResultVO> selectAllResult(long startRowNum, long endRowNum) {
		logger.info("selectAllResult(long startRowNum, long endRowNum):"+startRowNum +", " + endRowNum);
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("startRowNum", startRowNum);
		params.put("endRowNum", endRowNum);
		return sqlSession.selectList("mappers.ChallengeDAO-mapper.selectAllResult",params);
	}


	/**
	  * 문제 내 전체 도전과제 결과 보기
	  * @param startRowNum 시작 행
	  * @param endRowNum 끝 행
	  * @param cNum 도전과제 번호
	  * @return 도전과제결과 List
	  */
	@Override
	public List<ChallengeResultVO> selectAllResult(long startRowNum, long endRowNum, long cNum) {
		logger.info("selectAllResult(long startRowNum, long endRowNum, long cNum): "+startRowNum+", "+endRowNum+", "+cNum);
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("startRowNum", startRowNum);
		params.put("endRowNum", endRowNum);
		params.put("cNum", cNum);
		return sqlSession.selectList("mappers.ChallengeDAO-mapper.selectAllResult", params);
	}


	/**
	 * 문제의 내가 제출한 도전과제 결과 보기
	 * @param startRowNum 시작 행
	 * @param endRowNum 끝 행
	 * @param cNum 도전과제 번호
	 * @param userNum 사용자 번호
	 * @return 도전과제결과 List
	 */
	@Override
	public List<ChallengeResultVO> selectAllResult(long startRowNum, long endRowNum, long cNum, long userNum) {
		logger.info("selectAllResult(long startRowNum, long endRowNum, long cNum, long userNum): "+startRowNum+", "+endRowNum+", "+cNum+", " +userNum);		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("startRowNum", startRowNum);
		params.put("endRowNum", endRowNum);
		params.put("cNum", cNum);
		params.put("userNum", userNum);
		return sqlSession.selectList("mappers.ChallengeDAO-mapper.selectAllResult", params);
	}

	
	/**
	 * 도전과제의 결과 중 가장 최근 과제 결과를 가져옴
	 * @param cNum 도전과제 번호
	 * @param userNum 사용자 번호
	 * @return 가장최근 결과
	 */
	@Override
	public ChallengeResultVO getLastResult(long cNum, long userNum) {
		logger.info("getLastResult(long cNum, long userNum): "+cNum +", "+ userNum);
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("cNum", cNum);
		params.put("userNum", userNum);
		return sqlSession.selectOne("mappers.ChallengeDAO-mapper.getLastResult", params);
	}


	/**
	 * 모든 도전과제의 모든 결과 개수를 가져옴
	 * @return 결과개수
	 */
	@Override
	public long getCountTotalResult() {
		logger.info("getCountTotalResult(): ");
		return sqlSession.selectOne("mappers.ChallengeDAO-mapper.getCountTotalResult");
	}


	/**
	 * 도전과제의 모든 결과 개수를 가져옴
	 * @param cNum
	 * @return 결과 개수
	 */
	@Override
	public long getCountTotalResult(long cNum) {
		logger.info("getCountTotalResult(): "+ cNum);
		return sqlSession.selectOne("mappers.ChallengeDAO-mapper.getCountTotalResult",cNum);
	}


	/**
	 * 도전과제내 모든 케이스를 가져옴 
	 * @param cNum 도전과제 번호
	 * @return 테스트케이스 VO의 리스트
	 */
	@Override
	public List<ChallengeCaseVO> selectAllCase(long cNum) {
		logger.info("selectAllCase(long cNum): "+ cNum);
		return sqlSession.selectOne("mappers.ChallengeDAO-mapper.selectAllCase",cNum);
	}



	/**
	 * 케이스 번호로 케이스 데이터 하나 열람 
	 * @param caseNum 케이스 번호
	 * @return 테스트케이스VO
	 */
	@Override
	public ChallengeCaseVO selectOneCase(long caseNum) {
		logger.info("selectOneCase(long caseNum): "+caseNum);
		return sqlSession.selectOne("mappers.ChallengeDAO-mapper.selectOneCase",caseNum);
	}



	


	/* Update */
	/**
	 * 도전과제 변경
	 * @param challenge 수정된 VO
	 * @return 성공 시 1
	 */
	@Override
	public int updateChallenge(ChallengeVO challenge) {
		logger.info("updateChallenge(ChallengeVO challenge): "+challenge);		
		return sqlSession.update("mappers.ChallengeDAO-mapper.updateChallenge",challenge);
	}

	/**
	 * 도전과제 결과 변경
	 * @param result
	 * @return 성공 시 1
	 */
	@Override
	public int updateChallengeResult(ChallengeResultVO result) {
		logger.info("updateChallengeResult(ChallengeResultVO result): "+result);
		return sqlSession.update("mappers.ChallengeDAO-mapper.updateChallengeResult",result);
	}
	
	/**
	 * 도전과제 테스트케이스 변경
	 * @param testCase  수정된 VO
	 * @return 성공 시 1
	 */
	@Override
	public int updateChallengeCase(ChallengeCaseVO testCase) {
		logger.info("updateChallengeCase(ChallengeCaseVO testCase): "+testCase);
		return sqlSession.update("mappers.ChallengeDAO-mapper.updateChallengeCase",testCase);
	}



	/* Delete */
	/**
	 * 도전과제 삭제
	 * @param cNum 도전과제 번호
	 * @return 성공 시 1
	 */
	@Override
	public int deleteChallenge(long cNum) {
		logger.info("deleteChallenge(long cNum): "+cNum);
		return sqlSession.delete("mappers.ChallengeDAO-mapper.deleteChallenge",cNum);
	}
	
	/**
	 * 도전과제 결과 삭제
	 * @param resultNum
	 * @return 성공 시 1
	 */
	@Override
	public int deleteChallengeReseult(long resultNum) {
		logger.info("deleteChallengeReseult(long resultNum): "+resultNum);
		return sqlSession.delete("mappers.ChallengeDAO-mapper.deleteChallengeReseult",resultNum);
	}

	
	/**
	 * 도전과제 테스트케이스 삭제
	 * @param caseNum 케이스 번호
	 * @return 성공 시 1
	 */
	@Override
	public int deleteChallengeCase(long caseNum) {
		logger.info("deleteChallengeCase(long caseNum): "+caseNum);
		return sqlSession.delete("mappers.ChallengeDAO-mapper.deleteChallengeCase",caseNum);
	}


	
	/**
	 * 도전과제에 포함된 모든 테스트케이스삭제
	 * @param cNum
	 * @return 성곡 시 1
	 */
	@Override
	public int deleteAllCase(long cNum) {
		logger.info("deleteAllCase(long cNum): "+ cNum);
		return sqlSession.delete("mappers.ChallengeDAO-mapper.deleteAllCase",cNum);
	}

	
}

