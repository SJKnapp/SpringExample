package com.example.demo.data.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.data.Duck;

@Repository
public interface DuckRepo extends JpaRepository<Duck, Integer> {
	List<Duck> findByNameIgnoreCase(String name);
}
