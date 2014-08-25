<%-- 
    Document   : index
    Created on : 2014-8-20, 16:07:20
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="JDBC.Conn"%>
<%@ page language="java" import="JDBC.Json" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<%
//  Json test = new Json();
//  test.main();
Connection con=new Conn().getConnection();

Statement stmt=con.createStatement();
Statement stmt1=con.createStatement();
Statement stmt2=con.createStatement();
Conn.main(stmt,stmt1,stmt2);
//ResultSet province = stmt.executeQuery("select * from province");
//
//
////ResultSet rs=stmt.executeQuery("select  distinct province from districts");
//while(province.next()){
//    
//    String id = province.getString(1);
//    String name=province.getString(2);
//    out.print(name+"\n");
//    String select = "select parents,name from city where parents = "+ id;
//    ResultSet city = stmt1.executeQuery(select);
//
//while(city.next()){
//String cityq=city.getString(2);
//out.print(cityq);
//}
//
//city.close();
//}
//String select = "select count(*) from city where parents = 5 ";
//ResultSet city = stmt2.executeQuery(select);
//city.next();
//String ss = city.getString(1);
//out.print(ss);
//city.close();
//province.close();
con.close();
%>
    </body>
</html>
