package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.College;
import com.graduation.nemtr.po.Major;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MajorRepository extends MongoRepository<Major, String> {
}
