package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Album;

import java.util.List;

public interface AlbumService {
    List<Album> queryAll();

    void addAlbum(Album album);

    void deleteAlbum(Integer id);
}
