package by.artezio.hackathon;

import by.artezio.hackathon.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "by.artezio.hackathon.repository")
@EnableTransactionManagement
@EnableJpaAuditing
public class HackathonApplication {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserSecurityService userSecurityService;

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
	@Bean
	protected PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}

	@Bean
	protected WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
		return new WebSecurityConfigurerAdapter() {
			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http.exceptionHandling()
						.and().authorizeRequests().antMatchers("/").permitAll()
						.antMatchers("/task", "/progress", "/task/*", "/progress/*").authenticated()
						.and().formLogin().loginPage("/").usernameParameter("login").defaultSuccessUrl("/progress").failureUrl("/?error")
						.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
				http.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400);
				http.csrf().disable();
			}
		};
	}
}
