import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.greenart.dbutil.DBUtil;

public class DAO_heart {
	
	//1. (행 생성-create ) 유저 아이디, 코디셋 아이디를 받아서 → 좋아요 테이블에 전부 넣는다.(버튼을 눌렀을 때 insert 되야함 )
	//2. (행 삭제) 좋아요 버튼 한번 더 누르면 행 삭제하기. 
	//3. (데이터 찾는 거 - 진호오빠 필요한 기능) 유저 아이디를 찾아서 → 그거에 맞는 좋아요 id 를 받아서 그걸 띄운다.
	
	
	
	//유저 아이디를 받아서, 좋아요 테이블에 넣기 
	private Heart resultMapping(ResultSet rs) throws SQLException{
		int id =rs.getInt("id");
		String user_id = rs.getString("user_id");
		int CodiSet_id = rs.getInt("CodiSet_id");
		
		return new Heart(id, user_id,CodiSet_id);
	}
	
	
	//현재 로그인 된 유저아이디, 코디셋을 받아서 좋아요 테이블에  insert 
	
	//where절로 현재 로그인 된 유저아이디를 	받아와야함?
	//코디셋을 받아야함? 
	public int create(String user_id, int CodiSet_id) throws SQLException{
		String query = "insert into heart (user_id,CodiSet_id) values (?,?)"/*여기에  로그인 된 유저 아이디 들어가야 함 */;
		
		Connection conn = null;
		PreparedStatement pstmt =null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			
			//배치작업
			pstmt.setString(1, user_id); 
			pstmt.setInt(2, CodiSet_id);
			return pstmt.executeUpdate();
			
		}finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}
	}
	//유저 아이디를 파라미터로 받아서 
	// 그 유저 아이디가 저장한 좋아요 목록 조회하기 (시간 빼고 전부)
	public List<Heart> read(String user_id) throws SQLException{
		String query ="select id,user_id,CodiSet_id from heart where user_id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<Heart> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(resultMapping(rs));
			}
			
		}finally {
			DBUtil.closeRS(rs);
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return list;
	}
	

	//좋아요 테이블의 아이디를 받아서, 그 행을 삭제한다.  
	public int delete(String user_id,int codiSet_id) throws SQLException {
//		String query = "delete from heart where id = ?";
		String query2 = "delete from heart where user_id = ? and codiSet_id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn= DBUtil.getConnection();
			pstmt = conn.prepareStatement(query2);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, codiSet_id);
			
			return pstmt.executeUpdate();
		}finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}
		
	}
	
	
}
