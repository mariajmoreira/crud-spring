package com.crud.crudspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.crudspring.model.Calculations;
import com.crud.crudspring.model.Client;
import com.crud.crudspring.model.User;

@Repository
public interface ClientsRepository extends JpaRepository<Client,Long> {
     Optional<Client> findByUserid(Long userId);
}
