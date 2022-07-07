package com.graduation.nemtr.repository;
import com.graduation.nemtr.po.Major;
import com.graduation.nemtr.po.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByNameAndPassword(String name,String pwd);
    Optional<User> findByPhoneAndPassword(String phone,String pwd);
    Optional<User> findByName(String name);
}
