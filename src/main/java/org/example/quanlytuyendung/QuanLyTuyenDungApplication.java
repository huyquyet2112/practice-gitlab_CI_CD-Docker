package org.example.quanlytuyendung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "org.example.quanlytuyendung")
public class QuanLyTuyenDungApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuanLyTuyenDungApplication.class, args);
    }

}
