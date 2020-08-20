package com.example.demo.repos;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findOneById(long id);

    User findOneByUsernameAndPasswordAndActivated(String username, String password, boolean activated);
}
