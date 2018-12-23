package com.baizhi.cmfz.service.Impl;

import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.mapper.ChapterMapper;
import com.baizhi.cmfz.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    public void addChapter(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString().replace("-", ""));
        chapterMapper.insert(chapter);
    }

    public void deleteChapter(Chapter chapter) {
        chapterMapper.delete(chapter);
    }
}
