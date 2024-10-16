package com.luve2code.aopdemo;

import com.luve2code.aopdemo.dao.AccountDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner   commandLineRunner(AccountDAO accountDAO){
        return runner->{
            demoBeforeAdvice(accountDAO);

        };
    }

    private void demoBeforeAdvice(AccountDAO accountDAO) {
        // call the business method
        accountDAO.addAccount();

        System.out.println("Again !");
        accountDAO.addAccount();
    }

}
