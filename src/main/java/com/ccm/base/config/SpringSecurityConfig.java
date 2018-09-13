package com.ccm.base.config;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/9/11 10:12
 * @Description: spring security 框架配置文件,暂时无用,选取了shiro
 */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
public class SpringSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

    /*@Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers("/**.html","/**.css", "/img/**", "/**.js","/static/**");

    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/**","/","/login","/swagger-ui.html","api").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();

        httpSecurity.csrf().disable();
    }*/

//    @Bean
//    public Md5PasswordEncoder passwordEncoder(){
//        return new new Md5PasswordEncoder();
//    }
}
