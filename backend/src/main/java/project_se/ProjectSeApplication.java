package project_se;

import entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repos.TestRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class ProjectSeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSeApplication.class, args);

	}




}
