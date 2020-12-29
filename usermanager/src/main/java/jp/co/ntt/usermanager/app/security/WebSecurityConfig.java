package jp.co.ntt.usermanager.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import jp.co.ntt.usermanager.app.security.hander.authen.UserAuthenticationFailureHandler;
import jp.co.ntt.usermanager.app.security.hander.authen.UserAuthenticationSuccessHandler;
import jp.co.ntt.usermanager.domain.service.share.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	// 认证成功时触发的handler
	@Autowired
	private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

	// 认证成功时触发的handler
	@Autowired
	private UserAuthenticationFailureHandler userAuthenticationFailureHandler;

	// 异常【There is no PasswordEncoder mapped for the id "null"】
	/**
	 * 原因： 这个错主要发生在Spring-Sercurity5.X版本上， 例如SpringBoot2.x。
	 * 导致这个错误发生主要原因就是在之前版本中的NoOpPasswordEncoder被DelegatingPasswordEncoder取代了，
	 * 而保存在数据库中的密码没有没有指定加密方式。
	 * 
	 * @return
	 */
	public NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 1.表单登录
		http.formLogin()// 表单登录 来身份认证时，关于首页必须准许访问
				.loginPage("/login.html").permitAll().loginProcessingUrl("/authentication/login")// 自定义登录路径
				.passwordParameter("password").usernameParameter("userId")
				.successHandler(userAuthenticationSuccessHandler).failureHandler(userAuthenticationFailureHandler)
				// .successForwardUrl("/login/success")
				// .failureForwardUrl("/login/failure")
				.and()

				// 2.配置过滤请求
				.authorizeRequests()// 对请求授权
				.antMatchers("/login.html", "/authentication/login","/login/systemError","/login/badCredentials","/login/disabled").permitAll()
				.antMatchers("/user/register").hasRole("ADMIN")
				.antMatchers("/user/update").hasRole("ADMIN")
				.antMatchers("/user/delete")
				.hasRole("ADMIN")
				.anyRequest()// 任何请求
				.authenticated()// ; // 都需要身份认证
				.and().csrf().disable()

				// 3.logout 的配置
				.logout().logoutUrl("/user/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").and()

				// session管理
				.sessionManagement().invalidSessionUrl("/user/logout")
				// 防止多重登录
				.maximumSessions(1).and();

	}

	/**
	 * 配置忽略静态文件
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/webjars/**");
	}

}
