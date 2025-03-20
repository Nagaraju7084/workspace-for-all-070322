package com.medilab.preclinic.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MediSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private MediUserAuthenticationProvider mediAuthnProvider;

	// default generated user and password
	protected void configure(HttpSecurity http) throws Exception {
		// HttpSecurity is used to define the urls to protected and define the
		// authentication method
		// and also to define the various application artifacts like cors is required to
		// enable
		// csrf is required to be enable(csrf is by default enable by spring security
		// 4.x onwords)
		// super.configure(http);
		http
			.addFilterBefore(new MediUserCheckingFilter(), UsernamePasswordAuthenticationFilter.class)
			.csrf().csrfTokenRepository(new CookieCsrfTokenRepository().withHttpOnlyFalse())
			.and()
			.authorizeRequests()
			.antMatchers("/home").permitAll()
			.antMatchers("/dashboard").authenticated()
			.antMatchers("/doctors").hasRole("doctor")//should be same in db
			.antMatchers("/api/*").authenticated()
			.and()
			.formLogin()
			.and()
			.httpBasic(); // from rest services using http header in request
	}

	// custom generated user and password
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { //AuthenticationManagerBuilder is used to build mostly for the
	 * custom authentication provider and custom user details manager and inmemory
	 * user details manager //AuthenticationManagerBuilder is used to build the
	 * authentication manager that authentication manager will provides the
	 * authentication details to the //authentication provider
	 * //AuthenticationManagerBuilder is also used to defined user details manager
	 * to create the user schema
	 * 
	 * //1.inmemory auth .inMemoryAuthentication()
	 * .withUser("admin").password("admin").authorities("admin").and()
	 * .withUser("user").password("user").authorities("user");//.and()
	 * //.passwordEncoder(NoOpPasswordEncoder.getInstance());// it won't encode the
	 * password while storing the users into db
	 * 
	 * * }
	 */
	// 2.database //auth.jdbcAuthentication().dataSource(dataSource);

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(mediAuthnProvider);
		// auth.jdbcAuthentication().dataSource(dataSource);
	}

	// 3.useing UserDetailsService
	/*
	 * @Bean public UserDetailsService userDetailsService() { //inmemeory user
	 * details manager
	 * 
	 * InMemoryUserDetailsManager inMemoryManager = new
	 * InMemoryUserDetailsManager(); UserDetails user1 = new User("admin", "admin",
	 * Arrays.asList(new SimpleGrantedAuthority("admin"))); UserDetails user2 = new
	 * User("user", "user", Arrays.asList(new SimpleGrantedAuthority("user")));
	 * 
	 * inMemoryManager.createUser(user1); inMemoryManager.createUser(user2);
	 * 
	 * return inMemoryManager;
	 * 
	 * //jdbc user details manager JdbcUserDetailsManager jdbcManager = new
	 * JdbcUserDetailsManager(dataSource); return jdbcManager; }
	 */

	@Bean
	public PasswordEncoder passWordEncoder() {
		// return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder(5);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {// this configure web used for ignoring
																// the security check on the static resources
		// TODO Auto-generated method stub
		// super.configure(web);
		web.ignoring().antMatchers("/assets/**");
	}

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder(5).encode("12345"));
	}

}
