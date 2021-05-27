package com.zti.yerbastore.service;

import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.repository.YerbaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class YerbaService {

    private final YerbaRepository yerbaRepository;
    private final PhotoService photoService;

    @Transactional(readOnly = true)
    public List<Yerba> findAll() {
        List<Yerba> yerbas = yerbaRepository.findAll();

        yerbas.forEach(yerba -> yerba.setPhoto(null));

        return yerbas;
    }

    @Transactional(readOnly = true)
    public Yerba findById(String id) {
        Yerba yerba = yerbaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yerba with id '" + id + "' does not exist."));

        yerba.getPhoto().setImage(null);

        return yerba;
    }

    public Yerba insert(Yerba yerba) {
        yerba.setViews(0);

        return yerbaRepository.insert(yerba);
    }

    public Yerba update(String id, Yerba yerba) {
        Yerba dbYerba = findById(id);

        yerba.setId(id);
        yerba.setViews(dbYerba.getViews());

        return yerbaRepository.save(yerba);
    }

    public Yerba incrementViews(String id) {
        Yerba yerba = findById(id);

        yerba.setViews(yerba.getViews() + 1);

        return yerbaRepository.save(yerba);
    }

    public Photo setPhoto(String id, MultipartFile photoFile) {
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
