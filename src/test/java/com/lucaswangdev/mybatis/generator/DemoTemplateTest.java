package com.lucaswangdev.mybatis.generator;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class DemoTemplateTest {
    
    @Test
    public void testTemplateFilesExist() {
        // 测试demo目录中的模板文件是否存在且符合命名规范
        File demoDir = new File("demo/vms");
        assertTrue("Demo mybatis-gen directory should exist", demoDir.exists());
        
        // 查找所有.jm.vm文件
        Collection<File> jmVmFiles = FileUtils.listFiles(demoDir, new String[]{"jm.vm"}, true);
        assertTrue("Should find at least one .jm.vm template file", jmVmFiles.size() > 0);
        
        // 验证入口模板文件存在
        boolean hasEntryTemplate = false;
        for (File file : jmVmFiles) {
            if (file.getName().equals("tpl1.jm.vm")) {
                hasEntryTemplate = true;
                break;
            }
        }
        assertTrue("Should have tpl1.jm.vm as entry template", hasEntryTemplate);
    }
    
    @Test
    public void testTemplateStructure() {
        // 验证模板目录结构
        File tplDir = new File("demo/vms/tpl");
        assertTrue("Template directory should exist", tplDir.exists());
        assertTrue("Template directory should be a directory", tplDir.isDirectory());
        
        // 检查关键模板文件是否存在
        String[] expectedTemplates = {
            "Entity.tpl.vm", "Entity.vm", "EntityBase.vm",
            "Repository.tpl.vm", "Repository.vm", "RepositoryBase.vm",
            "Mapper.tpl.vm", "Mapper.vm"
        };
        
        for (String templateName : expectedTemplates) {
            File templateFile = new File(tplDir, templateName);
            assertTrue("Template file " + templateName + " should exist", templateFile.exists());
        }
    }
}