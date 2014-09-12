package gi;

//import static gi.DBUtil.sqlQuery;
//import static java.lang.*;
import com.sun.rowset.CachedRowSetImpl;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MySqlHelper {
	public  MySqlHelper(){
        }
        public static Connection getConnection(){
		 Connection conn=null;
   try {
    Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
   } catch (ClassNotFoundException e) {
    // TODO Auto-generated catch block
    System.out.println("Driverclass load fail!");
    e.printStackTrace();
   }
     try {
    conn = DriverManager.getConnection("proxool.proxool");
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
   return conn;
}
	
	public static int update0(String sql,Object... params){
		Connection conn=getConnection();
                int count=0;
		if(conn!=null)
		{
			try {int i;
				PreparedStatement ps=conn.prepareStatement(sql);
			for( i=0;i<params.length;i++)
				{
					ps.setObject(i+1, params[i]);
                                      // ps.setString(i+1, (String)params[i]);
					
				}	
                                        count=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print("出错！！~?");
				close(conn);
			}	
	}
                close(conn);
           return count;
        }
	public static CachedRowSetImpl sqlQuery(String sql, Object... params) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i + 1, params[i]);
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			System.err.println("error");   //$NON-NLS-1$ //$NON-NLS-2$
			e.printStackTrace();
		}
              CachedRowSetImpl rowset=new CachedRowSetImpl();
	          rowset.populate(rs);
                  close(con);
           
	          return rowset;
       
                }
        public static int getCount(String sql) throws SQLException
        {
            //String sql=select count(*) from gitb;
          //   ResultSet rs= sqlQuery(sql);
              CachedRowSetImpl rs= sqlQuery(sql);
             int count=0;
             if(rs==null)   count=0;
            try {
                while(rs.next()){
                 count=  Integer.parseInt(rs.getString(1)) ;
                   // count=rs.getInt(0);
                }} catch (SQLException ex) {
              //  Logger.getLogger(MySqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            return count;
        }
//        public static int getCount(String sql) throws SQLException
//        {
//            //String sql=select count(*) from gitb;
//             CachedRowSetImpl rs= sqlQuery(sql);
//             int count=0;
//             if(rs==null)   count=0;
//            try {
//                while(rs.next()){
//                    count++;
//                }} catch (SQLException ex) {
//              //  Logger.getLogger(MySqlHelper.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return count;
//        }
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println("error");  
		}
	}
	public static void main(String[] args) throws SQLException{	
            //getConnection();
	  //String sql="insert into gitb(name,date) values(?,?)";
            //update0(sql,"ggg","2014-4-4");
     
            String sqlString="SELECT * FROM gitb";
            System.out.println(getCount(sqlString));
           ResultSet dsResultSet= sqlQuery(sqlString);
           while(dsResultSet.next()){
           String classfiy=dsResultSet.getString("classify")+"jjjj";
           
           System.out.print(classfiy);
           }
          // String inString="";

           //int fI= update0(inString);
         // System.out.print(fI);

           int aI=1;
	}
}
