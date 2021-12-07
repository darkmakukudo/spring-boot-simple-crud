package com.rest.hibernate.resthibernatecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.hibernate.resthibernatecrud.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
}
