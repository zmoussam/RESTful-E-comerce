package dss.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/index", "/index.html", "/login",
								"/css/**", "/js/**", "/images/**", "/webjars/**")
						.permitAll()
						.requestMatchers(HttpMethod.GET, "/productos", "/cart", "/cart/**").permitAll()
						.requestMatchers("/api/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/cart/**").permitAll()
						.requestMatchers("/admin/**",
								"/products/add/**", "/products/edit/**", "/products/delete/**")
						.hasRole("ADMIN")
						.anyRequest().authenticated())

				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/admin", true)
						.failureUrl("/login?error=true") // Redirects back to login on error
						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.permitAll())

				.exceptionHandling(ex -> ex
						.accessDeniedPage("/login?forbidden=true") // Redirect to login if forbidden
				)

				.csrf(csrf -> csrf.ignoringRequestMatchers(
						"/productos/add/**", "/productos/edit/**", "/productos/delete/**", "/admin/**"));

		return http.build();
	}

	@Bean
	WebSecurityCustomizer ignoringCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/h2-console/**");
	}

	@Bean
	public UserDetailsService users() {
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin").password("admin").roles("ADMIN").build();
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user").password("user").roles("USER").build();
		return new InMemoryUserDetailsManager(admin, user);
	}
}
