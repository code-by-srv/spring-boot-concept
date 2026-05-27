package com.example.SpringTransactionManagement.repositories;

import com.example.SpringTransactionManagement.entities.SalaryAccount;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SalaryAccountRepository extends CrudRepository<SalaryAccount, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)  // pessimistic lock
    Optional<SalaryAccount> findById(Long id);
}
