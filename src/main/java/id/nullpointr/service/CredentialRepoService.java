package id.nullpointr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.ICredentialRepository;

import id.nullpointr.repository.CredentialRepository;

@Service
public class CredentialRepoService implements ICredentialRepository{
	
	CredentialRepository credentialRepository = null;
	
	@Autowired
	public CredentialRepoService(CredentialRepository credentialRepository) {
		this.credentialRepository = credentialRepository;
	}
	
	@Override
	public String getSecretKey(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
		// TODO Auto-generated method stub
		
	}

}
