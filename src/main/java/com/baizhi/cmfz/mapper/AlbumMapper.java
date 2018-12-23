package com.baizhi.cmfz.mapper;

import com.baizhi.cmfz.entity.Album;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumMapper extends Mapper<Album> {
    List<Album> queryAll();
}
