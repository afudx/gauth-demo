package id.nullpointr.gauth;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.ICredentialRepository;

import id.nullpointr.model.Users;
import id.nullpointr.repository.UsersRepository;

@Service
public class CredentialRepoService implements ICredentialRepository{
	private static final Logger logger = LoggerFactory.getLogger(CredentialRepoService.class);

	UsersRepository usersRepository = null;
	
	@Autowired
	public CredentialRepoService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@Override
	public String getSecretKey(String email) {
		Users users = usersRepository.findByEmail(email);
		return users.getGSecretKey();
	}

	@Override
	public void saveUserCredentials(String email, String gSecretKey, int gValidationCode, List<Integer> scratchCodes) {
		Users users = usersRepository.findByEmail(email);
		users.setGSecretKey(gSecretKey);
		users.setGValidationCode(gValidationCode);
		
		usersRepository.save(users);
	}
	
	public void updateEnabledGAuth(String email, int status) {
		Users users = usersRepository.findByEmail(email);
		users.setgIsGauthEnabled(status);
		
		usersRepository.save(users);
	}

}
