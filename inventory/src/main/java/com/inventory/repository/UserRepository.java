package com.inventory.repository;

import com.inventory.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Owner, Integer> {

    List<Owner> findAllByEmail(String email);
}
