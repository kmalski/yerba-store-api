package com.zti.yerbastore.controller.admin;

import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.service.YerbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/admin/yerba", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminYerbaController {

    private final YerbaService yerbaService;

    @Autowired
    public AdminYerbaController(YerbaService yerbaService) {
        this.yerbaService = yerbaService;
    }

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
    public byte[] addYerbaPhoto(@PathVariable String id, @RequestParam("photo") MultipartFile photoFile) {
        Photo photo = yerbaService.addPhoto(id, photoFile);

        return photo.getImage().getData();
    }


}
