package everytable.reservation.dao;

import java.util.ArrayList;
import java.util.List;

import everytable.main.dao.DAO;
import everytable.reservation.vo.ReservationVO;
import everytable.util.db.DB;
import everytable.util.page.PageObject;

public class ReservationDAO extends DAO {
	
	// 예약 리스트
	public List<ReservationVO> list(PageObject pageObject) throws Exception{
		List<ReservationVO> list = new ArrayList<>();
		
		// 1.2.
		con = DB.getConnection();
		
		// 3.
		String sql = "select store_id, res_no, to_char(res_date, 'yyyy-mm-dd') res_date, res_time, res_type "
				+ " from reservation order by ";
		
		sql += "select rownum rnum store_id, res_no, res_date, res_time, res_type "
				+ " from (" + sql + ")";
		
		sql = "select rnum store_id, res_no, res_date, res_time, res_type "
				+ " from(" + sql + ") where rnum between ? and ?";
		
		// 4.
		pstmt = con.prepareStatement(sql);
		
		// 5.
		rs = pstmt.executeQuery();
		
		// 6.
		if(rs != null) {
			while(rs.next()) {
				ReservationVO vo = new ReservationVO();
				
				vo.setStoreId(rs.getLong("storeId"));
				vo.setResNo(rs.getLong("resNo"));
				vo.setResDate(rs.getString("resDate"));
				vo.setResTime(rs.getString("resTime"));
				vo.setResType(rs.getString("resType"));
				list.add(vo);
			} // while 끝
		} // if 끝
		
		// 7.
		DB.close(con, pstmt, rs);
		
		return list;
	} // list() 끝
	
	// 예약 게시판 글의 개수
	public Long getTotalRow(PageObject pageObject) throws Exception {
		Long totalRow = 0L;
		
		// 1.2.
		con = DB.getConnection();
		
		// 3.
		String sql = "select count(*) from reservation";
		
		// 4.
		pstmt = con.prepareStatement(sql);
		
		// 5.
		rs = pstmt.executeQuery();
		
		// 6.
		if(rs != null && rs.next()) {
			totalRow = rs.getLong(1);
		} // if 끝
		
		// 7.
		DB.close(con, pstmt, rs);
		
		return totalRow;
	} // getTotalRow() 끝
	
	

}
