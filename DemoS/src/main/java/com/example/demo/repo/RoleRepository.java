package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

	RoleEntity findByName(String name);

}
