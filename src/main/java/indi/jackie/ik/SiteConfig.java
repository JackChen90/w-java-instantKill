package indi.jackie.ik;

import indi.jackie.ik.filter.CustomCasFilter;
import indi.jackie.ik.filter.CustomCasSignOutFilter;
import indi.jackie.ik.utils.CasProps;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

/**
 * @author jackie chen
 * @create 2017-01-19
 * @description Site Config
 */
@Configuration
public class SiteConfig extends WebMvcConfigurerAdapter {

    @Autowired
    CasProps casProps;

    /**
     * ContextListener for cas sign out filter
     *
     * @return ContextListener
     */
    @Bean
    public ServletListenerRegistrationBean singleSignOutHttpSessionListenerRegistration() {
        ServletListenerRegistrationBean registration = new ServletListenerRegistrationBean();
        registration.setListener(new SingleSignOutHttpSessionListener());
        return registration;
    }

    /**
     * cas sign out filter
     *
     * @return sign out filter
     */
    @Bean
    public FilterRegistrationBean casSignOutFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SingleSignOutFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("casServerUrlPrefix", casProps.getCasServerUrlPrefix());
        registration.setName("casSignOutFilter");
        registration.setOrder(1);
        return registration;
    }

//    @Bean(name = "casSignOutFilter")
//    public Filter customCasSignOutFilter() {
//        return new CustomCasSignOutFilter();
//    }


    /**
     * cas filter
     *
     * @return cas filter
     */
    @Bean
    public FilterRegistrationBean casFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(customCasFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("casServerLoginUrl", casProps.getCasServerLoginUrl());
        registration.addInitParameter("serverName", casProps.getServerName());
        registration.addInitParameter("excludedUrl", casProps.getExcludedUrl());
        registration.setName("customCasFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean(name = "customCasFilter")
    public Filter customCasFilter() {
        return new CustomCasFilter();
    }

    /**
     * cas ticket validate filter
     *
     * @return ticket validate filter
     */
    @Bean
    public FilterRegistrationBean casTicketValidationFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("casServerUrlPrefix", casProps.getCasServerUrlPrefix());
        registration.addInitParameter("serverName", casProps.getServerName());
        registration.setOrder(3);
        return registration;
    }

    /**
     * HttpRequest wrapper filter
     * to make request.getRemoteUser()&request.getUserPrincipal() method can use
     *
     * @return wrapper filter
     */
    @Bean
    public FilterRegistrationBean casWrapperFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(4);
        return registration;
    }

//    @Bean
//    public Filter characterEncodingFilter() {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        return characterEncodingFilter;
//    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //不知是否因为springmvc的bug，responsebody注解，编码方式默认是ISO
        Iterator<HttpMessageConverter<?>> it = converters.iterator();
        while (it.hasNext()) {
            HttpMessageConverter<?> converter = it.next();
            if (converter instanceof StringHttpMessageConverter) {
                it.remove();
            }
        }
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }
}
