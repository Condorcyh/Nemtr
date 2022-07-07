package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.CollegeDescription;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollegeDescriptionRepository extends MongoRepository<CollegeDescription, String> {
    Optional<CollegeDescription> findBySchoolId(Long schoolId);

    @Query(value = "{'name':?0}", fields = "{'school_id':1}")
    Optional<CollegeDescription> findByName(String schoolName);

    @Query(fields = "{'description':0}")
    List<CollegeDescription> findByNameContains(String name);
}
