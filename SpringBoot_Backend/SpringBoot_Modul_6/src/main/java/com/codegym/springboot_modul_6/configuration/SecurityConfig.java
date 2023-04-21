package com.codegym.springboot_modul_6.configuration;
import com.codegym.springboot_modul_6.security.CustomAccessDeniedHandler;
import com.codegym.springboot_modul_6.security.JwtAuthenticationFilter;
import com.codegym.springboot_modul_6.security.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private IAccountService accountService;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**").disable();
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
        http.authorizeRequests()
//                .antMatchers("/api/categories/**", "/api/account/**", "/api/product/get_home").permitAll()
//                .antMatchers( "/api/product/**" ,"/api/cart/**").hasAnyRole("USER","OWNER")
//                .antMatchers( "/api/store/**","/api/productdetail/**").hasAnyRole("OWNER")
////                .antMatchers("/api/product/**","/api/categories/**", "/api/account/**", "/api/product/get_home","/api/store/**","/api/productdetail/**").permitAll()
////                .antMatchers( ).hasAnyRole("USER","OWNER")
////                .antMatchers( "/api/store/**","/api/productdetail/**").hasAnyRole("OWNER")
//                .antMatchers(  "/api/cart/**", "/api/order/**").hasAnyRole("USER")
                .antMatchers("/api/product/**","/api/categories/**", "/api/account/**", "/api/product/get_home","/api/store/**","/api/productdetail/**", "/api/cart/**", "/api/order/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().configurationSource(corsConfigurationSource()).disable();
    }

}

