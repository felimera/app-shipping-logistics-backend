package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findOneByEmail(String email);
}
