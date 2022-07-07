package com.graduation.nemtr.enums;
public enum RankType {
    RUANKE("软科","rk_rank"),
    WUSHULIAN("武书连","wsl_rank"),
    XIAOYOUHUI("校友会","xyh_rank"),
    QS("QS排名（世界）","qs_rank"),
    US("US排名（世界）","us_rank"),
    TIMESCHINA("泰晤士中国","tws_rank_china");
    private final String name;
    private final String mapName;
    RankType(String name, String mapName){
        this.name = name;
        this.mapName = mapName;
    }
    @Override
    public String toString(){
        return mapName;
    }

    public String getName(){
        return name;
    }

}
