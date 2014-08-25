/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JDBC;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Logger;


/**
 *
 * @author yang
 */
public class Conn {
public void Conn(){}

public Connection getConnection(){
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

 public static void main(Statement stmt,Statement stmt1,Statement stmt2) throws SQLException{
//     Connection con=new Conn().getConnection();
//
//     Statement stmt=con.createStatement();
        int provinceCount = 0, provinceCountNum = 0;
        String select = "select count(*) from province " ;
        ResultSet province = stmt.executeQuery(select);
        province.next();
        provinceCountNum = province.getInt(1);
        province.close();
        province = stmt.executeQuery("select * from province");



        PrintWriter output = null;
        try {
            output = new PrintWriter("C:\\Users\\Administrator\\Desktop\\tojson.json","UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
    }
        output.print("[");
        int pcount = 1,ccount = 1,Ccount = 1;
        
        
        while(province.next()){
            
            String jsonStr="{\n\"id\":"+Integer.toString(pcount)+",\n\"text\":\""+ province.getString(2) +"\","+"\n\"state\":\"closed\",\n\"children\":[";
            output.print(jsonStr);

            int cityCount = 0;
            int cityCountNum = 0;
            select = "select count(id) from city where parents = " + province.getString(1);
            ResultSet city = stmt1.executeQuery(select);
            city.next();
            cityCountNum = city.getInt(1);
            city.close();
            select = "select id,name from city where parents = " + province.getString(1);
            city = null;
            city = stmt1.executeQuery(select);
            ccount = 1;
            while(city.next()){
                
                int countyCount = 0;   //
                int countyCountNum = 0;
                String selectCounty = "select count(id) from county where parents = \""+ province.getString(1)+ "," +city.getString(1)+"\"";
                ResultSet county = stmt2.executeQuery(selectCounty);
                county.next();
                countyCountNum = county.getInt(1);
                county.close();             //
                
                String childrenStr1="{\n\t\"id\":"+ Integer.toString(pcount) + Integer.toString(ccount) +",\n\t\"text\":\"" + city.getString(2)+"\"";//除去,\n\t\"state\":\"closed\",\n\t
                ccount++;
                output.print(childrenStr1);
                if(cityCountNum==1||countyCountNum==0){                                 //加
                    output.print("\n");                          //加
                }                                                   //加
               // if(cityCount < cityCountNum-1)                 
               else {
                    output.println(",\n\t\"state\":\"closed\",\n\t\"children\":[");//增加了\n\t\"state\":\"closed\",\n\t
                }
//                int countyCount = 0;
//                int countyCountNum = 0;
//                String selectCounty = "select count(id) from county where parents = \""+ province.getString(1)+ "," +city.getString(1)+"\"";
//                ResultSet county = stmt2.executeQuery(selectCounty);
//                county.next();
//                countyCountNum = county.getInt(1);
//                county.close();
                county = null;
                selectCounty = "select name from county where parents = \""+ province.getString(1)+ "," +city.getString(1)+"\"";
                county = stmt2.executeQuery(selectCounty);
                Ccount = 1;
                while(county.next()){
                    output.print("{\t\t"+"\"id\":");
                    String childrenStr2= Integer.toString(pcount) + Integer.toString(ccount-1)+Integer.toString(Ccount)+",\n\t\t\"text\":"+"\""+ county.getString(1) +"\"\n}";
                    Ccount++;
                    output.print(childrenStr2);
                    if(countyCount < countyCountNum-1){
                        output.print(",");
                        countyCount++;
                    }
                    else{
                        output.println("]");
                    }
                }
                if(cityCount < cityCountNum-1){
                        output.print("},");
                        cityCount++;
                    }
                    else{
                        output.println("}]");        //加}
                    }
                county.close();                   
            }
            city.close();
            if(provinceCount < provinceCountNum-1){
                        output.print("},");
                        provinceCount++;
                    }
                    else{
                        output.println("}]");     //加}
            }
            pcount++;
        }
        province.close();
        output.close();
//        con.close();
    }


// public static void main(String args[]) throws SQLException{
//     
//      Connection con=new Conn().getConnection();
//         if (con != null) {
//                System.out.println("数据连接测试成功！");
//            }
//    
//      Statement Stmt = null;
//    try {
//        Stmt = con.createStatement();
//            ResultSet rs=Stmt.executeQuery("select * from city");
//         if(rs.next()){
//        System.out.print("OK");
//      }
//    } catch (SQLException ex) {
//        //Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
//    }
//   
//}

}