package dev.be.async.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync //선언해야 비동기 프로그래밍을 사용할 수 있다.
public class AsyncConfig {
}
