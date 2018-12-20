package com.baizhi.cmfz.service.Impl;

import com.baizhi.cmfz.dto.BannerDto;
import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.mapper.BannerMapper;
import com.baizhi.cmfz.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    public BannerDto queryAll(Integer page, Integer rows) {
        int count = bannerMapper.selectCount(new Banner());
        List<Banner> banners = bannerMapper.queryAll(page, rows);
        return new BannerDto(count, banners);
    }

    public void updateStatus(Banner banner) {
        bannerMapper.updateStatus(banner);
    }

    public void deleteBanner(Banner banner) {
        bannerMapper.delete(banner);
    }

    public void addBanner(Banner banner) {
        bannerMapper.insert(banner);
    }
}
