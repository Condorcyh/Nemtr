package com.graduation.nemtr.po.inner;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "province_layer")
public class ProvinceLayerInner {

    @Field("id")
    String batchId;

    @Field("name")
    String batchName;

    @Field("layer")
    List<ProvinceLayerNumbersInner> layer;

    public Long getSameLevel(Long score){
        for(ProvinceLayerNumbersInner p:layer){
            if(p.IsSameScore(score)){
                return Long.parseLong(p.total);
            }
        }
        return Long.parseLong(layer.get(layer.size()-1).total);
    }

}
