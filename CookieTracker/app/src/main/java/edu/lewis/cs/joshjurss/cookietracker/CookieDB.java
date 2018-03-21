package edu.lewis.cs.joshjurss.cookietracker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Josh Jurss on 3/22/2017.
 */

public class CookieDB {

    private List<Cookie> cookieList;
    private static CookieDB cookieDB;

    public static CookieDB get(){
        if(cookieDB==null){
            cookieDB = new CookieDB();
        }
        return cookieDB;
    }

    private CookieDB(){
        cookieList = new ArrayList<Cookie>();

        cookieList.add(new Cookie("Chocolate Chip", "none", "round", 1, false ));
        cookieList.add(new Cookie("Peanut Butter", "none", "amoeba", 1, false ));
        cookieList.add(new Cookie("Lemon", "Sugar Crystals", "square", 1, true));
    }

    public List<Cookie> getCookies(){
        return cookieList;
    }

    public Cookie getCookie(UUID id){
        Cookie cookie = null;

        for(Cookie c:cookieList){
            if(c.getId().equals(id)){
                cookie = c;
            }
        }
        return cookie;
    }

}
