package com.lucaswangdev.mybatis.generator;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class GeneratorTest {
    
    @Test
    public void testGeneratorInitialization() {
        // 测试生成器是否可以正确初始化
        Logger logger = new Logger() {
            @Override
            public void error(Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void error(String message) {
                System.err.println("Error: " + message);
            }

            @Override
            public void info(String message) {
                System.out.println("Info: " + message);
            }

            @Override
            public void setProgress(int percent) {
                System.out.println("Progress: " + percent + "%");
            }
        };
        
        Generator generator = new Generator(logger);
        assertTrue("Generator should be created successfully", generator != null);
    }
    
    @Test
    public void testDemoConfigurationExists() {
        // 测试demo配置文件是否存在
        File demoConfig = new File("demo/generator.properties");
        assertTrue("Demo configuration file should exist", demoConfig.exists());
        
        File demoTemplate = new File("demo/vms/tpl1.jm.vm");
        assertTrue("Demo template file should exist", demoTemplate.exists());
    }
}