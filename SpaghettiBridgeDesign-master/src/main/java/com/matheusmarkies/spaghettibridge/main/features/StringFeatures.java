package com.matheusmarkies.spaghettibridge.main.features;

public class StringFeatures {
    public static String invert(String s){
        String newS = "";
        for(int i = (s.length()-1);i>=0;i--)
            newS += s.charAt(i);
        return newS;
    }
}
