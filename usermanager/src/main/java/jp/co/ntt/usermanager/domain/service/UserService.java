package jp.co.ntt.usermanager.domain.service;

import jp.co.ntt.usermanager.domain.model.UserDTO;

public interface UserService {
	UserDTO singleUserInforById(String userId);

}
