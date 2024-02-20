package com.socialmedia.services.repository;
import com.socialmedia.services.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {}