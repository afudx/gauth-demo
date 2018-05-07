package id.nullpointr.gauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

import id.nullpointr.utils.AppConstants;

@Service
public class GAuthService {
	@Value("${gauth.issuer}")
	String gAuthIssuer;
	
	private GoogleAuthenticator googleAuthenticator;
	private CredentialRepoService credentialRepoService;
	
	@Autowired
	public GAuthService(GoogleAuthenticator googleAuthenticator, CredentialRepoService credentialRepoService){
		this.googleAuthenticator = googleAuthenticator;
		this.credentialRepoService = credentialRepoService;
	}
	
	public String generateKey(String accountName) {
		final GoogleAuthenticatorKey gauthKey = googleAuthenticator.createCredentials(accountName);
		String otpAuthUrl = GoogleAuthenticatorQRGenerator.getOtpAuthURL(gAuthIssuer, accountName, gauthKey);
		
		return otpAuthUrl;
	}
	
	public boolean authorizeUser(String email, int clientKey) {
		String gSecretKey = credentialRepoService.getSecretKey(email);
		boolean isCorrect = googleAuthenticator.authorize(gSecretKey, clientKey);
		if(isCorrect)
			credentialRepoService.updateEnabledGAuth(email, AppConstants.G_AUTH_ENABLED);
		
		return isCorrect;
	}
}
