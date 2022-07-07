package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.Rate;
import com.graduation.nemtr.po.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends MongoRepository<Rate, String> {
    Boolean existsByUserIdAndMajorId(String userId,Long majorId);
    List<Rate> findAllByUserId(String userId);
    List<Rate> findAllByMajorId(Long majorId);
    Long countRatesByMajorId(Long majorId);
}
