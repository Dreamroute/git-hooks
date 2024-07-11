package com.github.dreamroute.git.hooks.sample.starter;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("local")
public class CheckPreCommitAutoConfiguration implements CommandLineRunner {
    @Override
    public void run(String... args) {
        String baseDir = System.getProperty("user.dir");
        baseDir += "/.git/hooks/pre-commit";
        boolean exist = FileUtil.exist(baseDir);
        if (!exist) {
            String h = StrUtil.repeat('-', 50);
            log.error("\r\n" + h + "\r\n请先执行[mvn clean] 或 [mvn install]安装pre-commit\r\n" + h);
            System.exit(0);
        }
    }
}