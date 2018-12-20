package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu")
public class Menu implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private String iconcls;
    private String url;
    private List<Menu> menu;
}
