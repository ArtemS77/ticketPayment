package net.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class })
@EnableScheduling
@EnableCaching(proxyTargetClass = true)
@EnableAsync
public class CustomerTicketDemo {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CustomerTicketDemo.class, args);

        DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }uhhhjhjhj

   // dhfdjfhdjfhdjfhjdfhjdfhdjhf
        wertyuhgfrdfghjhgfdfg
}
