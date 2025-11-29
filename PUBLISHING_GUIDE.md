# Publishing MyBatis Generator Maven Plugin to Maven Central

This guide explains how to publish the mybatis-gen-plugin to Maven Central Repository so that other developers can easily use it in their projects.

## Prerequisites

1. Sonatype JIRA account
2. GPG key for signing artifacts
3. Proper Maven settings.xml configuration

## Steps to Publish

### 1. Prepare Your Environment

1. Generate a GPG key if you don't have one:
   ```bash
   gpg --gen-key
   ```

2. Get your key ID:
   ```bash
   gpg --list-keys
   ```

3. Distribute your public key:
   ```bash
   gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID
   ```

### 2. Configure Maven Settings

Add the following to your `~/.m2/settings.xml`:

```xml
<settings>
  <servers>
    <server>
      <id>ossrh</id>
      <username>your_sonatype_username</username>
      <password>your_sonatype_password</password>
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
        <gpg.keyname>YOUR_KEY_ID</gpg.keyname>
        <gpg.passphrase>your_gpg_passphrase</gpg.passphrase>
      </properties>
    </profile>
  </profiles>
</settings>
```

### 3. Update Project POM

Ensure your project POM has all the required information:

```xml
<name>mybatis-gen-plugin</name>
<description>Maven plugin for code generation based on database model and velocity template</description>
<url>https://github.com/yourusername/mybatis-gen-plugin</url>

<licenses>
  <license>
    <name>MIT License</name>
    <url>http://www.opensource.org/licenses/mit-license.php</url>
  </license>
</licenses>

<developers>
  <developer>
    <name>Your Name</name>
    <email>your.email@example.com</email>
    <organization>Your Organization</organization>
    <organizationUrl>http://www.yourorganization.com</organizationUrl>
  </developer>
</developers>

<scm>
  <connection>scm:git:git://github.com/yourusername/mybatis-gen-plugin.git</connection>
  <developerConnection>scm:git:ssh://github.com:yourusername/mybatis-gen-plugin.git</developerConnection>
  <url>http://github.com/yourusername/mybatis-gen-plugin/tree/master</url>
</scm>
```

### 4. Add Distribution Management

Add this to your POM:

```xml
<distributionManagement>
  <snapshotRepository>
    <id>ossrh</id>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
  </snapshotRepository>
  <repository>
    <id>ossrh</id>
    <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>
  </repository>
</distributionManagement>
```

### 5. Add Plugins for Deployment

Add these plugins to your POM:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.sonatype.plugins</groupId>
      <artifactId>nexus-staging-maven-plugin</artifactId>
      <version>1.6.7</version>
      <extensions>true</extensions>
      <configuration>
        <serverId>ossrh</serverId>
        <nexusUrl>https://oss.sonatype.org/</nexusUrl>
        <autoReleaseAfterClose>true</autoReleaseAfterClose>
      </configuration>
    </plugin>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-source-plugin</artifactId>
      <version>2.2.1</version>
      <executions>
        <execution>
          <id>attach-sources</id>
          <goals>
            <goal>jar-no-fork</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-javadoc-plugin</artifactId>
      <version>2.9.1</version>
      <executions>
        <execution>
          <id>attach-javadocs</id>
          <goals>
            <goal>jar</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
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
    </plugin>
  </plugins>
</build>
```

### 6. Deploy to Maven Central

1. Create a JIRA ticket at https://issues.sonatype.org to get your groupId approved
2. Once approved, deploy a snapshot:
   ```bash
   mvn clean deploy
   ```

3. Deploy a release:
   ```bash
   mvn versions:set -DnewVersion=1.0.0
   mvn clean deploy -P release
   ```

## Usage in Other Projects

Once published, other developers can use the plugin by adding it to their project's pom.xml:

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

Then run:
```bash
mvn mybatis-gen:generate
```