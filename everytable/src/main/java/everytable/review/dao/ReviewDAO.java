package everytable.review.dao;

import java.util.ArrayList;
import java.util.List;
import everytable.main.dao.DAO;
import everytable.review.vo.ReviewVO;
import everytable.util.db.DB;
import everytable.util.page.PageObject;

public class ReviewDAO extends DAO{
	//1.리뷰 리스트
	public List<ReviewVO> list(PageObject pageObject) throws Exception {
		List<ReviewVO> list = new ArrayList<ReviewVO>();
		
		//1 2
		con = DB.getConnection();
		//3
		// 3-1 원본 데이터 가져오기
		String sql = " select  b.no, b.content, b.id, m.name, "
				+ "  to_char(b.writeDate, 'yyyy-mm-dd') writeDate "
				+ "  from board_reply b, member m "
				+ "  where (no = ?) and (b.id = m.id) "
				+ "  order by rno desc";
		// 3-2 순서 번호 붙이기
		sql = "    select rownum rnum, no, content, id, name, writeDate "
				+ "    from( " + sql + ")";
		// 3-3 페이지 데이터 가져오기
		sql = "select rnum, no, content, id, name, writeDate "
				+ " from( " + sql + ") "
				+ " where rnum between ? and ?";
		//4
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, pageObject.getStartRow());
		pstmt.setLong(2, pageObject.getEndRow());
		//5
		rs = pstmt.executeQuery();
		//6
		if(rs != null) {
			while(rs.next()) {
				ReviewVO vo = new ReviewVO();
				vo.setNo(rs.getLong("no"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setWriteDate(rs.getString("writeDate"));
				list.add(vo);
			} // while 의 끝
		} // if 끝
		//7
		DB.close(con, pstmt, rs);
		
		return list;
	} //list의 끝
	
	// 1-1 리뷰 데이터 개수 가져오기
		public Long getTotalRow(PageObject pageObject) throws Exception{
			
			
			// 리턴 타입과 동일한 변수 선언 - 결과 저장
			Long totalRow = 0L;
			
			// 1. 드라이버 확인 - static으로 선언된 내용이 자동으로 올라간다.	// 2. 연결 객체 - 정보를 넣고 서버에 다녀온다.
			con = DB.getConnection();
			
			// 3. 실행할 쿼리 작성
			String sql = "select count(*) from review where no = ?";
			
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
//			pstmt.setLong(1, pageObject.getNo());
			
			// 5. 실행 : select :executeQuery() -> rs, insert / update / delete :executeUpdate() -> Integer
			rs = pstmt.executeQuery();
			
			// 6. DB에서 가져온 데이터 채우기
			if(rs != null && rs.next()) {
				// 데이터를 저장한다.
				totalRow = rs.getLong(1);
			} // if의 끝
			
			// 7. DB 닫기
			DB.close(con, pstmt, rs);
			
			return totalRow;
		} // getTotalRow()의 끝

		// 2. 댓글 등록
		public Integer write(ReviewVO vo) throws Exception {
			Integer result = 0;
			
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = "insert into review( no, content, id) "
					+ " values(review_seq.nextval, ?, ?, ?)" ;
			//4 .
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			// 5.
			result = pstmt.executeUpdate();
			// 6. - 5. 처리됨.
			//7.
			DB.close(con, pstmt);
			
			return result;
		}
		
		// 3. 댓글 수정
		public Integer update(ReviewVO vo) throws Exception {
			Integer result = 0;
			
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = "update review set content = ? "
					+ " where id = ?" ;
			//4 .
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getId());
			// 5.
			result = pstmt.executeUpdate();
			// 6. - 5. 처리됨.
			//7.
			DB.close(con, pstmt);
			
			return result;
		}
		
		
		// 4. 댓글 삭제
		public Integer delete(ReviewVO vo) throws Exception {
			Integer result = 0;
			
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = "delete from review "
					+ " where id = ?" ;
			//4 .
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			// 5.
			result = pstmt.executeUpdate();
			// 6. - 5. 처리됨.
			//7.
			DB.close(con, pstmt);
			
			return result;
		}

}