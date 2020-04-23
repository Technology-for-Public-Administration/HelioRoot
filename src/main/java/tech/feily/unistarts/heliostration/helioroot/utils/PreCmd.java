package tech.feily.unistarts.heliostration.helioroot.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.mongodb.client.MongoDatabase;

public class PreCmd {

    private static Scanner scan = new Scanner(System.in);
    private static String[] fields = {"dbHost", "dbname", "docName", "SerCli"};
    private static String[] display = {"DB & doc: ", "SerCli: "};
    private static Map<String, String> param = new HashMap<String, String>();
    
    public static boolean run() {
        String temp = "";
        int i = 0;
        while (i < display.length) {
            System.out.print(display[i]);
            temp = scan.nextLine();
            if (display[i].equals("DB & doc: ")) {
                if (temp.equals("none")) {
                    param.put("hasDb", temp);
                    i++;
                } else if (dbIsValid(temp)){
                    param.put(fields[0], temp.split("\\.")[0]);
                    param.put(fields[1], temp.split("\\.")[1]);
                    param.put(fields[2], temp.split("\\.")[2]);
                    i++;
                }
            } else if (display[i].equals("SerCli: ")){
                param.put(fields[3], temp);
                i++;
            }
        }
        return true;
    }
    
    public static boolean dbIsValid(String dbdoc) {
        try {
            MongoDatabase mgdb = MongoDB.getInstance(dbdoc.split("\\.")[0], dbdoc.split("\\.")[1]);
            mgdb.getCollection(dbdoc.split("\\.")[2]);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    public static Map<String, String> getParam() {
        return param;
    }
    
}
