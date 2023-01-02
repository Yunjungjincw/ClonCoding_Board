package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.JdbcUtil;

public class BoardDao {
		private JdbcUtil ju;
		
		public BoardDao() {
			ju = JdbcUtil.getInstance();
		}
		
		
	//C
	public int insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int ret = -1;
		
		String sql = "insert into board (NUM,TITLE,WRITER,CONTENT,REGDATE,CNT)"+ 
				 	" values (board_seq.nextval,?,?,?,sysdate,0)";
		try {
			conn = ju.getConnection();
			pstmt = conn.prepareStatement(sql); //쿼리문 실행하기전 준비
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			ret = pstmt.executeUpdate();	
			//executeUpdate 쿼리문 실행 // inset, update, delete
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	

	//R
	public List<BoardVo> selectAll(){
		Connection conn = null;
		Statement stmt= null;
		ResultSet rs= null;
		
		String sql = "select NUM,TITLE,WRITER,CONTENT,REGDATE,CNT" + 
				" from BOARD order by NUM desc";
		ArrayList<BoardVo> ls = new ArrayList<BoardVo>();
		
		try {
			conn = ju.getConnection();
			stmt = conn.createStatement();	//statement를 써봤었는데, 아무 생각 없이 코드 작성해서 그랬나 기억이 안남
			rs = stmt.executeQuery(sql);
	
			
			while(rs.next()) {
				BoardVo vo = new BoardVo(
				rs.getInt(1),
				rs.getString(2),
				rs.getString(3),
				rs.getString(4),
				new Date(rs.getDate(5).getTime()),
				rs.getInt(6));
				
				ls.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}
	
	//R 상세조회
	public BoardVo selectOne(int num){
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String sql = "select NUM,TITLE,WRITER, CONTENT,REGDATE,CNT" + 
				" from BOARD" + 
				" where NUM=?";
		BoardVo vo = null;
		
		try {
			conn = ju.getConnection();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				updateCnt(num);
				vo = new BoardVo(
				rs.getInt(1),
				rs.getString(2),
				rs.getString(3),
				rs.getString(4),
				new Date(rs.getDate(5).getTime()),
				rs.getInt(6)+1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return vo;
	}
	
	
	
	//U
	public int update(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int ret = -1;
		
		String sql = "update BOARD" + 
				" set TITLE=?, CONTENT=?" + 
				" where NUM=?";
		try {
			conn = ju.getConnection();
			pstmt = conn.prepareStatement(sql); //쿼리문 실행하기전 준비
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNum());
			ret = pstmt.executeUpdate();	
			//executeUpdate 쿼리문 실행 // inset, update, delete
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	
	//조회수 늘리기
	public int updateCnt(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int ret = -1;
		
		String sql = "update BOARD" + 
				" set CNT=CNT+1" + 
				" where NUM=?";
		try {
			conn = ju.getConnection();
			pstmt = conn.prepareStatement(sql); //쿼리문 실행하기전 준비
			
			pstmt.setInt(1,num);
		
			ret = pstmt.executeUpdate();	
			//executeUpdate 쿼리문 실행 // inset, update, delete
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	
	
	
	//D
	public int delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int ret = -1;
		
		String sql = "delete from BOARD" + 
				" where NUM=?";
		try {
			conn = ju.getConnection();
			pstmt = conn.prepareStatement(sql); //쿼리문 실행하기전 준비
			
			
			pstmt.setInt(1, num);
			ret = pstmt.executeUpdate();	
			//executeUpdate 쿼리문 실행 // inset, update, delete
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	
	
	
	
	
	
}
