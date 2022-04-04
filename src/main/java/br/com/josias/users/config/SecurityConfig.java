package br.com.josias.users.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.josias.users.service.UserService;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		//Em banco de dados
		auth.userDetailsService(userService)
					.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/h2").permitAll()
				.antMatchers("/h2/**").permitAll()
				.antMatchers("/h2/**.html").permitAll()
				.antMatchers("/h2/**.css").permitAll()
				.antMatchers("/h2/**.js").permitAll()
				.antMatchers("/h2/**.**").permitAll()
				.antMatchers("/api/users/registration").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/swagger-ui/**").permitAll()
				.antMatchers("/swagger-ui/**.css").permitAll()
				.antMatchers("/swagger-ui/**.js").permitAll()
				.antMatchers("/swagger-ui/**.png").permitAll()
				.antMatchers("/swagger-ui/**.svg").permitAll()
				.antMatchers("/swagger-ui/**.**").permitAll()
				.antMatchers("/v3/api-docs").permitAll()
				.antMatchers("/v3/api-docs/**").permitAll()
				.antMatchers("/v3/api-docs/swagger-config").permitAll()
				.antMatchers("/v3/api-docs/swagger-config/**").permitAll()
				.antMatchers("/v3/api-docs/swagger-config/**.css").permitAll()
				.antMatchers("/v3/api-docs/swagger-config/**.js").permitAll()
				.antMatchers("/v3/api-docs/swagger-config/**.png").permitAll()
				.antMatchers("/v3/api-docs/swagger-config/**.svg").permitAll()
				.antMatchers("/v3/api-docs/swagger-config/**.**").permitAll()
				.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.and()
				.httpBasic();
	}
	
	
	
}