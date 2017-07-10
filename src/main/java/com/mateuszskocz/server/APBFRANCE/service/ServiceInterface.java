package com.mateuszskocz.server.APBFRANCE.service;

import com.mateuszskocz.server.APBFRANCE.domain.Car;

import java.util.List;

/**
 * Created by Mati on 08.07.2017.
 */
public interface ServiceInterface<T> {

    List<T> getObj();
    T create(T obj);
    T fingById(String id);
    T update(T obj);
}
