/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

/**
 *
 * @author farhan
 */
public class Query {
    public static String getBodyMail(String id){
        return "select * from body_email where id="+Integer.parseInt(id)+";";
    }
    
    public static String getDataJson(){
        return "select * from file_receiver";
    }
    
}
