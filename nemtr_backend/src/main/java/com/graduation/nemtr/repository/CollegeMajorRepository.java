package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.College;
import com.graduation.nemtr.po.CollegeMajor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollegeMajorRepository extends MongoRepository<CollegeMajor, String> {
    Optional<CollegeMajor> findBySchoolId(Long schoolId);
}
