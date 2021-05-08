package com.zti.yerbastore.service;

import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.repository.PhotoRepository;
import com.zti.yerbastore.repository.YerbaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class YerbaService {

    private final YerbaRepository yerbaRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public YerbaService(YerbaRepository yerbaRepository, PhotoRepository photoRepository) {
        this.yerbaRepository = yerbaRepository;
        this.photoRepository = photoRepository;
    }

    @Transactional(readOnly = true)
    public List<Yerba> findAll() {
        return yerbaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Yerba findById(String id) {
        return yerbaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yerba with id '" + id + "' does not exist."));
    }

    public Yerba save(Yerba yerba) {
        return yerbaRepository.save(yerba);
    }

    public void deleteById(String id) {
        Yerba yerba = findById(id);

        photoRepository
                .findById(yerba.getPhoto().getId())
                .ifPresent(value -> photoRepository.deleteById(value.getId()));

        yerbaRepository.deleteById(yerba.getId());
    }
}
