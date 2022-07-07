package com.graduation.nemtr.enums;
public enum EntranceType {
    LI(1,"理科"),
    WEN(2,"文科"),
    ZONGHE(3,"综合"),
    PHYSICS(2073,"物理类"),
    HISTORY(2074,"历史类"),
    ;
    private final int type;
    private final String name;
    EntranceType(int type,String name){
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }
    public String getName(){return name;}
    public static String getNameById(int id){
        for(EntranceType p :EntranceType.values()){
            if(id==p.getType()){
                return p.getName();
            }
        }
        return null;
    }
}
