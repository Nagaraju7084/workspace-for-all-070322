/**
 * 
 */
package com.medi.preclinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.medi.preclinic.config.filter.MedilabUserAuthzFilter;


/**
 * @author IM-LP-1763
 *
 */
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MedilabSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MedilabUserAuthenticationProvider medilabAuthnProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		 .authorizeRequests()
		 .antMatchers(HttpMethod.POST,"/api/users")
		 .permitAll()
		 .anyRequest().authenticated()
		 //.antMatchers("/api/users").permitAll()
		 .and().csrf().disable()
		 .addFilterBefore(medilabUserAuthzFilter(), BasicAuthenticationFilter.class)
		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 .and()
		 //.formLogin().disable()
		 .formLogin().and()
		 .httpBasic();
		
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(medilabAuthnProvider);
		
	}
	 

	@Bean
	public PasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder(12);
	}
	
	@Bean
	public MedilabUserAuthzFilter medilabUserAuthzFilter() {
		return new MedilabUserAuthzFilter();
	}

}
