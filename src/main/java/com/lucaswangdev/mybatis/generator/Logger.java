package com.lucaswangdev.mybatis.generator;


public interface Logger {

    void error(String message);

    void error(Exception e);

    void info(String message);

    void setProgress(int percent);

}
