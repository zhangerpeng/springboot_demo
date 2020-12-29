package jp.co.ntt.usermanager.domain.repository;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ntt.usermanager.domain.model.UserDTO;

@Mapper
public interface UserRepository {
	
	UserDTO getSingleUserInforById(String userId);
	
}
