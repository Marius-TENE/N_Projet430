package agpe.authentification.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void globalCinfig(AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception{
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select login as principal,password as credentials,true from utilisateur where login = ?")
		.authoritiesByUsernameQuery("select login as principal, role as role from utilisateur where login = ?")
		.rolePrefix("ROLE_");
	}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(
                            "/connexion**",
                            "/motpasse-oublie**",
                            "/echecConnexion",
                            "/restaurer-mot-passe**").permitAll()
                    .antMatchers(
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/vendor/**",
                            "/dist/**",
                            "/webjars/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/connexion")
                        .defaultSuccessUrl("/determinerRole")
                        .failureUrl("/echecConnexion")
                        .permitAll()
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/deconnexion"))
                        .logoutSuccessUrl("/connexion")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/nonAutorise");
        		
    }

	
	  @Bean 
	  public BCryptPasswordEncoder passwordEncoder(){ 
		  return new BCryptPasswordEncoder();
	  }
	 
}