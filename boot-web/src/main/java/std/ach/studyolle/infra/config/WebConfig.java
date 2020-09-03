package std.ach.studyolle.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@RequiredArgsConstructor
//public class WebConfig implements WebMvcConfigurer {
//
//    private final NotificationInterceptor notificationInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> staticResourcesPath = Arrays.stream(StaticResourceLocation.values())
//                .flatMap(StaticResourceLocation::getPatterns)
//                .collect(Collectors.toList());
//        staticResourcesPath.add("/node_modules/**");
//
//        registry.addInterceptor(notificationInterceptor)
//            .excludePathPatterns(staticResourcesPath);
//    }
//}

@Configuration
public class WebConfig implements WebMvcConfigurer {

  // 요청 - 뷰 연결
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("main");
 //   registry.addViewController("/login").setViewName("login");
    registry.addViewController("/admin").setViewName("admin");
    registry.addViewController("/signup").setViewName("signup");
  }
}