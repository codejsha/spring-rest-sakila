# spring-rest-sakila

Sakila REST API Service (Sample Project)

![Gradle Build](https://github.com/codejsha/spring-rest-sakila/actions/workflows/gradle.yml/badge.svg)

[English](README.md) | [Korean](README_ko-KR.md)

Sakila REST API 서비스는 학습 및 테스트 목적으로 MySQL에서 제공하는 샘플 데이터베이스인 Sakila 데이터베이스에 대한 액세스를 제공합니다. Sakila 데이터베이스는 DVD 대여점 회사를 모델링하고 있으며, 영화, 배우, 고객, 대여 등에 대한 데이터를 포함합니다.

이 서비스는 Sakila 데이터베이스에서 CRUD(Create, Read, Update, Delete) 작업을 수행하는 간단한 방법을 제공합니다. 또한 복잡한 쿼리를 수행하는 엔드포인트도 제공합니다.

## 목차

- [목차](#목차)
- [시작하기](#시작하기)
  - [요구사항](#요구사항)
  - [디펜던시, 플러그인](#디펜던시-플러그인)
  - [외부 MySQL 데이터베이스](#외부-mysql-데이터베이스)
- [설치](#설치)
  - [리포지토리 다운로드](#리포지토리-다운로드)
  - [데이터베이스 연결 설정 구성](#데이터베이스-연결-설정-구성)
  - [어플리케이션 설정 구성](#어플리케이션-설정-구성)
  - [프로젝트 빌드](#프로젝트-빌드)
  - [어플리케이션 실행](#어플리케이션-실행)
- [API 문서](#api-문서)
  - [엔드포인트](#엔드포인트)
  - [레퍼런스](#레퍼런스)
- [관측가능성](#관측가능성)
- [샘플 데이터](#샘플-데이터)
- [라이선스](#라이선스)

## 시작하기

### 요구사항

- Java 17
- Gradle 7
- MySQL 8

### 디펜던시, 플러그인

- Spring Boot Web
- Spring Data JPA
- Spring HATEOAS
- Spring REST Docs
- Lombok
- Querydsl
- Blaze Persistence
- MapStruct
- Guava
- Asciidoctor

### 외부 MySQL 데이터베이스

애플리케이션을 실행하려면 외부 MySQL 데이터베이스가 필요합니다. 외부 데이터베이스는 [MySQL docker](https://hub.docker.com/_/mysql) 컨테이너 또는 온-프레미스 프로세스로 실행할 수 있습니다 ([MySQL Community Downloads](https://dev.mysql.com/downloads/)). 설치 후 [Sakila 데이터베이스](https://dev.mysql.com/doc/sakila/en/) 공식 가이드를 따라 데이터베이스 구조를 생성하고 데이터를 채워넣습니다. 환경 구성 예제는 다음 링크를 참고하세요: https://github.com/codejsha/infrastructure-examples/tree/main/mysql

## 설치

### 리포지토리 다운로드

```bash
# GitHub CLI
gh repo clone codejsha/spring-rest-sakila
# Git CLI
git clone https://github.com/codejsha/spring-rest-sakila.git
```

### 데이터베이스 연결 설정 구성

Sakila REST API 서비스를 사용하기 전에 데이터베이스 연결 설정을 구성해야 합니다. 데이터베이스 연결 설정은 `application.yaml` 파일에 정의됩니다. 기본 설정은 다음과 같습니다:

```yaml
# application.yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/sakila
    username: sakila
    password: sakila
```

### 어플리케이션 설정 구성

어플리케이션 API 엔드포인트에 대한 기본 URL은 `http://localhost:8080/api/v1` 입니다.

```yaml
# application.yaml
server:
  port: 8080
  servlet:
    context-path: /api/v1
...

app:
  uri:
    scheme: http
    host: localhost
    port: ${server.port}
```

### 프로젝트 빌드

```bash
cd spring-rest-sakila
bash ./gradlew build
```

### 어플리케이션 실행

```bash
bash ./gradlew bootRun
```

## API 문서

### 엔드포인트

Sakila REST API 서비스는 다음과 같은 몇 가지 엔드포인트를 제공합니다. 각 엔드포인트는 CRUD(Create, Read, Update, Delete) 작업을 지원합니다.

- Actors: `/api/v1/actors`
- Customers: `/api/v1/customers`
- Films: `/api/v1/films`
- Payments: `/api/v1/payments`
- Rentals: `/api/v1/rentals`
- Reports: `/api/v1/reports`
- Staffs: `/api/v1/staffs`
- Stores: `/api/v1/stores`

### 레퍼런스

API 레퍼런스는 Spring REST Docs에서 Asciidoctor를 사용하여 생성되며 어플리케이션에서 `/api/v1/docs/index.html`로 제공됩니다.

## 관측가능성

어플리케이션은 Spring Boot Actuator와 Micrometer를 사용하여 관측가능성을 제공합니다.

- Actuator: `/api/v1/actuator`
- Prometheus: `/api/v1/actuator/prometheus`

```yaml
# application.yaml
management:
  endpoints:
    web:
      exposure:
        include: "health,prometheus"
        # include: "*"
    jmx:
      exposure:
        exclude: "*"
```

## 샘플 데이터

샘플 데이터는 [MySQL Sakila sample database](https://dev.mysql.com/doc/sakila/en/)에서 가져온 것입니다. DVD 대여점에 관련된 데이터를 포함하고 있으며, 영화, 배우, 고객, 대여 등에 대한 데이터를 포함하고 있습니다. 또한 데이터베이스에서는 뷰, 저장 프로시저, 트리거도 제공합니다.

## 라이선스

이 프로젝트는 Apache License 2.0 라이선스를 따릅니다.

```text
Copyright 2023 Jinseong Ha

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
