package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.CollegeScore;
import com.graduation.nemtr.po.CollegeScoreSimple;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendTempRepository extends MongoRepository<CollegeScoreSimple, String> {
    Optional<CollegeScoreSimple> findBySchoolId(Long schoolId);
}
