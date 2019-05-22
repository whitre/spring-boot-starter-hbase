package com.spring4all.spring.boot.starter.hbase;

import com.spring4all.spring.boot.starter.hbase.boot.HbaseAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author whitr
 * @create 2019-05-22 14:31
 * @since 2019.05.22
 */
@SpringBootApplication
@Import(HbaseAutoConfiguration.class)
public class TestBase {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TestBase.class).web(false).run(args);
    }
}
