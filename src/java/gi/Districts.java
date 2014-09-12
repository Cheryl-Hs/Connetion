/*
 * To change this template, choose Tools | Templates
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
//import java.util.Date;

/**
 *
 * @author lyunfan
 */
public class Districts extends HttpServlet {

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
            int method = Integer.parseInt(request.getParameter("method"));
            String data = "";
            switch (method) {
                case 1:
                    try {
                        data = getDate(request);
                    } catch (SQLException ex) {
                        out.write("error");

                        // Logger.getLogger(GiServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                    submit(request);
                    break;
                // case 3:modifyRow();break;
                // case 4:saveDate();break;

            }
            //   String ss="{\"total\":40,\"rows\":[{\"classify\":\"11\",\"name\":\"22\",\"area\":\"33\"}]}";
            out.write(data);
            /* TODO output your page here. You may use following sample code. */
            // out.println("<!DOCTYPE html>");
            // out.println("<html>");
            //  out.println("<head>");
            //out.println("<title>Servlet GiServelet</title>");            
            // out.println("</head>");
            // out.println("<body>");
            // out.println("<h1>Servlet GiServelet at " + request.getContextPath() + "</h1>");
            // out.println("</body>");
            // out.println("</html>");
        } finally {
            out.close();
        }
    }

    public void submit(HttpServletRequest request) {
        int flag = 0;
        String deleted = request.getParameter("deleted");
        String inserted = request.getParameter("inserted");
        String updated = request.getParameter("updated");
        if (deleted != null) {
            JSONArray arr = JSONArray.fromObject(deleted);
            for (Object o : arr) {
                JSONObject a = JSONObject.fromObject(o);
                String sqldeleteString = null;
                Object[] objects = new Object[a.size()];
                
                sqldeleteString = JsonToDBUtil.JODeleteDb("districts");
                Iterator itt = a.keys();
                int i = 0;
                while (itt.hasNext()) {
                    String key = itt.next().toString();
                    String value = a.getString(key);
                   /* if(value.equals("")){
                    value=null;
                    }*/
                    objects[i] = value;
                    i++;
                }
                Object id=new Object();
                id=objects[0];
                flag = MySqlHelper.update0(sqldeleteString, id);
            }
        }
        if (inserted != null) {
            JSONArray arr = JSONArray.fromObject(inserted);
            for (Object o : arr) {
                JSONObject a = JSONObject.fromObject(o);
                String sqlInsertString = null;
                Object[] objects = new Object[a.size()];
                sqlInsertString = JsonToDBUtil.JOInsertDb(a, "districts",5);
                /* if(objects[a.size()-3]==""){
         
                 Date nowDate=new Date();
                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                 String time = dateFormat.format( nowDate ); 

                 objects[a.size()-3]=time;
           
                 }*/
                /* for (int i = 0; i < a.size(); i++) {
                 String j = (String) a.get(i);
                 objects[i]=j;
                 }*/
                Iterator itt = a.keys();
                //  params=new Object[jo.size()];
                int i = 0;
                while (itt.hasNext()) {
                    String key = itt.next().toString();
                    String value = a.getString(key);
                    // {
                    //Date date=new Date(0);
                    //date=date.valueOf(value);
                    // objects[i]=date;
                    // }
                     if(!key.equals("id"))
                    objects[i] = value;
                    i++;
                }
                flag = MySqlHelper.update0(sqlInsertString, objects);
            }

        }
        if (updated != null) {
            JSONArray arr = JSONArray.fromObject(updated);
            for (Object o : arr) {
                JSONObject a = JSONObject.fromObject(o);
                String sqlUpdateString = null;
                Object[] objects = new Object[a.size()];
                String s=" SET `code`=?,`province`=?,`city`=?,`county`=?,`townroad`=? WHERE `id`=?";
                sqlUpdateString = JsonToDBUtil.JOUpdateDb("districts",s);
                Iterator itt = a.keys();
                //  params=new Object[jo.size()];
                int i = 0;
               // int count=6;
                while (itt.hasNext()) {
                    String key = itt.next().toString();
                    String value = a.getString(key);
               if(value.equals("")){
                    value=null;
                    }
                    objects[i] = value;
                    i++;
                   // count--;
                   // if(count==0)
                     //   break;
                }
                Object ls=new Object();
                     ls=objects[0];
                for(int m=0;m<a.size()-1;m++){
                objects[m]=objects[m+1];
                }
                objects[a.size()-1]=ls;
                flag = MySqlHelper.update0(sqlUpdateString, objects);
            }
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
          //  rows--;
           // if(rows==0)
            //    break;
        }

        return jsonarray;
    } 
   /* public JSONArray resultSetToJson(ResultSet rs) throws SQLException {
        // json数组  
        if (rs == null) {
            return null;
        }
        JSONArray jsonarray = new JSONArray();

        // 获取列数  
        ResultSetMetaData rsmd = null;

        rsmd = rs.getMetaData();
//int rowsI=rs.getRow();

        // 遍历ResultSet中的每条数据  
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();

            // 遍历每一列 
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String columnName = rsmd.getColumnName(i);
                //  getColumnCount(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }

            jsonarray.add(jsonObj);
        }

        return jsonarray;
    }*/
 
    public String getDate(HttpServletRequest request) throws SQLException {
        int total = MySqlHelper.getCount("select count(*) from `districts`");//假设从数据库中取出100条
       // int total =MySqlHelper.getCount("select * from localserviceinfo_2"); //假设从数据库中取出100条

        int page = 0, rows = 0;
        page = Integer.parseInt(request.getParameter("page"));
        rows = Integer.parseInt(request.getParameter("rows"));
        int start=0;
        start=(page-1)*rows;
        String sqlString = "SELECT * FROM `districts` limit "+start+" ,"+rows;//
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
