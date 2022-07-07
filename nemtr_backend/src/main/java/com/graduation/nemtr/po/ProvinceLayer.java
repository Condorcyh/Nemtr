package com.graduation.nemtr.po;

import com.graduation.nemtr.enums.EntranceType;
import com.graduation.nemtr.po.inner.ProvinceBatchInner;
import com.graduation.nemtr.po.inner.ProvinceLayerInner;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "province_layer")
public class ProvinceLayer {
    @Indexed
    @Field("_id")
    String id;

    @Field("province_id")
    Long provinceId;

    @Field("province_name")
    String provinceName;

    @Field("level")
    List<ProvinceLayerInner> level;

    public Long getSameLevel(EntranceType entranceType,Long score){
        for(ProvinceLayerInner provinceLayerInner:level){
            if(provinceLayerInner.getBatchName().equals(entranceType.getName())){
                return provinceLayerInner.getSameLevel(score);
            }
        }
        return 0L;
    }

}
