package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.predict.PredictProvince;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictRepository extends MongoRepository<PredictProvince, String> {
    @Query(value = "{'?0.label':?1}}")
    List<PredictProvince> findBySame(String typeName,long label);
}
