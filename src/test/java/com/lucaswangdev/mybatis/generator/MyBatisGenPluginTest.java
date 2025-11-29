package com.lucaswangdev.mybatis.generator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MyBatisGenPluginTest {
    
    @Test
    public void testMyBatisGenPluginInitialization() {
        // 简单测试确保类可以正确加载
        Logger logger = new Logger() {
            @Override
            public void error(Exception e) {
                // Empty implementation for test
            }

            @Override
            public void error(String message) {
                // Empty implementation for test
            }

            @Override
            public void info(String message) {
                // Empty implementation for test
            }

            @Override
            public void setProgress(int percent) {
                // Empty implementation for test
            }
        };
        
        Generator generator = new Generator(logger);
        assertTrue("Generator should be created successfully", generator != null);
    }
}