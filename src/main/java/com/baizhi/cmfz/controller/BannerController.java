package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dto.BannerDto;
import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public BannerDto queryAll(Integer page, Integer rows) {
        return bannerService.queryAll(page, rows);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public void updateStatus(Banner banner) {
        bannerService.updateStatus(banner);
    }

    @RequestMapping("/deleteBanner")
    @ResponseBody
    public void deleteBanner(Banner banner) {
        bannerService.deleteBanner(banner);
    }

    @RequestMapping("/addBanner")
    @ResponseBody
    public void addBanner(HttpServletRequest req, Banner banner, MultipartFile multipartFile) throws IOException {
        String oldName = multipartFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName;
        String path = req.getRealPath("/img") + "/" + newName;
        multipartFile.transferTo(new File(path));
        banner.setImgPath("/img/" + newName);
        banner.setPubDate(new Date());
        bannerService.addBanner(banner);
    }
}
