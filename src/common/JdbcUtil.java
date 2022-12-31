package common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//드라이버 로드
//DB와 Connection (Dao 에서 쿼리문 실행을 통해서 데이터를 가져와야하기 때문에 클래스 생성)
public class JdbcUtil {
	private static JdbcUtil instance = new JdbcUtil();
	
	private static DataSource ds;	//JNDI DB 연결 소스 코드 (형식 존재함)
	static {
		
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("드라이버 로딩 성공!");
				
				//데이터 소스 준비
				InitialContext ctx = new InitialContext();
				ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myOracle");
				// jdbc/myOracle 이름의 자원을 찾아라, 
				// 리소스 정보를 가지고 톰캣이 정보를 찾고 자원을 불러와서 데이터 소스를 생성해라
				System.out.println("Connection Pool 생성!");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
	}
	
	
	
	
	private JdbcUtil() {
		//다른 곳에서 사용할 수 있도록 생성자 생성
	}
	
	
	//JdbcUtil 
	public static JdbcUtil getInstance() {
		return instance;
	}
	
	
	//DB에 접속된 Connection을 return 해줘야하기 때문에, Connection을 return해줘야 함.
	public Connection getConnection() throws SQLException{
		return ds.getConnection(); 	//풀에서 커넥션 반환
	}
	
}
