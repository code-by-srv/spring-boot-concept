package com.example.SpringTransactionManagement.services;

import com.example.SpringTransactionManagement.entities.Employee;
import com.example.SpringTransactionManagement.entities.SalaryAccount;

public interface SalaryAccountService {

    void createAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}
