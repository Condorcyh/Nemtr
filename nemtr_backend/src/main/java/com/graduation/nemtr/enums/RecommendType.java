package com.graduation.nemtr.enums;
public enum RecommendType {
    RISK("冲刺院校"),
    NORMAL("求稳院校"),
    SAFE("保底院校");
    private final String name;
    RecommendType(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }


}
