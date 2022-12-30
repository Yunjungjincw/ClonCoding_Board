package common;


//드라이버 로드
//DB와 Connection (Dao 에서 쿼리문 실행을 통해서 데이터를 가져와야하기 때문에 클래스 생성)
public class JdbcUtil {
	private static JdbcUtil instance = new JdbcUtil();
	
	
	static {
		
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("드라이버 로딩 성공!");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	
	
	private JdbcUtil() {
		//다른 곳에서 사용할 수 있도록 생성자 생성
	}
	
	
	//JdbcUtil 
	public JdbcUtil getInstance() {
		return instance;
	}
	
	
	
	
}
