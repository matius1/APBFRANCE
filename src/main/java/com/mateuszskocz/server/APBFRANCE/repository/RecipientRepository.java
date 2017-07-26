package com.mateuszskocz.server.APBFRANCE.repository;

import com.mateuszskocz.server.APBFRANCE.domain.Recipient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Mati on 26.07.2017.
 */
public interface RecipientRepository extends MongoRepository<Recipient, String>{

    List<Recipient> findById(String id);

}
