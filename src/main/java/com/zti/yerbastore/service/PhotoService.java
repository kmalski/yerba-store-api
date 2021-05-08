package com.zti.yerbastore.service;

import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.repository.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Transactional(readOnly = true)
    public Photo findById(String id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Photo with id '" + id + "' does not exist."));
    }

    public Photo save(String title, MultipartFile file) throws IOException {
        Photo photo = Photo.builder()
                .title(title)
                .image(new Binary(BsonBinarySubType.BINARY, file.getBytes()))
                .build();

        return photoRepository.save(photo);
    }

}
