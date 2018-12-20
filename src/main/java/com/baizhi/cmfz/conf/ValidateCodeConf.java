package com.baizhi.cmfz.conf;

import com.baizhi.cmfz.util.CreateValidateCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeConf {

    @Bean
    public CreateValidateCode getCreateValidateCode() {
        return new CreateValidateCode();
    }
}
