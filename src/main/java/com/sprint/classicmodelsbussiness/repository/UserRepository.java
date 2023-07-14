package com.sprint.classicmodelsbussiness.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.classicmodelsbussiness.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByName(String name);
}
