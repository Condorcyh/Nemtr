package com.graduation.nemtr.po.majors;

import com.graduation.nemtr.enums.EntranceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "#{@MajorDocumentConfig.mongodbCollectionName()}")
public class MajorProvince {
    @Indexed
    @Field("_id")
    String id;

    @Field("name")
    String name;

    @Field("school_id")
    Long schoolId;

    @Field("理科")
    List<MajorInfo> li;

    @Field("文科")
    List<MajorInfo> wen;

    @Field("物理类")
    List<MajorInfo> physics;

    @Field("历史类")
    List<MajorInfo> history;

    @Field("综合")
    List<MajorInfo> zonghe;

    public List<MajorInfo> getType(EntranceType entranceType){
        if(entranceType.compareTo(EntranceType.LI)==0){
            return li;
        }
        else if(entranceType.compareTo(EntranceType.WEN)==0){
            return wen;
        }
        else if(entranceType.compareTo(EntranceType.ZONGHE)==0){
            return zonghe;
        }
        else if(entranceType.compareTo(EntranceType.PHYSICS)==0){
            return physics;
        }
        else if(entranceType.compareTo(EntranceType.HISTORY)==0){
            return history;
        }
        return li;
    }
}
