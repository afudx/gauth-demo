package id.nullpointr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.warrenstrange.googleauth.GoogleAuthenticator;

@Configuration
public class GAuthConfig {
	
	@Bean
	public GoogleAuthenticator googleAuthenticator() {
		GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
		
		return googleAuthenticator;
	}
}
