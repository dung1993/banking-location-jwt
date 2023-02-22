package com.cg.service.transfer;

import com.cg.model.Transfer;
import com.cg.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TransferServiceImpl implements ITransferService {

    @Autowired
    private TransferRepository transferRepository;


    @Override
    public List<Transfer> findALl() {
        return null;
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Transfer save(Transfer transfer) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Transfer transfer) {

    }
}
