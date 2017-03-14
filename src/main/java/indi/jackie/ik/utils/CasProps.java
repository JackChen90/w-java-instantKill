package indi.jackie.ik.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jackie chen
 * @create 2017-03-06
 * @description cas properties in application.yml
 */
@Component
@ConfigurationProperties(prefix = "cas")
public class CasProps {
    /**
     * client app name
     */
    private String serverName;

    /**
     * cas login url
     */
    private String casServerLoginUrl;

    /**
     * url that is not sent to cas
     */
    private String excludedUrl;

    /**
     * cas server url
     */
    private String casServerUrlPrefix;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getCasServerLoginUrl() {
        return casServerLoginUrl;
    }

    public void setCasServerLoginUrl(String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public String getExcludedUrl() {
        return excludedUrl;
    }

    public void setExcludedUrl(String excludedUrl) {
        this.excludedUrl = excludedUrl;
    }

    public String getCasServerUrlPrefix() {
        return casServerUrlPrefix;
    }

    public void setCasServerUrlPrefix(String casServerUrlPrefix) {
        this.casServerUrlPrefix = casServerUrlPrefix;
    }
}
