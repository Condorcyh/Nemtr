package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.College;
import com.graduation.nemtr.po.ProvinceInfo;
import com.graduation.nemtr.po.ProvinceLayer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceInfoRepository extends MongoRepository<ProvinceInfo, String> {
    Optional<ProvinceInfo> findByProvinceId(Long provinceId);
}
