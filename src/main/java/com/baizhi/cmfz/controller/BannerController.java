package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dto.BannerDto;
import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/queryAll")
    public BannerDto queryAll(Integer page, Integer rows) {
        return bannerService.queryAll(page, rows);
    }

    @RequestMapping("/updateStatus")
    public void updateStatus(Banner banner) {
        bannerService.updateStatus(banner);
    }

    @RequestMapping("/deleteBanner")
    public void deleteBanner(Banner banner) {
        bannerService.deleteBanner(banner);
    }

    @RequestMapping("/addBanner")
    public void addBanner(HttpServletRequest req, Banner banner, MultipartFile multipartFile) throws IOException {
        String oldName = multipartFile.getOriginalFilename();
        String newName = "/img/banner/" + UUID.randomUUID().toString().replace("-", "") + oldName;
        String path = req.getRealPath(newName);
        multipartFile.transferTo(new File(path));
        banner.setImgPath(newName);
        banner.setPubDate(new Date());
        bannerService.addBanner(banner);
    }
}
