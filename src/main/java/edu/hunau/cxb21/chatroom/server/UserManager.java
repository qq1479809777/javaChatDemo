package edu.hunau.cxb21.chatroom.server;

import java.util.HashMap;

public class UserManager {
    public static HashMap<String,String> allUsers = new HashMap<String,String>();
    static{
        allUsers.put("gangge", "123");
        allUsers.put("xiaobai", "456");
        allUsers.put("gujie", "789");
    }

    public static boolean login(String username, String password){
        if(allUsers.get(username)!=null && allUsers.get(username).equals(password)){
            return true;
        }else{
            return false;
        }
    }
}