/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gi;

import java.util.Collection;
import java.util.Iterator;
import net.sf.json.JSONObject;

/**
 *
 * @author witailab
 */
public  class JsonToDBUtil {

    //public static String JOInsertDb(JSONObject jo, String db, Object[] params) {
            public static String JOInsertDb(JSONObject jo, String db,int number) {

        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        sb.append("Insert into "+db+"(");
       Iterator itt = jo.keys();  
       //  params=new Object[jo.size()];
        int count=0;
    while (itt.hasNext()) {  
           //sb.append("?" + ",");
        String key = itt.next().toString(); 
        number--;
        sb.append(key+",");
      //  String value = jo.getString(key);  
      //  params[i]=value;
        count++;
        if(number==0)
            break;
    }//INSERT INTO `gidb`.`gitb` (`ID`, `classify`, `name`, `area`, `telephone`, `zipcode`, `address`, `province`, `region`, `county`, `towm`, `village`, `road`, `harry`, `building`, `floor`, `houseid`, `date`, `source`, `flag`) VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
     String substring=  sb.toString().substring(0,sb.length()-1);
sb1.append(substring);

    sb1.append(") values(");
    for(int i=0;i<count;i++){
    sb1.append("?,");
    }
  substring=sb1.toString().substring(0,sb1.length()-1)+")";
    //sb1.append(")");
    //sb.append("Insert into "+db+"(`classify`, `name`, `area`, `telephone`, `zipcode`, `address`, `province`, `region`, `county`, `towm`, `village`, `road`, `harry`, `building`, `floor`, `houseid`,`date`,  `source`, `flag`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
   //sb.append("Insert into "+db+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

   // String ssString=sb.toString();
    //String subString=ssString.substring(0,ssString.length()-1)+")";
//        for (int i = 0; i < jo.size(); i++) {
//            Object j =  jo.get((Object)i);
//  
//           
//           if (i < jo.size() - 1) {
//              String ss= ( String) jo.get("classify");
//                sb.append(j + ",");
//            } else {
//                sb.append(j+")");
//
//            }
//        }

        return substring;
    }
    public static String JOUpdateDb( String db,String s) {

        StringBuilder sb = new StringBuilder();
       
        
 /*       Iterator itt = jo.keys();  
       //  params=new Object[jo.size()];
        int i=0;
    while (itt.hasNext()) {  
           sb.append("?" + ",");
        String key = itt.next().toString();  
        String value = jo.getString(key);  
        params[i]=value;
        i++;
    }*///INSERT INTO `gidb`.`gitb` (`ID`, `classify`, `name`, `area`, `telephone`, `zipcode`, `address`, `province`, `region`, `county`, `towm`, `village`, `road`, `harry`, `building`, `floor`, `houseid`, `date`, `source`, `flag`) VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
    sb.append("UPDATE "+db+s);
    //String subString=ssString.substring(0,ssString.length()-1)+")";
//        for (int i = 0; i < jo.size(); i++) {
//            Object j =  jo.get((Object)i);
//  
//           
//           if (i < jo.size() - 1) {
//              String ss= ( String) jo.get("classify");
//                sb.append(j + ",");
//            } else {
//                sb.append(j+")");
//
//            }
//        }

        return sb.toString();
    }
     public static String JODeleteDb(String db) {

        StringBuilder sb = new StringBuilder();
       
        
 /*       Iterator itt = jo.keys();  
       //  params=new Object[jo.size()];
        int i=0;
    while (itt.hasNext()) {  
           sb.append("?" + ",");
        String key = itt.next().toString();  
        String value = jo.getString(key);  
        params[i]=value;
        i++;
    }*///INSERT INTO `gidb`.`gitb` (`ID`, `classify`, `name`, `area`, `telephone`, `zipcode`, `address`, `province`, `region`, `county`, `towm`, `village`, `road`, `harry`, `building`, `floor`, `houseid`, `date`, `source`, `flag`) VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
    sb.append("DELETE FROM "+db+" WHERE `id`=?");
    String ssString=sb.toString();
    //String subString=ssString.substring(0,ssString.length()-1)+")";
//        for (int i = 0; i < jo.size(); i++) {
//            Object j =  jo.get((Object)i);
//  
//           
//           if (i < jo.size() - 1) {
//              String ss= ( String) jo.get("classify");
//                sb.append(j + ",");
//            } else {
//                sb.append(j+")");
//
//            }
//        }

        return ssString;
    }
             
    
             
    
  /*  public static String JOUpdateDb(JSONObject jo, String db, Object[] params) {
        StringBuilder sb = new StringBuilder();
       
        sb.append("undate "+db+" set ");
        Iterator itt = jo.keys();  
       //  params=new Object[jo.size()];
        int i=0;
    while (itt.hasNext()) {  
          
        String key = itt.next().toString(); 
         sb.append(key+"=?,");
        String value = jo.getString(key);  
        params[i]=value;
        i++;
    }
    sb.append("Insert into "+db+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    String ssString=sb.toString();
    //String subString=ssString.substring(0,ssString.length()-1)+")";
//        for (int i = 0; i < jo.size(); i++) {
//            Object j =  jo.get((Object)i);
//            jo.ge
//           
//            if (i < jo.size() - 1) {
//              String ss= ( String) jo.get("classify");
//                sb.append(j + ",");
//            } else {
//                sb.append(j+")");
//
//            }
//        }

        return ssString;
    }*/
}
