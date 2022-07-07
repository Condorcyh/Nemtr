package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.ProvinceInfo;
import com.graduation.nemtr.po.ProvinceLayer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceLayerRepository extends MongoRepository<ProvinceLayer, String> {
    Optional<ProvinceLayer> findByProvinceId(Long provinceId);
}
