package gi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;

public class DBUtil {

	static final String driver = "com.mysql.jdbc.Driver";// 驱动类名称
	static final String url = "jdbc:mysql://localhost:3306/gidb";// 数据库访问路径
	static final String user = "root";
	static final String password = "";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);// 加载数据库驱动
			conn = DriverManager.getConnection(url, user, password);// 创建数据库连接
		} catch (ClassNotFoundException e) {
			System.err.println("无法加载数据库驱动，找不到驱动类");
		} catch (SQLException e) {
			System.err.println("无法连接数据库，请检查数据库连接信息！");
		}
		return conn;
	}

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println("无法关闭数据库连接！");
		}
	}

	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.err.println("无法关闭Statement语句！");
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.err.println("无法关闭ResultSet结果集！");
		}
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			close(rs);
		} finally {
			try {
				close(stmt);
			} finally {
				close(conn);
			}
		}
	}

	public static int sqlUpdate(String sql, Object... params) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int rows = -1;
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("执行SQL语句：" + sql + "失败，具体原因如下：");
			System.err.println(e.getStackTrace());
		} finally {
			close(null, pstmt, con);
		}
		return rows;
	}

	public static ResultSet sqlQuery(String sql, Object... params) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CachedRowSet crs = null;
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			rs = pstmt.executeQuery();
//			crs = new CachedRowSetImpl();
//			crs.populate(rs);
		} catch (SQLException e) {
			System.err.println("执行SQL语句：" + sql + "失败，具体原因如下：");
			System.err.println(e.getStackTrace());
		} //finally {
			//close(rs, pstmt, con);
		//}
		return rs;
	}
	public static void main(String[] args) throws SQLException{	
            //getConnection();
//	  String sql="insert into gitable(classify,name) values(?,?)";
//            sqlUpdate(sql,"1","2");
           
            
            String sqlString="SELECT * FROM `gitb`";
            //String sqlString=insert into gitb values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);
           ResultSet dsResultSet= sqlQuery(sqlString);
           while(dsResultSet.next()){
           String classfiy=dsResultSet.getString("classify");
           System.out.print(classfiy);
           }
           int aI=1;
	}

	
}
