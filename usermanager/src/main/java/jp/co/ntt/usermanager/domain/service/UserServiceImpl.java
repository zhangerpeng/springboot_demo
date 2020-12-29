package jp.co.ntt.usermanager.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.ntt.usermanager.domain.model.UserDTO;
import jp.co.ntt.usermanager.domain.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
	private UserRepository userRepositroy;
	@Override
	public UserDTO singleUserInforById(String userId) {
		// TODO Auto-generated method stub
		UserDTO user =userRepositroy.getSingleUserInforById("0003");
		return user;
	}

}
