package dev.be.async.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final EmailService emailService; // 빈을 프록시 객체로 매핑해서 스프링이 비동기 처리에 동작할 수 있게끔 가져온다.

    //정상적인 비동기 처리
    //다 다른 스레드들이 찍힘
    //스프링 프레임워크에서 비동기로 처리하려면, 빈으로 등록을 해서 빈을 가져와 사용해야 한다.
    public void asyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail(); //비동기로 동작할 수 있게 Sub Thread 에게 위임
        emailService.sendMailWithCustomThreadPool();
    }

    //비정상적인 처리
    //동일한 스레드들이 찍힘, 비동기 프로그래밍 X
    //인스턴스를 생성해 사용했기 때문에 스프링 프레임워크가 모른다.(빈 등록이 되어 있지 않다.)
    public void asyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService = new EmailService();
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    //비정상적인 처리
    //동일한 스레드들이 찍힘, 비동기 프로그래밍 X
    //이미 이 클래스에서 빈(EmailService)을 가져왔기 때문에 Async 어노테이션을 붙이지 않은 메소드처럼 동작하게 됨
    //따라서 비동기 프로그래밍을 하려면, 빈 주입을 받아야 한다.
    public void asyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async
    public void sendMail() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
    }

}
