package std.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import std.java.enumcase.ApiInfoMang;

@Component
@Slf4j
public class TestRunner implements ApplicationRunner {

	@Value("${api.getMemberInfo.url}")
	private String apiUrl;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		log.debug("====================================================================");
		log.debug(ApiInfoMang.getMemberInfo.getDescription());
		log.debug(ApiInfoMang.getPointList.getDescription());
		log.debug(ApiInfoMang.getMemberInfo.getUrl());
		log.debug("xxxxxxxxxxxxxxxxxxxx" +  ApiInfoMang.getMemberInfo);
		ApiInfoMang.getMemberInfo.setUrl(apiUrl);
		log.debug(apiUrl);
		log.debug(ApiInfoMang.getMemberInfo.getUrl());
		log.debug("=====================https://github.com/proper-for-me/std-spring-boot/edit/develop/boot-test/src/main/java/std/runner/TestRunner.java"
			  +"============원본에서 수정===============================");
	}


}
