## MEHAGO

db설정 변경...

```yml
# datasource
datasource:
  driver-class-name: org.mariadb.jdbc.Driver
  url: jdbc:mysql://127.0.0.1:3306/mehago?characterEncoding=utf8
  username: mehago
  password: mehago
  initialSize: 10
  maxActive: 100
```

spring-boot run (backend 나중에 추가해야됨 npm run dev:backend)

```bash
$ npm run dev
```

springboot와 통신
경로 : /api

backend와 통신
경로 : /chat

### 구조

#### spring

<pre>
    src
    |--- /main
            |--- /java
            |       |--- /com/douzone/mehago
            |               |--- /config
            |               |       |--- WebConfig.java
            |               |--- /controller
            |               |       |--- AccountController.java
            |               |--- /repository
            |               |       |--- AccountRepository.java
            |               |--- /service
            |               |       |--- AccountService.java
            |               |--- /vo
            |               |       |--- Account.java
            |               |--- /utils
            |               |       |--- AES.java
            |               |       |--- InvaildJwtException.java
            |               |       |--- JwtDecoder.java
            |               |       |--- JwtTokenUtil.java
            |               |--- MehagoApplication.java
            |--- /resource
                    |--- /mybatis
                    |       |--- /mappers
                    |       |       |--- account.xml
                    |       |--- configuration.xml
                    |--- application.yml
</pre>

JWT <https://github.com/MeltingZone-douzone/mehago/tree/main/docs/jwt/>

#### frontend

<pre>
    frontend
        |--- /config
        |       |--- babel.config.json
        |       |--- webpack.config.js
        |--- /public
        |       |--- /assets
        |       |       |--- /js
        |       |               |-- main.js
        |       |--- favicon.ico
        |       |--- index.html
        |--- /src
</pre>

#### backend
