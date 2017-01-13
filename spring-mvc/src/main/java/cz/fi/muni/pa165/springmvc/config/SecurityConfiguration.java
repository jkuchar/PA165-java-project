package cz.fi.muni.pa165.springmvc.config;

import cz.fi.muni.pa165.service.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author rtrembecky
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"cz.fi.muni.pa165.service.security", "cz.fi.muni.pa165.springmvc.config"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/logout").permitAll()
                .antMatchers("/car/list**", "/car/view**", "/records/list**").hasRole("USER")
                .antMatchers("/user/list**", "/user/view**",
                        "/car/new**", "/car/create**", "/car/service**", "/car/ok**", "/car/discard**",
                        "/approved/**", "/rejected/**", "/application/**", "/rent/**", "/returned/**",
                        "/records/create**", "/records/add**"
                        ).hasRole("MANAGER")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}
