package com.graduation.nemtr.po.inner;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "province_layer")
public class ProvinceLayerNumbersInner {

    @Field("score")
    String score;

    @Field("num")
    String num;

    @Field("total")
    String total;

    @Field("rank")
    String rank;

    public Boolean IsSameScore(Long score){
        if(this.score.contains("-")){
            String[] res = this.score.split("-");
            return score >= Long.parseLong(res[0]) && score <= Long.parseLong(res[1]);
        }
        else {
            return score==Long.parseLong(this.score);
        }
    }

}
