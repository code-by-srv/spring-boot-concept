package com.example.SpringTransactionManagement.services.impl;

import com.example.SpringTransactionManagement.entities.Employee;
import com.example.SpringTransactionManagement.entities.SalaryAccount;
import com.example.SpringTransactionManagement.repositories.SalaryAccountRepository;
import com.example.SpringTransactionManagement.services.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class SalaryAccountServiceImpl implements SalaryAccountService {

    private final SalaryAccountRepository salaryAccountRepository;

    @Override
    public void createAccount(Employee employee) {

        //if (employee.getName().equals("saurav")) throw new RuntimeException("Saurav is not allowed");

        // after the new employee is created salary account is also created.
        SalaryAccount salaryAccount = SalaryAccount.builder()
                .employee(employee)
                .balance(BigDecimal.ZERO)
                .build();

        salaryAccountRepository.save(salaryAccount);

    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public SalaryAccount incrementBalance(Long accountId) {
        SalaryAccount salaryAccount = salaryAccountRepository.findById(accountId)
                .orElseThrow(()-> new RuntimeException("Account not found with the id: "+accountId));

        BigDecimal prevBalance = salaryAccount.getBalance();
        BigDecimal newBalance = prevBalance.add(BigDecimal.valueOf(1L));

        salaryAccount.setBalance(newBalance);

        return salaryAccountRepository.save(salaryAccount);
    }
}
