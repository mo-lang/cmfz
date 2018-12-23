package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/queryAll")
    public List<Album> queryAll() {
        return albumService.queryAll();
    }

    @RequestMapping("/addAlbum")
    public void addAlbum(Album album, HttpServletRequest req, MultipartFile multipartFile) throws IOException {
        String oldName = multipartFile.getOriginalFilename();
        String newName = "/img/album/" + UUID.randomUUID().toString().replace("-", "") + oldName;
        String path = req.getRealPath(newName);
        multipartFile.transferTo(new File(path));
        album.setCoverImg(newName);
        album.setPubDate(new Date());
        albumService.addAlbum(album);
    }

    @RequestMapping("/deleteAlbum")
    public void deleteAlbum(Integer id) {
        albumService.deleteAlbum(id);
    }

}
