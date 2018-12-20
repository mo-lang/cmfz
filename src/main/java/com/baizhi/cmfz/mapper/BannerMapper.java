package com.baizhi.cmfz.mapper;

import com.baizhi.cmfz.entity.Banner;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerMapper extends Mapper<Banner> {
    List<Banner> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);

    void updateStatus(Banner banner);
}
