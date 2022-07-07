package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.CollegePlan;
import com.graduation.nemtr.po.CollegeScoreSimple;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollegePlanRepository extends MongoRepository<CollegePlan, String> {
    Optional<CollegePlan> findBySchoolId(Long schoolId);
}
