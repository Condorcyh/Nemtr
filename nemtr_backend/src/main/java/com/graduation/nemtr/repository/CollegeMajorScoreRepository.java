package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.majors.MajorProvince;
import com.graduation.nemtr.po.predict.PredictProvince;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollegeMajorScoreRepository extends MongoRepository<MajorProvince, String> {
    Optional<MajorProvince> findBySchoolId(Long schoolId);
}
