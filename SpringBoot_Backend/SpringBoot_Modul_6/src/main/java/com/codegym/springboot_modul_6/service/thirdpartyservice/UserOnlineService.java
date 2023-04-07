package com.codegym.springboot_modul_6.service.thirdpartyservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class UserOnlineService {

    private static final UserOnlineService USER_ONLINE_SERVICE = new UserOnlineService();

    public static UserOnlineService getUserOnlineService(){
        return USER_ONLINE_SERVICE;
    }

    private  Map<String , ArrayList<String >> userCache;

    private  ArrayList<String > tempList = new ArrayList<>();

    public  Map<String , ArrayList<String >> getUser(){
        return userCache;
    }


    public  void addUser(String ads){
        tempList.add(ads);
        userCache.put("user", tempList);
    }

    public  void removeUser(String ads){
        tempList.remove(ads);
        userCache.put("user", tempList);
    }
}
