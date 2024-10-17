package com.luve2code.aopdemo;

import com.luve2code.aopdemo.dao.AccountDAO;
import com.luve2code.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration.class})

public class AopDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner   commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO){
        return runner->{
//            demoBeforeAdvice(accountDAO,membershipDAO);
              demoAfterReturningAdvice(accountDAO);
        };
    }

    private void demoAfterReturningAdvice(AccountDAO accountDAO) {

        // call method to find the accounts
        List<Account> accounts=accountDAO.findAccounts();

        // print the accounts
        System.out.println("Main Program: demoAfterReturningAdvice\n-----\n");
        System.out.println(accounts);


    }

    private void demoBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

        Account account=new Account();
        account.setName("Hanin");
        account.setLevel("Gold");

        // call the business method
        accountDAO.addAccount(account,true);
        accountDAO.doWork();

        // call the accountdao getter/setter methods
        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");

       String name= accountDAO.getName();
        String code=accountDAO.getServiceCode();

        // call the membership business method
        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();
    }
}
