package com.lazy.mybatis.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity    // 开启Spring Security
//@EnableGlobalMethodSecurity(prePostEnabled = true)	//开启方法级的权限注解  性设置后控制器层的方法前的@PreAuthorize("hasRole('admin')") 注解才能起效
public class WebSecurityConfig {//extends WebSecurityConfigurerAdapter {

    /*@Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }*/
}
