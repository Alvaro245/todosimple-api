package com.alvarotrindade.demo.repositories;


import com.alvarotrindade.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
