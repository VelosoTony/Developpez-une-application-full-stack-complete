package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
