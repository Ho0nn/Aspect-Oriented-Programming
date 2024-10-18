package com.luve2code.aopdemo;

import com.luve2code.aopdemo.dao.AccountDAO;
import com.luve2code.aopdemo.dao.MembershipDAO;
import com.luve2code.aopdemo.service.TrafficFortuneService;
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
    public CommandLineRunner   commandLineRunner(AccountDAO accountDAO,
                                                 MembershipDAO membershipDAO,
                                                 TrafficFortuneService fortuneService){
        return runner->{
//            demoBeforeAdvice(accountDAO,membershipDAO);
//            demoAfterReturningAdvice(accountDAO);
//            demoAfterThrowingAdvice(accountDAO);
//            demoAfterAdvice(accountDAO);
//            demoAroundAdvice(fortuneService);
            demoAroundAdviceHandleException(fortuneService);
        };
    }

    private void demoAroundAdviceHandleException(TrafficFortuneService fortuneService) {
        System.out.println("\nMain Program: demoAroundAdvice");

        System.out.println("Calling getFortune");
        boolean flag=true;

        String data = fortuneService.getFortune(flag);

        System.out.println("\nMy fortune is: "+data);
        System.out.println("Finished !");
    }

    private void demoAroundAdvice(TrafficFortuneService fortuneService) {

        System.out.println("\nMain Program: demoAroundAdvice");

        System.out.println("Calling getFortune");
        String data = fortuneService.getFortune();

        System.out.println("\nMy fortune is: "+data);
        System.out.println("Finished !");

    }

    private void demoAfterAdvice(AccountDAO accountDAO) {
        // call method to find the accounts

        List<Account> accounts=null;

        try{
            // add boolean flag to simulate exceptions
            boolean flag=false;
            accounts=accountDAO.findAccounts(flag);
        }
        catch (Exception exc){

            // print exception
            System.out.println("\n\nMain Program: caught exception: "+exc);
        }

        // print the accounts
        System.out.println("\n\nMain Program: demoAfterThrowingAdvice\n-----\n");
        System.out.println(accounts+"\n");

    }

    private void demoAfterThrowingAdvice(AccountDAO accountDAO) {
        // call method to find the accounts

        List<Account> accounts=null;

        try{
            // add boolean flag to simulate exceptions
            boolean flag=true;
            accounts=accountDAO.findAccounts(flag);
        }
        catch (Exception exc){

            // print exception
            System.out.println("\n\nMain Program: caught exception: "+exc);
        }

        // print the accounts
        System.out.println("\n\nMain Program: demoAfterThrowingAdvice\n-----\n");
        System.out.println(accounts+"\n");


    }

    private void demoAfterReturningAdvice(AccountDAO accountDAO) {

        // call method to find the accounts
        List<Account> accounts=accountDAO.findAccounts();

        // print the accounts
        System.out.println("\n\nMain Program: demoAfterReturningAdvice\n-----\n");
        System.out.println(accounts+"\n");


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
