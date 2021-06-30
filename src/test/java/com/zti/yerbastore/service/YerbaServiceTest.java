package com.zti.yerbastore.service;

import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.repository.YerbaRepository;
import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class YerbaServiceTest {

    @Mock
    private YerbaRepository yerbaRepository;

    @Mock
    private PhotoService photoService;

    private YerbaService yerbaService;

    @BeforeEach
    void setUp() {
        yerbaService = new YerbaService(yerbaRepository, photoService);
    }

    @Test
    void testFindAllEmpty() {
        when(yerbaRepository.findAll()).thenReturn(Collections.emptyList());

        List<Yerba> result = yerbaService.findAll();

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testFindAllReturnsNoPhotos() {
        Yerba testYerba = Yerba.builder().id("1").photo(new Photo(new Binary(new byte[]{}))).build();
        when(yerbaRepository.findAll()).thenReturn(Collections.singletonList(testYerba));

        List<Yerba> result = yerbaService.findAll();

        assertEquals(1, result.size());
        assertNull(result.get(0).getPhoto());
    }

    @Test
    void testInsert() {
        Yerba testYerba = Yerba.builder().id("1").build();
        when(yerbaRepository.insert(testYerba)).thenReturn(testYerba);

        Yerba result = yerbaService.insert(testYerba);

        assertEquals(testYerba, result);
        assertEquals(0, result.getViews());
    }

    @Test
    void testUpdate() {
        Yerba yerbaFromDb = Yerba.builder().id("1").views(13).build();
        Yerba testYerba = Yerba.builder().name("TestName").build();
        when(yerbaRepository.findById("1")).thenReturn(Optional.ofNullable(yerbaFromDb));
        when(yerbaRepository.save(testYerba)).thenReturn(testYerba);

        Yerba result = yerbaService.update("1", testYerba);

        assertEquals(testYerba, result);
        assertEquals(13, result.getViews());
        assertEquals("1", result.getId());
        assertNull(result.getPhoto());
    }
}