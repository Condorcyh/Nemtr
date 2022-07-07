package com.graduation.nemtr.utils;

public class Methods {

    public static int getBetweenOrBelow(String x){
        if(x.contains("-")){
            String s1 = x.substring(0,x.indexOf('-'));
            String s2 = x.substring(x.indexOf('-') + 1);
            return (Integer.parseInt(s1) + Integer.parseInt(s2))/2;

        }
        else if(x.endsWith("+")){
            return Integer.parseInt(x.substring(0, x.length() - 1)) + 1;
        }
        return Integer.parseInt(x);
    }

    public static int rankZero(Integer i){
        return i == 0 ? 99999 : i;
    }
}
