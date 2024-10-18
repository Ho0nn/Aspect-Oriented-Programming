

package com.luve2code.aopdemo.aspect;

import com.luve2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class DemoLoggingAspect {



    @Around("execution(* com.luve2code.aopdemo.service.*.getFortune(..))")
    public Object  aroundGetFortune(
            ProceedingJoinPoint joinPoint) throws Throwable{
        // print out which method we are advising on
        String  method=joinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @Around on method()"+method);

        //  get begin timestamp
        long begin=System.currentTimeMillis();

        // get end timestamp
        Object result=null;
        try{
        result=joinPoint.proceed();
        }
        catch (Exception exc){
            // log the exception
            System.out.println(exc.getMessage());

            //rethrow exception
            throw exc;
        }

        // get end timestamp
        long end=System.currentTimeMillis();

        // compute duration and display
        long duration=end-begin;

        System.out.println("\n======>>> Duration: "+(duration/1000.0)+" seconds");

        return result;
    }





    @After("execution(* com.luve2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint joinPoint){
        // print out which method we are advising on
        String  method=joinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @After (finally) on findAccounts()"+method);

    }


    @AfterThrowing(
            pointcut = "execution(* com.luve2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc")
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint,Throwable exc){

        // print out which method we are advising on
        String  method=joinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @AfterThrowing on findAccounts()"+method);

        // log the exception
        System.out.println("\n======>>> The Exception  is: "+exc);

    }


    // add a new advice for @AfterReturning on the findAccounts method

    @AfterReturning(
            pointcut = "execution(* com.luve2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result" )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account>result){

        // print out which method we are advising on
       String  method=joinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @AfterReturning on findAccounts()"+method);

        // print out results of the method call
        System.out.println("\n======>>> result is: "+result);

        // post-process data ...

        // convert account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n======>>> result is: "+result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through accounts
        for (Account account:result){
            String  uppCase= account.getName().toUpperCase();

            // update the name
            account.setName(uppCase);
        }

        // get uppercase version of name

        // update name in the account



    }


    @Before("com.luve2code.aopdemo.aspect.AOPExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n======>>> Executing @Before advice on addAccount()");


        // display the  method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = joinPoint.getArgs();

        // loop over them
        for (Object arg: args) {
            System.out.println("method args: " + arg);
            if (arg instanceof Account) {

                // downcast and print Account specific stuff
                Account account = (Account) arg;
                System.out.println("account name: " + account.getName());
                System.out.println("account level: " + account.getLevel());
            }
        }
    }




}

