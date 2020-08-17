package std.ach.studyolle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootWebApp {
	
	public static void main(String[] args) {
		System.out.println("run");
		SpringApplication.run(BootWebApp.class, args);
	}
}
