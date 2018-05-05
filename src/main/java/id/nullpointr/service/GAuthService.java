package id.nullpointr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

@Service
public class GAuthService {
	private GoogleAuthenticatorKey gauthKey = null;
	
	@Value("${gauth.issuer}")
	String gAuthIssuer;
	
	public GoogleAuthenticator googleAuthenticator = null;
	
	@Autowired
	public GAuthService(GoogleAuthenticator googleAuthenticator){
		this.googleAuthenticator = googleAuthenticator;
	}
	
	public String generateKey(String accountName) {
		gauthKey = googleAuthenticator.createCredentials(accountName);
		GoogleAuthenticatorQRGenerator  gauthQRGenerator = new GoogleAuthenticatorQRGenerator();
		String otpAuthUrl = gauthQRGenerator.getOtpAuthURL(gAuthIssuer, accountName, gauthKey);
		
		return otpAuthUrl;
	}
	
	public boolean authorizeUser(int enteredPassword) {
		boolean isCorrect = googleAuthenticator.authorize(gauthKey.getKey(), enteredPassword);
		
		return isCorrect;
	}
}
