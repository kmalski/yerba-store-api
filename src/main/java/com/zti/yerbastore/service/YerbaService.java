package com.zti.yerbastore.service;

import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.repository.YerbaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class YerbaService {

    private final YerbaRepository yerbaRepository;
    private final PhotoService photoService;

    @Autowired
    public YerbaService(YerbaRepository yerbaRepository, PhotoService photoService) {
        this.yerbaRepository = yerbaRepository;
        this.photoService = photoService;
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

    public Yerba insert(Yerba yerba) {
        return yerbaRepository.insert(yerba);
    }

    public Yerba update(String id, Yerba yerba) {
        findById(id);
        yerba.setId(id);

        return yerbaRepository.save(yerba);
    }

    public Photo addPhoto(String id, MultipartFile photoFile) {
        Yerba yerba = findById(id);
        Photo photo = photoService.insert(photoFile);

        yerba.setPhoto(photo);
        yerbaRepository.save(yerba);

        return photo;
    }

    public void deleteById(String id) {
        Yerba yerba = findById(id);

        photoService.deleteById(yerba.getPhoto().getId());
        yerbaRepository.deleteById(yerba.getId());
    }
}
