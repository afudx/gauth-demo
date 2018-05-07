package id.nullpointr.gauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.ICredentialRepository;

@Configuration
public class GAuthConfig {
	
	ICredentialRepository credentialRepository;
	
	@Autowired
	public GAuthConfig(ICredentialRepository credentialRepository) {
		this.credentialRepository = credentialRepository;
	}
	
	@Bean
	public GoogleAuthenticator googleAuthenticator() {
		GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
		googleAuthenticator.setCredentialRepository(credentialRepository);
		
		return googleAuthenticator;
	}
}
