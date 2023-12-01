package com.crud.crudspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crud.crudspring.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByUsernameAndPassword(String username, String password);

   /*  Optional<User> findByUsername(String username); */

    User findByUsername(String username);

    User findByEmail(String email);

}
