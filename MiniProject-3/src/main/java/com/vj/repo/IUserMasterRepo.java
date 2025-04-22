package com.vj.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vj.entity.UserMaster;

public interface IUserMasterRepo extends JpaRepository<UserMaster, Integer> {
	
	
	public UserMaster findByEmailAndPassword(String email,String password);
	
	public UserMaster findByEmail(String email);
	
	public UserMaster findByNameAndEmail(String name,String email);
	
}
