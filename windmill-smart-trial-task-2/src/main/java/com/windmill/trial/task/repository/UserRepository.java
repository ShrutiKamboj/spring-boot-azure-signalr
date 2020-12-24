package com.windmill.trial.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.windmill.trial.task.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String>{

}
