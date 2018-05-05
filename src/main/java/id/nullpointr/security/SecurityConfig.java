package id.nullpointr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/vendor/**", "/login").permitAll()		
				.antMatchers("/**").hasRole("USER")			
				.and()
			.formLogin()
				.loginProcessingUrl("/login-process")
				.usernameParameter("email")
				.passwordParameter("password")
				.successForwardUrl("/gauth")
				.loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/gauth")
				.and()
			.logout()
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
				.withUser("user@domain.com").password("user123").roles("USER");
	}
}