//package com.cookbook.security;
//@EnableWebSecurity
//public class CookbookAdventureSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private final CookbookAdventureUserAuthenticationProvider cookbookAdventureUserAuthenticationProvider;
//
//    public CookbookAdventureSecurityConfiguration(CookbookAdventureUserAuthenticationProvider cookbookAdventureUserAuthenticationProvider) {
//        this.cookbookAdventureUserAuthenticationProvider = cookbookAdventureUserAuthenticationProvider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(cookbookAdventureUserAuthenticationProvider);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/api/admin/**").hasRole(Role.ADMIN.toString())
//                .antMatchers("/api/user/**").hasRole(Role.USER.toString())
//                .antMatchers("/api/unauthorized/**").hasRole(Role.UNAUTHORIZED.toString())
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//}
