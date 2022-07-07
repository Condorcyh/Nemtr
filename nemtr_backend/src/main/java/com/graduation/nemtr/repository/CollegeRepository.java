package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.College;
import com.graduation.nemtr.po.CollegeDescription;
import com.graduation.nemtr.po.CollegeScore;
import com.graduation.nemtr.po.temp.RequestCollegeRank;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CollegeRepository extends MongoRepository<College, String> {

    Optional<College> findBySchoolId(Long schoolId);
    Optional<College> findByName(String name);
    Long countAllByProvinceId(Long provinceId);
    Long countAllByProvinceIdAndIs985(Long provinceId,Long is985);
    Long countAllByProvinceIdAndIs211(Long sprovinceId,Long is211);
    Long countAllByProvinceIdAndLevel(Long provinceId,Long level);
    Long countAllByProvinceIdAndDualClassName(Long provinceId,String dualClassName);


    @Query(value = "{'level_name':'普通本科'}")
    List<RequestCollegeRank> findAllRank();
}
