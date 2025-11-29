package com.lucaswangdev.mybatis.generator.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import com.lucaswangdev.mybatis.generator.Generator;
import com.lucaswangdev.mybatis.generator.Logger;

import java.io.File;

/**
 * Maven Plugin Mojo for generating code from database schema
 */
@Mojo(name = "generate")
public class GenerateMojo extends AbstractMojo implements Logger {
    
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;
    
    /**
     * The path to the properties file
     */
    @Parameter(property = "mybatis-gen.configFile", defaultValue = "generator.properties")
    private String configFile;
    
    /**
     * The base directory for template files
     */
    @Parameter(property = "mybatis-gen.templateDir", defaultValue = ".")
    private String templateDir;
    
    private boolean generating = false;
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (generating) {
            getLog().info("Another generation task is already running");
            return;
        }
        
        generating = true;
        
        try {
            getLog().info("Starting code generation");
            getLog().info("Using config file: " + configFile);
            
            // Resolve the config file path
            File baseDir = project.getBasedir();
            File configFilePath = new File(baseDir, configFile);
            
            if (!configFilePath.exists()) {
                // Try to find in template directory
                configFilePath = new File(baseDir, templateDir + File.separator + configFile);
            }
            
            if (!configFilePath.exists()) {
                throw new MojoExecutionException("Configuration file not found: " + configFilePath.getAbsolutePath());
            }
            
            getLog().info("Configuration file found at: " + configFilePath.getAbsolutePath());
            
            // Create generator and run
            Generator generator = new Generator(this);
            generator.generate(configFilePath.getAbsolutePath());
            
            getLog().info("Code generation completed successfully");
        } catch (Exception e) {
            throw new MojoExecutionException("Error during code generation", e);
        } finally {
            generating = false;
        }
    }
    
    @Override
    public void error(Exception e) {
        getLog().error("Error occurred during generation", e);
    }
    
    @Override
    public void error(String message) {
        getLog().error(message);
    }
    
    @Override
    public void info(String message) {
        getLog().info(message);
    }
    
    @Override
    public void setProgress(int percent) {
        getLog().info("Progress: " + percent + "%");
    }
    
    // Getters and setters
    public MavenProject getProject() {
        return project;
    }
    
    public void setProject(MavenProject project) {
        this.project = project;
    }
    
    public String getConfigFile() {
        return configFile;
    }
    
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
    
    public String getTemplateDir() {
        return templateDir;
    }
    
    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }
}