package com.assignment.bluebik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.bluebik.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
