package cn.reimbursement.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
	@Autowired
	private LoginInterceptor loginInterceptor;

	// 这个方法是用来配置静态资源的，比如html，js，css，等等
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	}

	// 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/staff/loginByIdAndPassword","/view/toLogin");
	}
}
