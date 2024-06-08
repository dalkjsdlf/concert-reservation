# concert-reservation
항해플러스 chapter2 서버구축 챕터 프로젝트 [콘서트예약]

## ✔ 인덱스 설계를 위한 쿼리 및 데이터 패턴 조사
 https://thoracic-catamaran-22f.notion.site/4bcf9632b896492d89b20041ec69abce?pvs=4
## ✔ 동시성 이슈에 대한 분석
 https://thoracic-catamaran-22f.notion.site/de17237568694b2386a43715543aa4f1?pvs=4
## ✔ 깃 브랜치 전략

### 🎯 main    : 운영(Production) 배포 소스, 최신화 소스

#### -> 개발시 DEVELOP으로 BRANCH, RELEASE로 부터 MERGING

### 🎯 release : QA(Staging) 개발이 완료되어 MERGING된 소스, 운영 반영전 QA TEST시 사용

#### -> DEVELOP으로 부터 MERGING, MAIN으로 MERGING

### 🎯 develop : 개발(Development) 

#### -> 개발시 MAIN으로부터 BRANCH, FEATURE로 부터 MERGING, RELEASE로 MERGING

### 🎯 feature : 개발 기능 단위로 FEATURE 브랜치로 개발

#### branch 명칭 --> feature/#[number]_[feature명칭]  ex) feature/#1_login

#### -> 개발시 DEVELOP으로부터 BRANCH, DEVELOP으로 MERGING


## ✔ 트러블 슈팅

### 1. 순환참조

* 문제


좌석 도메인 컴포넌트인 SeatReader에서 순환참조 문제 발생  

* 원인

UseCase에서 책임을 최소화 하기 위하여 간단한 값에 대한 검증로직을 컴포넌트영역 내에서만 구현하기 위해 하향 조정하면서 컴포넌트 레이어 안에서 순환참조가 발생!

데이터의 값에 대한 검사를 진행하는 SeatValidator와 SeatReader간 상호 참조형태

SeatValidator에서 좌석 데이터를 조회한다는 이유로 SeatReader를 사용하는 불상사가 발생

SeatValidator와 SeatReader의 책임과 역할이 불분명 하였으며, 참조방향을 정해놓고 개발하지 않아 서로 참조하는 형태가 되버림.

![image](https://github.com/dalkjsdlf/concert-reservation/assets/38232007/6f9a491e-e2b2-4c26-91c2-910c469c6ffa)


* 해결

도메인 컴포넌트간에도 '참조'가 필요하다면 참조 방향을 정의할 필요가 있음.

그 방향을 설정하기 위해 컴포넌트 책임 역할을 정의할 필요가 있어 아래와 같이 정의.

-> SeatValidator는 상위 레이어(UseCase)혹은 같은 레이어의 값 검증을 위한 보조도구 용도로 컴포넌트나 그 상위 레이어를 참조하지 않는다. 

-> SeatReader는 상위 는 정말 순수 조회형태로 SeatValidator를 이용하여 값 검증기능이 존재하여 SeatValidator를 사용할 수 있으나 SeatValidator를 위하여 데이터를 조회한다고 해서 SeatReader를 사용할 수는 없다.

-> SeatSupportor는 UseCase를 지원하는 기능모음이다. SeatSupportor에서는 SearReader나 SeatValidator를 참조할 수 있으나 방대 방향은 존재하지 않는다.

따라서 컴포넌트간에서 참조 방향을 아래와 같이 설정하였다.

Supportor -> Reader,Modifier -> Validator

반대방향은 허용X

![image](https://github.com/dalkjsdlf/concert-reservation/assets/38232007/23ac8c30-2ef3-4af4-b401-007e43b85f63)

### 2. 인터셉터 미적용 문제

* 문제

Token값을 검증하는 인터셉터인 TokenValidationInterceptor가 Runtime시 적용되지가 않음 (Request시 인터셉터를 무시)

* 원인

TokenValidationInterceptor는 @Component로 정의하여 사용하면서 

WebMvcConfigurer 구현체인 WebConfig 클래스에는 addInterceptor에서 적용할 때는 new로 객체생성하여 사용함

* 해결 

WebConfig클래스에 @RequeiredArgsConstructor를 통해 TokenValidationInterceptor객체를 생성자 주입으로 적용하여 해결


### 3. 테스트 코드에서 LOMBOK 사용하기

* 문제

테스트 코드에서는 SpringBootTest로 테스트하여도 Slf4j가 사용이 안됨


* 원인

테스트코드에서는 lombok에 대하여 별도의 dependency가 필요함

* 해결 

Gradle에 아래 내용 추가하고 사용
```
testCompileOnly 'org.projectlombok:lombok'
testAnnotationProcessor 'org.projectlombok:lombok'
```


## ✔ SWAGGER
### 1)
![스웨거 캡쳐1](https://github.com/dalkjsdlf/concert-reservation/assets/38232007/10bc6b6d-2135-4ea0-aefb-79625b6bc36a)

### 2)
![스웨거 캡처2](https://github.com/dalkjsdlf/concert-reservation/assets/38232007/0b67833b-59be-4d76-b48f-5a87781d424a)


## ✔ 기능명세
## notion url
https://thoracic-catamaran-22f.notion.site/6b5a4ec795134cf9a80484b601effb61?pvs=4

## ✔ 마일스톤
### notion url
https://thoracic-catamaran-22f.notion.site/Project-Milestone-29339f9c0e114750a5a1c3096609304f?pvs=4

## ✔ API 명세
### notion url
https://thoracic-catamaran-22f.notion.site/API-2ae57d00a361458bbec85797d42c0712?pvs=4

## ✔ 플로우차트
![flowchart](https://github.com/dalkjsdlf/concert-reservation/assets/38232007/af791dda-1e62-4974-8c8f-ac2bb08a63b5)

## ✔ 시퀀스 다이어그램

### 대기열 처리
![콘서트예매접근 drawio](https://github.com/dalkjsdlf/concert-reservation/assets/38232007/bb77b4ba-c5b3-4522-9e84-01c5e90450a6)

### 콘서트예매
![콘서트예매시퀀스 drawio](https://github.com/dalkjsdlf/concert-reservation/assets/38232007/ed813b29-50df-4287-8c52-0191e127dac9)

### 예약결제
![예약결제 drawio](https://github.com/dalkjsdlf/concert-reservation/assets/38232007/5bf52465-cadc-4b87-9be6-3177032457eb)


## ✔ ERD 문서
![concertreservation_erd](https://github.com/dalkjsdlf/concert-reservation/assets/38232007/42efbad8-f0a9-4179-a9e2-38748982057b)

## MockAPI
아래 소스경로 하위 도메인
src/main/java/io/hpp/concertreservation/biz/api
