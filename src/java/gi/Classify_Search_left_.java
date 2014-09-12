/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gi;

import com.sun.rowset.CachedRowSetImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;
//import static java.sql.Types.DATE;
import java.util.Iterator;
import net.sf.json.*;


/**
 *
 * @author witailab
 */
public class Classify_Search_left_ extends HttpServlet {

    List<GiBean> myList = new ArrayList<GiBean>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String data = "";
                    try {
                        data = getDate(request);
                    } catch (SQLException ex) {
                        out.write("error");
                    }
            out.write(data);
        } finally {
            out.close();
        }
    }
    public JSONArray resultSetToJson(CachedRowSetImpl rowset) throws SQLException {
        // json数组  
        if (rowset == null) {
            return null;
        }
        JSONArray jsonarray = new JSONArray();

        // 获取列数  
        ResultSetMetaData rsmd = null;

        rsmd = rowset.getMetaData();
//int rowsI=rs.getRow();
         //rowset.relative(-1);
        // 遍历ResultSet中的每条数据  
        while (rowset.next()) {
            JSONObject jsonObj = new JSONObject();

            // 遍历每一列 
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String columnName = rsmd.getColumnName(i);
                //  getColumnCount(i);
                String value = rowset.getString(columnName);
                jsonObj.put(columnName, value);
            }
            jsonarray.add(jsonObj);
        }
        return jsonarray;
    } 
    public String getDate(HttpServletRequest request) throws SQLException {
        int total = MySqlHelper.getCount("select count(*) from `localserviceinfo`");//假设从数据库中取出100条
       // int total =MySqlHelper.getCount("select * from localserviceinfo_2"); //假设从数据库中取出100条
        int page = 0, rows = 0;
        page = Integer.parseInt(request.getParameter("page"));
        rows = Integer.parseInt(request.getParameter("rows"));
        int start=0;
        start=(page-1)*rows;
        String sqlString = "SELECT `id`,`label` FROM `localserviceinfo` limit "+start+" ,"+rows;//
        CachedRowSetImpl cs = MySqlHelper.sqlQuery(sqlString);
      //  int number=1;
        
      //  cs.absolute((page-1)*rows+1);
        // JSONArray ja=new JSONArray();
        //ja= resultSetToJson(ds);
        JSONArray ja = new JSONArray();
        ja = resultSetToJson(cs);
        
        JSONObject jsono = new JSONObject();
        jsono.put("total", total);
        
        //jsono.put("rows", ja);
        jsono.put("rows", ja.toString());
//        GiBean giBean = new GiBean();
//        giBean.setClassify("a");
//        myList.add(giBean);
//        GiBean giBean1 = new GiBean();
//        myList.add(giBean1);
//        total = myList.size();

        // giBean1.setClassify("b");
        ///  String ss = "{\"total\":40,\"rows\":[{\"classify\":\"11\",\"name\":\"22\"}]}";
        return jsono.toString();
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
