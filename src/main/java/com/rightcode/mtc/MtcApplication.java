package com.rightcode.mtc;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class MtcApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MtcApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
