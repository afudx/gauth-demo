package id.nullpointr.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import id.nullpointr.model.Users;

public class UserPrincipal implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Users users = null;
	public int isGAuthEnabled = 0;
	
	public UserPrincipal(Users users){
		this.users = users;
		this.isGAuthEnabled = users.getgIsGauthEnabled();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		return users.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public int isGAuthEnabled() {
		return this.isGAuthEnabled;
	}
}
