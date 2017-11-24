
package me.u6k.sample.sample_spring_social_twitter.web;

import javax.inject.Inject;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private DataSource dataSource;

    @Autowired
    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        log.trace("#registerAuthentication: start");

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, true from Account where username = ?")
            .authoritiesByUsernameQuery("select username, 'ROLE_USER' from Account where username = ?")
            .passwordEncoder(passwordEncoder());

        log.trace("#registerAuthentication: end");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        log.trace("#configure(WebSecurity): start");

        web
            .ignoring()
            .antMatchers("/**/*.css", "/**/*.png", "/**/*.gif", "/**/*.jpg");

        log.trace("#configure: end");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.trace("#configure(HttpSecurity): start");

        http
            .formLogin()
            .loginPage("/signin")
            .loginProcessingUrl("/signin/authenticate")
            .defaultSuccessUrl("/connect")
            .failureUrl("/signin?param.error=bad_credentials")

            .and()
            .logout()
            .logoutUrl("/signout")
            .deleteCookies("JSESSIONID")

            .and()
            .authorizeRequests()
            .antMatchers("/", "/webjars/**", "/admin/**", "/favicon.ico", "/resources/**", "/auth/**", "/signin/**", "/signup/**", "/disconnect/facebook").permitAll()
            .antMatchers("/**").authenticated()

            .and()
            .rememberMe();

        log.trace("#configure: end");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.trace("#passwordEncoder: start,end");
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public TextEncryptor textEncryptor() {
        log.trace("#textEncryptor: start,end");
        return Encryptors.noOpText();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        log.trace("#springSecurityDialect: start,end");
        return new SpringSecurityDialect();
    }

}
