package com.cg.service.withdraw;

import com.cg.model.Withdraw;
import com.cg.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class WithdrawServiceImpl implements IWithdrawService {

    @Autowired
    private WithdrawRepository withdrawRepository;


    @Override
    public List<Withdraw> findALl() {
        return null;
    }

    @Override
    public Optional<Withdraw> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Withdraw save(Withdraw withdraw) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Withdraw withdraw) {

    }
}
