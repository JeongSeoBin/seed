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