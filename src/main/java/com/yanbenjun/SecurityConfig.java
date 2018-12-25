package com.yanbenjun;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * https://blog.csdn.net/a1439226817/article/details/77833230
 * @author Administrator
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity //这里如果放开js文件将不被允许执行，注释掉也没有影响，不知道为什么
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomSuccessHandler customSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index.jsp").permitAll()
                //.antMatchers("/static/**").permitAll()  //  允许所有用户对根路径以及匹配"/static/"开头的路径的访问
                //.antMatchers("/", "/index").hasRole("TEACHER")
                .anyRequest().authenticated()   //  任何尚未匹配的的URL地址只需要对用户进行权限验证
                .and()
                .formLogin()
                    .successHandler(customSuccessHandler)
                    //.failureUrl("/login?error=true")
                    //.defaultSuccessUrl("/home")
                    //.defaultSuccessUrl("/swagger-ui.html")  //  登陆成功后默认默认跳转到swagger页
                    .permitAll()
                    .and()
                    .rememberMe().tokenValiditySeconds(604800);
        http.logout();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //  将验证过程交给自定义的授权认证类
        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(this.userDetailsService());
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new AuthenticationProviderCustom();
    }
}

