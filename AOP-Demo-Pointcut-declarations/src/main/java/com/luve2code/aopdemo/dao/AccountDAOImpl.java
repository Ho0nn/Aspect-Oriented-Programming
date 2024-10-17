package com.luve2code.aopdemo.dao;


import com.luve2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;

    private String serviceCode;

    @Override
    public void addAccount(Account account,boolean vipFlag) {
        System.out.println(getClass()+" : Adding an account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass()+ ": doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass()+":getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass()+":setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass()+":getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass()+":setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        List<Account> accounts=new ArrayList<>();

        // create sample accounts
        Account account1 = new Account("Hanin","Bronze");
        Account account2 = new Account("Ali","Silver");
        Account account3 = new Account("Lama","Gold");

        // add them to the accounts list
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        return accounts;
    }
}
