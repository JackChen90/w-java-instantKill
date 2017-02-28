package indi.jackie.ik;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import java.lang.CharSequence;

/**
 * @author jackie chen
 * @create 2017/01/15
 * @description Main
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    /**
     * 继承{@link SpringBootServletInitializer SpringBootServletInitializer}是通过war包来部署的入口
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SiteConfig.class);
    }

    /**
     * Main 方法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
