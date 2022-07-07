package com.graduation.nemtr.po;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "college_description")
public class CollegeDescription {
    @Indexed
    @Field("_id")
    String id;
    @Field("name")
    String name;
    @Field("school_id")
    Long schoolId;
    @Field("description")
    String description;
}
