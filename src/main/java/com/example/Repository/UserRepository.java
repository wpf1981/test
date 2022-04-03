package com.example.Repository;

import com.example.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "test2")
public interface UserRepository extends JpaRepository<User,String> {
}
