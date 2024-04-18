# concert-reservation
항해플러스 chapter2 서버구축 챕터 프로젝트 [콘서트예약]

## ✔ 트러블 슈팅

### 1. 순환참조

* 문제


좌석 도메인 컴포넌트인 SeatReader에서 순환참조 문제 발생  

* 원인

UseCase에서 책임을 최소화 하기 위하여 간단한 값에 대한 검증로직 또한 컴포넌트영역내에서 처리되게끔 하위 레이어로 조정하면서 순환참조가 발생!

데이터의 값에 대한 검사를 진행하는 SeatValidator와 SeatReader간 상호 참조형태

SeatValidator와 SeatReader의 책임과 역할이 불분명 하여 서로 참조하는 형태로 구현이 진행되었었음

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
