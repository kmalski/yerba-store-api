package com.zti.yerbastore.service;

import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.repository.PhotoRepository;
import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PhotoServiceTest {

    @Mock
    private PhotoRepository photoRepository;

    private PhotoService photoService;

    @BeforeEach
    void setUp() {
        photoService = new PhotoService(photoRepository);
    }

    @Test
    void testFindByYerbaThrowsWhenNoPhotoFound() {
        Photo testPhoto = new Photo(new Binary(new byte[]{}));
        testPhoto.setId("testPhoto");
        Yerba testYerba = Yerba.builder().id("testYerba").photo(testPhoto).build();

        assertThrows(NotFoundException.class, () -> photoService.findByYerba(testYerba), "Photo with id 'testPhoto' does not exist.");
    }
}