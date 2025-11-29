package com.lucaswangdev.mybatis.generator;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class IntegrationTest {
    
    @Test
    public void testDemoFilesExist() {
        // 验证demo目录中的文件结构是否完整
        File demoDir = new File("demo");
        assertTrue("Demo directory should exist", demoDir.exists());
        assertTrue("Demo directory should be a directory", demoDir.isDirectory());
        
        // 检查配置文件
        File configFile = new File("demo/generator.properties");
        assertTrue("Config file should exist", configFile.exists());
        
        // 检查模板入口文件
        File templateEntry = new File("demo/vms/tpl1.jm.vm");
        assertTrue("Template entry file should exist", templateEntry.exists());
        
        // 检查模板目录
        File tplDir = new File("demo/vms/tpl");
        assertTrue("Template directory should exist", tplDir.exists());
        assertTrue("Template directory should be a directory", tplDir.isDirectory());
        
        // 检查一些关键模板文件
        File entityTpl = new File("demo/vms/tpl/Entity.tpl.vm");
        assertTrue("Entity template should exist", entityTpl.exists());
        
        File entityBaseTpl = new File("demo/vms/tpl/EntityBase.vm");
        assertTrue("EntityBase template should exist", entityBaseTpl.exists());
        
        File repositoryTpl = new File("demo/vms/tpl/Repository.tpl.vm");
        assertTrue("Repository template should exist", repositoryTpl.exists());
    }
    
    @Test
    public void testCoreClassesExist() {
        // 验证核心类是否存在且可以加载
        try {
            Class.forName("com.lucaswangdev.mybatis.generator.Generator");
            Class.forName("com.lucaswangdev.mybatis.generator.TemplateProcessor");
            Class.forName("com.lucaswangdev.mybatis.generator.Logger");
            Class.forName("com.lucaswangdev.mybatis.generator.meta.Database");
            Class.forName("com.lucaswangdev.mybatis.generator.meta.Table");
            Class.forName("com.lucaswangdev.mybatis.generator.meta.Column");
        } catch (ClassNotFoundException e) {
            assertTrue("All core classes should be loadable", false);
        }
    }
}