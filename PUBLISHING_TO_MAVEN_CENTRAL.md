# 发布插件到Maven仓库指南

本文档说明如何将mybatis-gen-plugin发布到Maven中央仓库或GitHub Packages。

## 重要说明

### Maven中央仓库 vs GitHub Packages

**Maven中央仓库（Maven Central）**
- 全球开发者都可以使用
- 需要Sonatype账号和GPG签名
- 发布命令：`mvn clean deploy -P release`
- 访问地址：https://central.sonatype.com/

**GitHub Packages**
- 托管在GitHub上的私有仓库
- 需要GitHub Personal Access Token
- 需要在~/.m2/settings.xml中配置GitHub Token
- 使用者需要配置GitHub认证才能下载
- 发布命令：`mvn deploy`（需要配置distributionManagement）

**当前配置状态**：
- ✅ 已配置Maven中央仓库发布（使用release profile）
- ❌ 未配置GitHub Packages发布（如需要请参考下方配置）

---

# 发布到Maven中央仓库

## 前提条件

### 1. 注册Sonatype账号
1. 访问 https://central.sonatype.com/
2. 注册账号并验证邮箱
3. 创建namespace（使用 `com.lucaswangdev`）
4. 验证GitHub仓库所有权

### 2. 配置GPG密钥

#### 生成GPG密钥
```bash
# 生成新密钥
gpg --gen-key

# 列出密钥
gpg --list-keys

# 导出公钥到密钥服务器
gpg --keyserver keyserver.ubuntu.com --send-keys <YOUR_KEY_ID>
```

#### 配置Maven settings.xml
在 `~/.m2/settings.xml` 中添加：

```xml
<settings>
  <servers>
    <server>
      <id>central</id>
      <username>你的Sonatype用户名</username>
      <password>你的Sonatype密码或token</password>
    </server>
  </servers>

  <profiles>
    <profile>
      <id>ossrh</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <properties>
        <gpg.executable>gpg</gpg.executable>
        <gpg.passphrase>你的GPG密钥密码</gpg.passphrase>
      </properties>
    </profile>
  </profiles>
</settings>
```

## 发布流程

### 1. 更新版本号
在 `pom.xml` 中更新版本号：
```xml
<version>1.0.0</version>
```

### 2. 执行发布命令
```bash
# 清理并打包
mvn clean package

# 发布到Maven中央仓库（使用release profile）
mvn clean deploy -P release
```

**重要提示**：
- 普通开发使用 `mvn clean install`（不会触发GPG签名和发布）
- 发布到中央仓库使用 `mvn clean deploy -P release`（会执行GPG签名和发布）
- 直接运行 `mvn deploy` 会跳过部署（因为配置了skip=true）

### 3. 验证发布
1. 访问 https://central.sonatype.com/
2. 登录并检查发布状态
3. 如果配置了 `autoPublish=true`，插件会自动发布
4. 否则需要手动点击"Publish"按钮

## pom.xml配置说明

### 必需的元数据
```xml
<name>mybatis-gen-plugin</name>
<description>Maven plugin for code generation based on database model and velocity template</description>
<url>https://github.com/lucaswangdev/mybatis-gen-plugin</url>
```

### 开发者信息
```xml
<developers>
  <developer>
    <name>Lucas Wang</name>
    <email>lucaswang.dev@gmail.com</email>
    <organization>lucaswangdev</organization>
    <organizationUrl>http://www.lucaswangdev.com</organizationUrl>
  </developer>
</developers>
```

### 许可证信息
```xml
<licenses>
  <license>
    <name>Apache License, Version 2.0</name>
    <url>https://www.apache.org/licenses/LICENSE-2.0</url>
    <distribution>repo</distribution>
  </license>
</licenses>
```

### SCM信息
```xml
<scm>
  <connection>scm:git:git://github.com/lucaswangdev/mybatis-gen-plugin.git</connection>
  <developerConnection>scm:git:ssh://github.com/lucaswangdev/mybatis-gen-plugin.git</developerConnection>
  <url>https://github.com/lucaswangdev/mybatis-gen-plugin</url>
</scm>
```

### 发布插件配置

#### Central Publishing Maven Plugin
```xml
<plugin>
  <groupId>org.sonatype.central</groupId>
  <artifactId>central-publishing-maven-plugin</artifactId>
  <version>0.5.0</version>
  <extensions>true</extensions>
  <configuration>
    <publishingServerId>central</publishingServerId>
    <autoPublish>true</autoPublish>
    <waitUntil>published</waitUntil>
  </configuration>
</plugin>
```

#### Source Plugin（必需）
```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-source-plugin</artifactId>
  <version>3.2.1</version>
  <executions>
    <execution>
      <id>attach-sources</id>
      <goals>
        <goal>jar-no-fork</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

#### Javadoc Plugin（必需）
```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-javadoc-plugin</artifactId>
  <version>3.2.0</version>
  <executions>
    <execution>
      <id>attach-javadocs</id>
      <goals>
        <goal>jar</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

#### GPG Plugin（必需）
```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-gpg-plugin</artifactId>
  <version>1.5</version>
  <executions>
    <execution>
      <id>sign-artifacts</id>
      <phase>verify</phase>
      <goals>
        <goal>sign</goal>
      </goals>
    </execution>
  </executions>
  <configuration>
    <gpgArguments>
      <arg>--pinentry-mode</arg>
      <arg>loopback</arg>
    </gpgArguments>
  </configuration>
</plugin>
```

## 常见问题

### 1. GPG签名失败

#### 问题：缺少私钥
```bash
# 检查GPG密钥
gpg --list-keys
gpg --list-secret-keys

# 如果没有密钥，生成新密钥
gpg --gen-key
```

#### 问题：GPG配置文件无效选项
检查 `~/.gnupg/gpg.conf` 文件，删除或注释掉无效的配置行。

#### 问题：没有默认密钥
```bash
# 列出所有密钥
gpg --list-secret-keys

# 在 ~/.m2/settings.xml 中指定密钥ID
<profiles>
  <profile>
    <id>ossrh</id>
    <properties>
      <gpg.executable>gpg</gpg.executable>
      <gpg.keyname>你的密钥ID</gpg.keyname>
      <gpg.passphrase>你的GPG密钥密码</gpg.passphrase>
    </properties>
  </profile>
</profiles>
```

#### 问题：密钥过期
```bash
# 延长密钥有效期
gpg --edit-key <KEY_ID>
> expire
> save
```

#### 临时跳过GPG签名（仅用于测试）
```bash
# 使用 -Dgpg.skip=true 跳过签名
mvn clean deploy -P release -Dgpg.skip=true
```

**注意**：发布到Maven中央仓库必须有GPG签名，跳过签名只能用于本地测试。

### 2. Javadoc生成失败
如果遇到Javadoc错误，可以临时添加：
```xml
<configuration>
  <doclint>none</doclint>
</configuration>
```

### 3. 发布失败
- 检查Maven settings.xml配置
- 确认Sonatype账号和密码正确
- 验证GPG密钥已上传到密钥服务器
- 查看Sonatype控制台的错误信息

## 发布后

### 1. 验证发布
发布成功后，可以在以下位置找到：
- Maven中央仓库：https://central.sonatype.com/artifact/com.lucaswangdev/mybatis-gen-plugin
- Maven搜索：https://search.maven.org/artifact/com.lucaswangdev/mybatis-gen-plugin

### 2. 使用发布的插件
其他用户可以在pom.xml中添加：
```xml
<build>
  <plugins>
    <plugin>
      <groupId>com.lucaswangdev</groupId>
      <artifactId>mybatis-gen-plugin</artifactId>
      <version>1.0.0</version>
      <configuration>
        <configFile>generator.properties</configFile>
      </configuration>
    </plugin>
  </plugins>
</build>
```

### 3. 更新README.md
发布后更新README.md，添加Maven依赖说明。

## 参考资料
- [Maven中央仓库发布指南](https://central.sonatype.org/publish/publish-guide/)
- [GPG签名指南](https://central.sonatype.org/publish/requirements/gpg/)
- [Central Publishing Maven Plugin文档](https://central.sonatype.org/publish/publish-portal-maven/)

---

# 发布到GitHub Packages（可选）

如果您想发布到GitHub Packages而不是Maven中央仓库，参考library-zero项目的发布方式：
https://github.com/lucaswangdev/library-zero/packages/2248650

## 1. 添加distributionManagement

在pom.xml中添加：

```xml
<distributionManagement>
  <repository>
    <id>github</id>
    <name>GitHub lucaswangdev Apache Maven Packages</name>
    <url>https://maven.pkg.github.com/lucaswangdev/mybatis-gen-plugin</url>
  </repository>
</distributionManagement>
```

## 2. 移除deploy插件的skip配置

在pom.xml中移除或修改deploy插件配置：

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-deploy-plugin</artifactId>
  <version>2.8.2</version>
  <!-- 移除或注释掉skip配置 -->
  <!--
  <configuration>
    <skip>true</skip>
  </configuration>
  -->
</plugin>
```

或者创建一个github-release profile：

```xml
<profiles>
  <profile>
    <id>github-release</id>
    <distributionManagement>
      <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/lucaswangdev/mybatis-gen-plugin</url>
      </repository>
    </distributionManagement>
    <build>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
          <configuration>
            <skip>false</skip>
          </configuration>
        </plugin>
      </plugins>
    </build>
  </profile>
</profiles>
```

## 3. 配置GitHub Token

### 3.1 生成GitHub Personal Access Token
1. 访问 https://github.com/settings/tokens
2. 点击 "Generate new token (classic)"
3. 勾选以下权限：
   - `write:packages` - 发布包
   - `read:packages` - 读取包
   - `delete:packages` - 删除包（可选）
4. 生成并复制token

### 3.2 配置~/.m2/settings.xml

根据GitHub官方文档，完整配置如下：

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo.maven.apache.org/maven2</url>
        </repository>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/lucaswangdev/mybatis-gen-plugin</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>你的GitHub用户名</username>
      <password>你的GitHub Personal Access Token</password>
    </server>
  </servers>
</settings>
```

## 4. 发布到GitHub Packages

### 4.1 确保pom.xml中配置了distributionManagement

```xml
<distributionManagement>
  <repository>
    <id>github</id>
    <name>GitHub lucaswangdev Apache Maven Packages</name>
    <url>https://maven.pkg.github.com/lucaswangdev/mybatis-gen-plugin</url>
  </repository>
</distributionManagement>
```

**重要**：
- `id` 必须与 `~/.m2/settings.xml` 中的 `<server><id>` 匹配
- URL格式：`https://maven.pkg.github.com/所有者/仓库名`
- 仓库名必须使用小写字母

### 4.2 执行发布命令（已配置github-release profile）

```bash
# 发布到GitHub Packages
mvn clean deploy -P github-release
```

**注意事项**：
- 确保 `~/.m2/settings.xml` 中配置了GitHub Token
- 确保Token有 `write:packages` 权限
- GitHub会自动在仓库下创建Packages

**常见401错误排查**：

1. **检查Token权限**
   - 访问 https://github.com/settings/tokens
   - 确认Token包含 `write:packages` 和 `read:packages` 权限
   - 确认Token未过期

2. **检查Token格式**
   - Token应该是以`ghp_`开头的字符串
   - 不是密码，而是Personal Access Token
   - 直接复制整个token，不要有空格或换行

3. **检查Server ID匹配**
   - `~/.m2/settings.xml` 中 `<server><id>github</id>`
   - 必须与 pom.xml 中 `<repository><id>github</id>` 匹配
   - ID区分大小写

4. **测试Token有效性**
   ```bash
   # 使用curl测试GitHub Token
   curl -H "Authorization: token YOUR_TOKEN" https://api.github.com/user
   ```

5. **重新生成Token**
   如果一切配置正确仍然401错误，建议重新生成Token：
   - 访问 https://github.com/settings/tokens
   - 删除旧Token
   - 创建新Token，勾选 `write:packages`、`read:packages`、`delete:packages`
   - 更新 settings.xml 中的密码

### 4.3 验证发布
发布成功后，可以在以下位置查看：
- GitHub仓库的Packages页面
- https://github.com/lucaswangdev/mybatis-gen-plugin/packages

## 5. 使用GitHub Packages中的插件

### 5.1 配置GitHub认证（使用者需要）

使用GitHub Packages中的插件的用户需要在 `~/.m2/settings.xml` 中配置：

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo.maven.apache.org/maven2</url>
        </repository>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/lucaswangdev/*</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <id>github</id>
          <url>https://maven.pkg.github.com/lucaswangdev/*</url>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>你的GitHub用户名</username>
      <password>你的GitHub Personal Access Token</password>
    </server>
  </servers>
</settings>
```

**注意**：
- URL使用通配符 `lucaswangdev/*` 可以访问该用户下的所有包
- Token至少需要 `read:packages` 权限

### 5.2 在项目中使用插件

直接在pom.xml中添加插件配置即可：

```xml
<build>
  <plugins>
    <plugin>
      <groupId>com.lucaswangdev</groupId>
      <artifactId>mybatis-gen-plugin</artifactId>
      <version>1.0.0</version>
      <configuration>
        <configFile>generator.properties</configFile>
      </configuration>
    </plugin>
  </plugins>
</build>
```

### 5.3 运行插件

```bash
mvn mybatis-gen:generate
```

**故障排除**：
- 如果提示"无法找到插件"，检查settings.xml的activeProfile是否启用
- 如果提示"401 Unauthorized"，检查GitHub Token是否正确配置
- 确保Token有 `read:packages` 权限

## 6. 删除GitHub Packages中的包

如果需要删除已发布到GitHub Packages的包，有以下几种方法：

### 6.1 通过GitHub网页界面删除（推荐）

1. 访问包页面：https://github.com/lucaswangdev/mybatis-gen-plugin/packages
2. 点击要删除的包（例如：mybatis-gen-plugin）
3. 在包页面右侧点击"Package settings"
4. 滚动到底部，找到"Danger zone"区域
5. 点击"Delete this package"
6. 输入包名确认删除

### 6.2 通过GitHub API删除

```bash
# 删除整个包
curl -X DELETE \
  -H "Authorization: token YOUR_GITHUB_TOKEN" \
  -H "Accept: application/vnd.github.v3+json" \
  https://api.github.com/user/packages/maven/mybatis-gen-plugin

# 或者删除特定版本
curl -X DELETE \
  -H "Authorization: token YOUR_GITHUB_TOKEN" \
  -H "Accept: application/vnd.github.v3+json" \
  https://api.github.com/user/packages/maven/mybatis-gen-plugin/versions/VERSION_ID
```

**注意**：
- YOUR_GITHUB_TOKEN 需要有 `delete:packages` 权限
- 删除整个包会移除所有版本
- 删除操作不可恢复

### 6.3 删除后的清理工作

删除GitHub Packages后，建议执行：

```bash
# 清理本地Maven缓存
rm -rf ~/.m2/repository/com/lucaswangdev/mybatis-gen-plugin

# 如果要继续使用本地开发版本
mvn clean install
```

---

## 两种发布方式对比

| 特性 | Maven中央仓库 | GitHub Packages |
|------|--------------|----------------|
| 全球访问 | ✅ 所有人无需认证即可使用 | ❌ **所有用户都必须配置GitHub Token** |
| 发布难度 | 较高（需要GPG签名） | 较低（只需GitHub Token） |
| 使用便利性 | ✅ 最佳（用户零配置） | ⚠️ 需要额外配置（settings.xml + Token） |
| 包可见性 | 完全公开 | 公开但需要认证才能下载 |
| 适用场景 | 开源项目，最大化用户便利性 | 私有项目或组织内部使用 |
| 发布命令 | `mvn deploy -P release` | `mvn deploy -P github-release` |

### ⚠️ 重要说明：GitHub Packages的使用限制

**即使包在GitHub上是公开的（Public），所有用户仍然需要配置GitHub Personal Access Token才能下载使用！**

这意味着：
- ✅ 任何人都可以在GitHub上看到包的存在
- ❌ 但**必须生成GitHub Token并配置settings.xml**才能下载和使用
- ⚠️ 这会给每个用户带来额外的配置负担（生成Token + 配置Maven）

**具体影响**：
```xml
<!-- 用户添加这个依赖后 -->
<dependency>
  <groupId>com.lucaswangdev</groupId>
  <artifactId>mybatis-gen-plugin</artifactId>
  <version>1.0.0</version>
</dependency>

<!-- 如果不配置GitHub Token，会报错：401 Unauthorized -->
<!-- 用户必须：
  1. 生成GitHub Personal Access Token（需要read:packages权限）
  2. 在~/.m2/settings.xml中配置Token
  3. 添加repository配置
  才能成功下载
-->
```

**对比**：
- **Maven中央仓库**：用户直接添加dependency即可，Maven自动从中央仓库下载，**零配置**
- **GitHub Packages**：用户需要完成3个步骤的配置才能使用，**配置复杂**

**推荐策略**：
1. **开源项目**：强烈建议发布到Maven中央仓库，让所有用户零配置使用
2. **测试阶段**：可以先发布到GitHub Packages验证功能，确认无误后再发布到中央仓库
3. **私有项目**：使用GitHub Packages，可以通过Token控制访问权限