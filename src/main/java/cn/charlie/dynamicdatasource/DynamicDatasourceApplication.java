package cn.charlie.dynamicdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author charlie
 * @date 4/9/2023 2:25 PM
 **/
@SpringBootApplication
@MapperScan("cn.charlie.dynamicdatasource.mapper")
public class DynamicDatasourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
    }
}
