package com.zti.yerbastore.controller.admin;

import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.service.YerbaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/yerba", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminYerbaController {

    private final YerbaService yerbaService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Yerba create(@RequestBody Yerba yerba) {
        return yerbaService.insert(yerba);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        yerbaService.deleteById(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Yerba update(@PathVariable String id, @RequestBody Yerba yerba) {
        return yerbaService.update(id, yerba);
    }

    @PostMapping(path = "/{id}/photo", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] setYerbaPhoto(@PathVariable String id, @RequestParam("photo") MultipartFile photoFile) {
        Photo photo = yerbaService.setPhoto(id, photoFile);

        return photo.getImage().getData();
    }

}
