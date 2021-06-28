package com.zti.yerbastore.service;

import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.repository.YerbaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

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
    public List<Yerba> findAllByParameters(String name, String originCountry) {
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("originCountry", contains().ignoreCase());

        Yerba example = Yerba.builder()
                .name(name)
                .originCountry(originCountry)
                .build();

        List<Yerba> yerbas = yerbaRepository.findAll(Example.of(example, matcher));
        yerbas.forEach(yerba -> yerba.setPhoto(null));

        return yerbas;
    }

    @Transactional(readOnly = true)
    public Yerba findById(String id) {
        return yerbaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yerba with id '" + id + "' does not exist."));
    }

    public Yerba insert(Yerba yerba) {
        yerba.setViews(0);

        return yerbaRepository.insert(yerba);
    }

    public Yerba update(String id, Yerba yerba) {
        Yerba dbYerba = findById(id);

        yerba.setId(id);
        yerba.setViews(dbYerba.getViews());
        yerba.setPhoto(dbYerba.getPhoto());

        return yerbaRepository.save(yerba);
    }

    public void incrementViews(String id) {
        Yerba yerba = findById(id);

        yerba.setViews(yerba.getViews() + 1);

        yerbaRepository.save(yerba);
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

        if (yerba.getPhoto() != null)
            photoService.deleteById(yerba.getPhoto().getId());
        yerbaRepository.deleteById(yerba.getId());
    }
}
