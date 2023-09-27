# 비동기 프로그래밍

스프링 비동기 프로그래밍에 대해 간단히 배워본다.

**Pull requests -> closed 확인**

## 비동기 프로그래밍이란?
Main Thread가 Task를 처리하는게 아니라, Sub Thread에게 Task를 위임하는 행위이다.

Sub Thread는 어떻게 생성되고 관리할까?
-> 스프링에서 Thread Pool을 생성해 비동기 작업을 처리한다.

따라서, ***스프링 빈으로 등록시켜*** 빈을 가져와 비동기 처리를 수행해야 한다.

#### 장점
- 다수의 동시 요청을 처리할 수 있어 성능이 향상된다.
- 애플리케이션이 더 빠르게 응답해 사용자 경험이 향상된다.
- 스레드 관리를 통해 효율적인 자원 관리가 가능하다.

#### 구성
- **corePoolsize** : 해당 Thread Pool에 **최소** 몇 개의 Thread를 가지고 있을 것인지 지정, 지정한 사이즈만큼 자원을 점유하고 있기 때문에 너무 큰 값을 설정해버리면 낭비가 됨.
- **maximumPoolSize** : 해당 Thread Pool에 **최대** 몇 개의 Thread를 가지고 있을 것인지 지정
- **keepAliveTime** : 지정한 시간만큼 Thread들이 일하지 않으면 자원을 반납하겠다는 옵션
- **unit** : keepAliveTime에서 시,분,초를 지정하기 위한 옵션
- **workQueue** : 큐를 사용해 workQueue에 많은 요청을 담아 놓음 -> 작업 중인 Thread들이 현재 Task가 마무리 되면 -> 워크큐에서 다음 작업을 할 Thread들을 가져온다.

#### 과정
corePoolSize 만큼 Thread를 생성하고

workQueue에 Task를 담고

다 차면

그 다음 maximumPoolSize만큼 새로운 Thread를 생성한다.


#### *
비동기 프로그래밍을 할 때, 스레드 이름을 찍어보면서 테스트를 하는 습관을 가지는 것이 좋다.

