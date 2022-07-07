package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.College;
import com.graduation.nemtr.po.CollegeDescription;
import com.graduation.nemtr.po.CollegeScore;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollegeScoreRepository extends MongoRepository<CollegeScore, String> {
    Optional<CollegeScore> findBySchoolId(Long schoolId);
}
