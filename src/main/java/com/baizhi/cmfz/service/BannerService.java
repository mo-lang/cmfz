package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dto.BannerDto;
import com.baizhi.cmfz.entity.Banner;

public interface BannerService {

    BannerDto queryAll(Integer page, Integer rows);

    void updateStatus(Banner banner);

    void deleteBanner(Banner banner);

    void addBanner(Banner banner);
}
