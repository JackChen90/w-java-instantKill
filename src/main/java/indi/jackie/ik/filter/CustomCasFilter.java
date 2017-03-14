package indi.jackie.ik.filter;

import org.jasig.cas.client.authentication.AuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author jackie chen
 * @create 2017-01-24
 * @description 自定义Cas过滤器
 */
public class CustomCasFilter implements Filter {

    private final String KEY_CAS_EXCLUDED_URL = "excludedUrl";
    AuthenticationFilter authenticationFilter = new AuthenticationFilter();
    private String CasExcludedUrls;

    /**
     * 去除URL定义中的换行（\n）
     *
     * @param excludedUrl 无需经过CAS的URL(带换行符\n)
     * @return excludedUrl 无需经过CAS的URL(去换行符\n)
     */
    private String jointString(String excludedUrl) {
        StringBuilder result = null;
        String[] resultArray = excludedUrl.split("\n");
        for (int i = 0; i < resultArray.length; i++) {
            result.append(resultArray[i]);
        }
        return result.toString();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authenticationFilter.init(filterConfig);
        //获取Filter初始化参数
        this.CasExcludedUrls = jointString(filterConfig.getInitParameter(KEY_CAS_EXCLUDED_URL));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //请求路径
        String path = ((HttpServletRequest) request).getRequestURI();
        //若路径属于无需过cas的类型，则不走cas的请求；否则走cas校验
        if (path.matches(this.CasExcludedUrls)) {
            chain.doFilter(request, response);
        } else {
            authenticationFilter.doFilter(request, response, chain);
        }
    }

    @Override
    public void destroy() {
        authenticationFilter.destroy();
    }
}
