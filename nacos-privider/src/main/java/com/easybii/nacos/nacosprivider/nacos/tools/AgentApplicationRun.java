package com.easybii.nacos.nacosprivider.nacos.tools;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2020,
 * Author: linyu902
 * Date: 2020/8/3 17:49
 * FileName: AgentApplicationRun
 */
@Component
public class AgentApplicationRun implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("被执行了----------------------------->");
    }
}
