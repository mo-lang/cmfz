package com.baizhi.cmfz.mapper;

import com.baizhi.cmfz.entity.Banner;
import tk.mybatis.mapper.common.Mapper;

public interface BannerMapper extends Mapper<Banner> {
    void updateStatus(Banner banner);
}
