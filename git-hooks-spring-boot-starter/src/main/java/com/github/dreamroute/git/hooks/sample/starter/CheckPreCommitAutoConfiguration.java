package com.github.dreamroute.git.hooks.sample.starter;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Slf4j
@Component
public class CheckPreCommitAutoConfiguration implements CommandLineRunner {

    @Resource
    private Environment environment;

    @Override
    public void run(String... args) {
        String[] activeProfiles = environment.getActiveProfiles();
        if (ArrayUtil.isEmpty(activeProfiles)) {
            throw new IllegalArgumentException("请指定启动环境信息active profiles");
        }
        boolean needCheck = Arrays.stream(activeProfiles)
            .anyMatch(e -> e.contains("local") || e.contains("qa") || e.contains("dev"));
        String baseDir = System.getProperty("user.dir");
        baseDir += "/.git/hooks/pre-commit";
        boolean exist = FileUtil.exist(baseDir);
        if (needCheck && !exist) {
            String h = CharSequenceUtil.repeat('-', 50);
            log.error("\r\n" + h + "\r\n请先执行[mvn clean] 或 [mvn install]安装pre-commit\r\n" + h);
            System.exit(0);
        }
    }
}