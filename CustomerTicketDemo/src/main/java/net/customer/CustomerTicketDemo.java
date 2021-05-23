package net.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class })
//@ComponentScan({ "src.main.java.net.customer.*" })
@EnableScheduling
@EnableAsync
public class CustomerTicketDemo {
    public static void main(String[] args) {
        SpringApplication.run(CustomerTicketDemo.class, args);
    }
}
