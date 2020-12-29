package jp.co.ntt.usermanager.domain.service.share;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.ntt.usermanager.domain.model.UserDTO;
import jp.co.ntt.usermanager.domain.repository.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDTO userDetails = userRepository.getSingleUserInforById(userId);
		return userDetails;
	}

}
