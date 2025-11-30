# MyBatis-Gen-Plugin

MyBatis-Gen-Plugin 是一个基于数据库模型和Velocity模板的代码生成工具。本项目已从Gradle项目重构为Maven插件，可以通过Maven命令直接使用。

> **致谢声明**：本项目代码受到 [jasmine](https://github.com/kongxiangxin/jasmine) 项目的启发，并在其基础上进行修改和二次开发。感谢源作者的贡献！

## 项目结构

```
mybatis-gen-plugin/
├── pom.xml              # Maven项目配置文件
├── src/                 # 源代码目录
│   ├── main/            # 主代码
│   │   └── java/        # Java源代码
│   └── test/            # 测试代码
│       └── java/        # Java测试代码
├── target/              # Maven构建输出目录
├── demo/                # 使用示例
│   ├── generator.properties
│   ├── vms/
│   └── test-project/    # 测试Maven项目
└── example-project/     # 在其他项目中使用插件的示例
```

## 重构内容

1. **项目构建系统**：从Gradle重构为Maven插件
2. **依赖管理**：使用Maven管理所有依赖项
3. **代码组织**：重新组织源代码结构，符合Maven标准目录结构
4. **Maven插件功能**：实现了Maven插件，可通过`mvn mybatis-gen:generate`命令使用
5. **保留核心功能**：保留了所有核心代码生成功能

## Maven插件使用

### 方式一：直接使用（推荐）

插件已发布到Maven中央仓库，可以直接在项目中使用，无需手动安装：

在您的项目`pom.xml`中添加插件配置：

> **注意**：请将 `<version>` 替换为[最新版本号](https://github.com/lucaswangdev/mybatis-gen-plugin/releases)。您可以在项目的 [Releases](https://github.com/lucaswangdev/mybatis-gen-plugin/releases) 页面查看最新版本。

```xml
<build>
  <plugins>
    <plugin>
      <groupId>com.lucaswangdev</groupId>
      <artifactId>mybatis-gen-plugin</artifactId>
      <version>最新版本号</version> <!-- 请查看 https://github.com/lucaswangdev/mybatis-gen-plugin/releases -->
      <configuration>
        <configFile>generator.properties</configFile>
      </configuration>
    </plugin>
  </plugins>
</build>
```

### 3. 运行代码生成

```bash
mvn mybatis-gen:generate
```

### 方式二：本地调试开发

如果您需要修改插件源码或进行本地调试，才需要执行以下步骤：

1. **克隆并安装到本地仓库**

```bash
git clone https://github.com/lucaswangdev/mybatis-gen-plugin.git
cd mybatis-gen-plugin
mvn clean install
```

2. **在项目中使用本地版本**

在您的项目pom.xml中引用本地安装的版本（参考上面的配置）

## 在其他项目中使用

### 1. 在您的项目中添加插件配置

在您的项目`pom.xml`中添加以下配置：

> **提示**：请访问 [GitHub Releases](https://github.com/lucaswangdev/mybatis-gen-plugin/releases) 查看最新版本号

```xml
<build>
  <plugins>
    <plugin>
      <groupId>com.lucaswangdev</groupId>
      <artifactId>mybatis-gen-plugin</artifactId>
      <version>最新版本号</version> <!-- 请替换为实际的最新版本，如 1.0.1 -->
      <configuration>
        <configFile>generator.properties</configFile>
      </configuration>
    </plugin>
  </plugins>
</build>
```

### 2. 创建配置文件

在您的项目根目录创建`generator.properties`文件：
```properties
# 数据库配置
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/your_database
jdbc.username=your_username
jdbc.password=your_password
```

### 3. 创建模板文件

复制模板文件到您的项目中：
- 创建`vms/tpl1.jm.vm`作为入口模板
- 创建`vms/tpl/`目录并添加所有模板文件

### 4. 运行代码生成

在您的项目目录中运行：
```bash
mvn mybatis-gen:generate
```

## 插件状态

✅ **已发布到Maven中央仓库**

本插件已发布到Maven中央仓库，可以直接在项目中使用。查看最新版本：
- [GitHub Releases](https://github.com/lucaswangdev/mybatis-gen-plugin/releases)
- [Maven Central](https://central.sonatype.com/artifact/com.lucaswangdev/mybatis-gen-plugin)

**仅在以下情况需要本地安装**：
- 您需要修改插件源码
- 您需要调试插件功能
- 您想使用未发布的开发版本

## 支持的数据库

- MySQL
- PostgreSQL
- SQL Server
- Oracle（需要手动安装ojdbc驱动）

## 核心类

- `Generator`：代码生成器主类
- `TemplateProcessor`：模板处理器
- `Logger`：日志接口
- 数据库元数据模型类：`Database`、`Table`、`Column`
- 配置类：`GenerateSetting`、`JdbcProperty`

## 使用方法

### 1. 创建配置文件

创建一个`generator.properties`文件，配置数据库连接信息：

```properties
# 数据库配置
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/your_database
jdbc.username=your_username
jdbc.password=your_password

# 表名前缀过滤
tablePrefix=tbl_
truncatePrefix=true

# 排除特定表
excludeTables=tbl_log,tbl_temp
```

### 2. 创建模板文件

创建以`.jm.vm`结尾的模板文件，例如`entity.jm.vm`：

```velocity
## entity.jm.vm
#foreach($table in $model.tables)
    ## 处理每个表
#end
```

### 3. 运行Maven插件

```bash
mvn mybatis-gen:generate
```

## Oracle数据库支持

如果需要使用Oracle数据库，需要手动下载ojdbc14.jar文件，并通过以下命令安装到本地Maven仓库：

```bash
mvn install:install-file -Dfile=ojdbc14.jar -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar
```

然后在您的`pom.xml`中添加Oracle JDBC依赖：

```xml
<dependency>
    <groupId>com.oracle</groupId>
    <artifactId>ojdbc14</artifactId>
    <version>10.2.0.4.0</version>
</dependency>
```

## 构建项目

```bash
# 编译项目
mvn compile

# 运行测试
mvn test

# 打包项目
mvn package

# 安装到本地仓库
mvn install
```

## 示例项目

在`demo/`目录中有一个完整的使用示例，展示了如何在其他Maven项目中使用MyBatis Generator插件。

## 许可证

MIT License