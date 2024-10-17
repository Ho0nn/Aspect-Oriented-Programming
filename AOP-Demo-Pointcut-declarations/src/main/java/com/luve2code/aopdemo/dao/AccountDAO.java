package com.luve2code.aopdemo.dao;


import com.luve2code.aopdemo.Account;

public interface AccountDAO {

    void addAccount(Account account,boolean vipFlag);

    boolean doWork();
    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);
}
