package com.graduation.nemtr.enums;
public enum Provinces {
    Beijing("北京",11),
    Tianjin("天津",12),
    Hebei("河北",13),
    Shanxi("山西",14),
    Neimenggu("内蒙古",15),
    Liaoning("辽宁",21),
    Jilin("吉林",22),
    Heilongjiang("黑龙江",23),
    Shanghai("上海",31),
    Jiangsu("江苏",32),
    Zhejiang("浙江",33),
    Anhui("安徽",34),
    Fujian("福建",35),
    Jiangxi("江西",36),
    Shandong("山东",37),
    Henan("河南",41),
    Hubei("湖北",42),
    Hunan("湖南",43),
    Guangdong("广东",44),
    Guangxi("广西",45),
    Hainan("海南",46),
    Chongqing("重庆",50),
    Sichuan("四川",51),
    Guizhou("贵州",52),
    Yunnan("云南",53),
    Tibet("西藏",54),
    Shaanxi("陕西",61),
    Gansu("甘肃",62),
    Qinghai("青海",63),
    Ningxia("宁夏",64),
    Xinjiang("新疆",65),
    Taiwan("台湾",71),
    Hongkong("香港",81),
    Macao("澳门",82),
    ;
    private final String name;
    private final int id;
    Provinces(String name,int id){
        this.name=name;
        this.id=id;
    }

    public static String toString(int id){
        for(Provinces p :Provinces.values()){
            if(id==p.getId()){
                return p.getName();
            }
        }
        return null;
    }

    public static String toEnglishString(int id){
        for(Provinces p :Provinces.values()){
            if(id==p.getId()){
                return p.toString();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static int getId(String name){
        for(Provinces p :Provinces.values()){
            if(name.equals(p.getName())){
                return p.getId();
            }
        }
        return -1;
    }

}
