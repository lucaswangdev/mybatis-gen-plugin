package com.lucaswangdev.mybatis.generator;

/**
 * 使用示例 - 演示如何使用MyBatis Generator代码生成器
 * 
 * 这个类展示了如何在Java应用程序中使用MyBatis Generator库来生成代码。
 * 注意：实际运行需要一个有效的数据库连接。
 */
public class UsageExample {
    
    public static void main(String[] args) {
        // 创建日志处理器
        Logger logger = new Logger() {
            @Override
            public void error(Exception e) {
                System.err.println("[ERROR] " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void error(String message) {
                System.err.println("[ERROR] " + message);
            }

            @Override
            public void info(String message) {
                System.out.println("[INFO] " + message);
            }

            @Override
            public void setProgress(int percent) {
                System.out.print("\r[PROGRESS] " + percent + "% completed");
            }
        };
        
        // 创建代码生成器
        Generator generator = new Generator(logger);
        
        // 使用demo配置文件生成代码
        // 注意：实际运行时需要修改配置文件中的数据库连接信息
        String configFilePath = "demo/generator.properties";
        
        System.out.println("Initializing code generation with config: " + configFilePath);
        System.out.println("Make sure to update the database connection details in the config file.");
        System.out.println("Also ensure the database is accessible before running.");
        
        // 为了演示目的，这里不实际调用generate方法，因为需要真实的数据库连接
        // 在实际使用中，可以取消注释下面的行：
        // generator.generate(configFilePath);
        
        System.out.println("Example setup completed successfully!");
    }
}