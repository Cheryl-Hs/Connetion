package JDBC;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */

public class Json {
    public static void main(){
        PrintWriter output = null;
        try {
            output = new PrintWriter("C:\\Users\\Administrator\\Desktop\\jquery-easyui-1.3.6\\demo\\tree\\tojson.json");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] qu = {"洪山区","江夏区","大村"};
        output.print("[");
        for (int k=0;k<3;k++){
            String jsonStr="{\n\"id\":1,\n\"text\":\"湖北省\","+"\n\"children\":[";
            output.print(jsonStr);

            for (int i=0;i<3;i++){
                String childrenStr1="{\n\t\"id\":11,\n\t\"text\":\"武汉市\",\n\t\"state\":\"closed\",\n\t\"children\":[";
                   output.println(childrenStr1);
                for (int j=0;j<3;j++){
                    String childrenStr2="{\t\t"+"\"id\":111,\n\t\t\"text\":"+"\""+ qu[j] +"\"\n},";
                        if (j!=2)
                            output.print(childrenStr2);
                        else 
                            output.print("{\t\t"+"\"id\":111,\n\t\t\"text\":"+"\""+ qu[j] +"\"\n}]");
                }
                if (i!=2)
                    output.print("\n},");
                else
                    output.print("");
                    
            }
            output.print("\n}]");
            if (k!=2)
                output.print("\n},");
            else
                output.print("");
        }
        output.println("\n}]");
        output.close();
    }
    
}
