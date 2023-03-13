# Server SW 2팀 Spring WAS Seed
## 신규 프로젝트 시작
커맨드 예시는 ATAS를 기준으로 한다.
1. 신규 프로젝트의 부모 폴더로 이동 후 Seed project를 clone한다.
   ```console
   $ git clone https://183.99.50.117/serverswdev2/server2_spring_was_seed.git
   ```
1. server2_spring_was_seed 폴더를 신규 프로젝트의 repository 이름과 동일하게 변경한다.
   ```console
   $ mv server2_spring_was_seed atas-web
   ```
1. `atas-web` 폴더로 이동 후 `pom.xml`을 편집한다. `<artifactId>`와 `<name>`을 동일한 이름으로 변경하고 `<description>`을 작성한다.
   ```xml
   <groupId>com.innowireless</groupId>
   <artifactId>AtasWas</artifactId>
   <version>1.0.0</version>
   
   <name>AtasWas</name>
   <description>ATAS Web Application Server</description>
   ```
1. `.git` 폴더를 삭제하고 새로운 Git 저장소를 생성한다.
   ```console
   $ del .git

   $ git init
   $ git remote add origin https://183.99.50.117/atas/atas-web.git
   $ git add .
   $ git commit -m "Initial commit"
   $ git push -u origin master
   ```

## 신규 프로젝트 설정
### 데이터베이스 사용
로그인 테스트를 위해 H2 데이터베이스를 사용하고 있으므로 아래와 같이 변경한다.

`application.yml`에서 `spring.datasource`를 사용할 데이터베이스 정보에 맞춰 변경한다.
```yml
spring:
  datasource:
    url: jdbc:postgresql://${ip}:${port}/${db_name}
    username: ${username}
    password: ${password}
```

`pom.xml`에서 H2 데이터베이스에 대한 dependency를 제거하거나 주석 처리한다.
```xml
<!-- 
<dependency>
   <groupId>com.h2database</groupId>
   <artifactId>h2</artifactId>
   <scope>runtime</scope>
</dependency>
-->
```

### gRPC 사용
1. `pom.xml`에서 gRPC 관련 dependency의 주석을 제거한다.
   ```xml
   <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-okhttp</artifactId>
      <version>1.31.0</version>
   </dependency>
   <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-protobuf</artifactId>
      <version>1.31.0</version>
   </dependency>
   <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-stub</artifactId>
      <version>1.31.0</version>
   </dependency>
   ```

1. `build` 요소에서 `os-maven-plugin`와 `protobuf-maven-plugin`에 대한 주석을 제거한다.
   ```xml
   <build>
      <extensions>
         <extension>
               <groupId>kr.motd.maven</groupId>
               <artifactId>os-maven-plugin</artifactId>
               <version>1.6.0</version>
         </extension>
      </extensions>
      <plugins>
         <plugin>
               <groupId>org.xolstice.maven.plugins</groupId>
               <artifactId>protobuf-maven-plugin</artifactId>
               <version>0.6.1</version>
               <configuration>
                  <protocArtifact>com.google.protobuf:protoc:3.5.1-1:exe:${os.detected.classifier}</protocArtifact>
                  <pluginId>grpc-java</pluginId>
                  <pluginArtifact>io.grpc:protoc-gen-grpc-java:1.18.0:exe:${os.detected.classifier}</pluginArtifact>
                  <protoSourceRoot>${basedir}/src/main/resources/grpc</protoSourceRoot>
                  <outputDirectory>${project.build.sourceDirectory}</outputDirectory>
                  <clearOutputDirectory>false</clearOutputDirectory>
               </configuration>
               <executions>
                  <execution>
                     <goals>
                           <goal>compile</goal>
                           <goal>compile-custom</goal>
                     </goals>
                  </execution>
               </executions>
         </plugin>
         ...
   ```
1. proto 파일(`resources/grpc/*.proto`) 변경 후 `mvn compile`을 실행 한다.