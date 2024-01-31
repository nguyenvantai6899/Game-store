package com.gamesstorebe.service.impl;

import com.gamesstorebe.entity.Developers;
import com.gamesstorebe.repository.DevelopersRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevelopersService {
    private final DevelopersRepository developersRepository;

    public DevelopersService(DevelopersRepository developersRepository) {
        this.developersRepository = developersRepository;
    }

    public List<Developers> developers (){
        return developersRepository.findAll();
    }

    public Developers getDeveloperById(int id) {
        return developersRepository.findById(id)
                .orElseThrow();
    }
}
