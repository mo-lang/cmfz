package com.baizhi.cmfz.service.Impl;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.mapper.AlbumMapper;
import com.baizhi.cmfz.mapper.ChapterMapper;
import com.baizhi.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private HttpServletRequest req;

    public List<Album> queryAll() {
        return albumMapper.queryAll();
    }

    public void addAlbum(Album album) {
        albumMapper.insert(album);
    }

    public void deleteAlbum(Integer id) {
        Chapter chapter = new Chapter();
        chapter.setAlbId(id);
        List<Chapter> chapters = chapterMapper.select(chapter);
        for (Chapter c : chapters) {
            chapterMapper.delete(c);
            File file = new File(req.getRealPath(c.getUrl()));
            file.delete();
        }
        Album album = albumMapper.selectByPrimaryKey(id);
        File file = new File(req.getRealPath(album.getCoverImg()));
        file.delete();
        albumMapper.deleteByPrimaryKey(album);
    }
}
