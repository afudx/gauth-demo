package id.nullpointr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserDetailsServiceCustom userDetailsServiceCustom;
	private AuthenticationSuccessHandlerCustom authenticationSuccessHandlerCustom;
	
	@Autowired
	public SecurityConfig(UserDetailsServiceCustom userDetailsServiceCustom, AuthenticationSuccessHandlerCustom authenticationSuccessHandlerCustom) {
		this.userDetailsServiceCustom = userDetailsServiceCustom;
		this.authenticationSuccessHandlerCustom = authenticationSuccessHandlerCustom;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/vendor/**", "/login").permitAll()
				.antMatchers("/gauth-register").access("principal.isGAuthEnabled == 0 ? permitAll : denyAll")
				.antMatchers("/**").authenticated()	
				.and()
			.formLogin()
				.loginProcessingUrl("/login-process")
				.usernameParameter("email")
				.passwordParameter("password")
				.successForwardUrl("/gauth")
				.loginPage("/login").failureUrl("/login?error").successHandler(authenticationSuccessHandlerCustom)
				.and()
			.logout()
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login");
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsServiceCustom);
	    authProvider.setPasswordEncoder(bCryptEncoder());
	    return authProvider;
	}
	
	public PasswordEncoder bCryptEncoder() {
	    return new BCryptPasswordEncoder();
	}
}