package com.zti.yerbastore.service;

import com.zti.yerbastore.exception.InternalServerErrorException;
import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    @Transactional(readOnly = true)
    public Photo findById(String id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Photo with id '" + id + "' does not exist."));
    }

    public Photo insert(MultipartFile file) {
        try {
            Photo photo = new Photo(new Binary(BsonBinarySubType.BINARY, file.getBytes()));

            return photoRepository.insert(photo);
        } catch (IOException ex) {
            throw new InternalServerErrorException("Could not upload file.");
        }
    }

    public void deleteById(String id) {
        photoRepository
                .findById(id)
                .ifPresent(value -> photoRepository.deleteById(value.getId()));
    }
}
