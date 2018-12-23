package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/addChapter")
    public void addChapter(Chapter chapter, HttpServletRequest req, MultipartFile multipartFile) throws IOException, EncoderException {
        String oldName = multipartFile.getOriginalFilename();
        Integer size = (int) multipartFile.getSize() / 1000;
        String newName = "/audio/" + UUID.randomUUID().toString().replace("-", "") + oldName;
        String contentType = multipartFile.getContentType();
        String path = req.getRealPath(newName);
        File file = new File(path);
        multipartFile.transferTo(file);
        Encoder encoder = new Encoder();
        MultimediaInfo multimediaInfo = encoder.getInfo(file);
        Integer duration = (int) multimediaInfo.getDuration() / 1000;
        chapter.setSize(size);
        chapter.setUrl(newName);
        chapter.setType(contentType);
        chapter.setDuration(duration);
        chapter.setUploadDate(new Date());
        chapterService.addChapter(chapter);
    }

    @RequestMapping("/deleteChapter")
    public void deleteChapter(HttpServletRequest req, Chapter chapter) {
        chapterService.deleteChapter(chapter);
        File file = new File(req.getRealPath(chapter.getUrl()));
        file.delete();
    }

    @RequestMapping("/download")
    public void download(HttpServletRequest req, HttpServletResponse res, String url) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File(req.getRealPath(url)));
        res.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("F:" + url, "UTF-8"));
        ServletOutputStream outputStream = res.getOutputStream();
        outputStream.write(bytes);
        if (outputStream != null) outputStream.flush();
        if (outputStream != null) outputStream.close();
    }

}
