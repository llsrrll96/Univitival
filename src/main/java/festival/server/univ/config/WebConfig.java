package festival.server.univ.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("resources/**") // 핸들러 추가
                .addResourceLocations("classpath:/static/") // 클래스패스 설정시 끝에 꼭 / 넣어주자.
                .setCachePeriod(20); // 초단위
    }
}
