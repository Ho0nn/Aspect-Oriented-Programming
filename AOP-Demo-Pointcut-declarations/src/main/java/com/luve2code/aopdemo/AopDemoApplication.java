package com.luve2code.aopdemo;

import com.luve2code.aopdemo.dao.AccountDAO;
import com.luve2code.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration.class})
public class AopDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner   commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO){
        return runner->{
            demoBeforeAdvice(accountDAO,membershipDAO);

        };
    }

    private void demoBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

        Account account=new Account();
        // call the business method
        accountDAO.addAccount(account,true);
        accountDAO.doWork();

        // call the membership business method
        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();
    }
}
