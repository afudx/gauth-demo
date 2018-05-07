package id.nullpointr.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.nullpointr.model.Users;
import id.nullpointr.repository.UsersRepository;

@Service
public class UserDetailsServiceCustom implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceCustom.class);

	UsersRepository usersRepository = null;
	
	@Autowired
	public UserDetailsServiceCustom(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		logger.info("Authenticating user: "+email);
		Users users = usersRepository.findByEmail(email);
		if (users == null) {
            throw new UsernameNotFoundException(email);
        }
		
		logger.info("Login successful for user: "+email);

		return new UserPrincipal(users);
	}

}
