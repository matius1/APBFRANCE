package com.mateuszskocz.server.APBFRANCE.service;

import com.mateuszskocz.server.APBFRANCE.domain.Recipient;
import com.mateuszskocz.server.APBFRANCE.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mati on 26.07.2017.
 */
@Service
public class RecipientService implements ServiceInterface<Recipient> {

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    public RecipientService(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }


    @Override
    public List getObj() {
        List<Recipient> recipientList = recipientRepository.findAll();
        return recipientList;
    }

    @Override
    public Recipient create(Recipient obj) {
        return recipientRepository.save(obj);
    }

    @Override
    public Recipient fingById(String id) {
        if (!recipientRepository.findById(id).isEmpty())
            return recipientRepository.findById(id).get(0);
        else
            return null;
    }


    @Override
    public Recipient update(Recipient obj) {
        return null;
    }


}
