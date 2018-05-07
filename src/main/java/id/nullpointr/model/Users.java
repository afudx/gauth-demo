package id.nullpointr.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "g_secret_key")
	private String gSecretKey;

	@Column(name = "g_validation_code")
	private int gValidationCode;
	
	@Column(name = "g_is_gauth_enabled")
	private int gIsGauthEnabled;
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGSecretKey() {
		return this.gSecretKey;
	}

	public void setGSecretKey(String gSecretKey) {
		this.gSecretKey = gSecretKey;
	}

	public int getGValidationCode() {
		return this.gValidationCode;
	}

	public void setGValidationCode(int gValidationCode) {
		this.gValidationCode = gValidationCode;
	}
	
	public int getgIsGauthEnabled() {
		return gIsGauthEnabled;
	}

	public void setgIsGauthEnabled(int gIsGauthEnabled) {
		this.gIsGauthEnabled = gIsGauthEnabled;
	}
}
