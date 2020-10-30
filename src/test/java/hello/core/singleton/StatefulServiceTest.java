package hello.core.singleton;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
        int userA = statefulService1.order("userA", 10000);

        // ThreadA : B사용자 20000원 주문
        int userB = statefulService2.order("userB", 20000);

        System.out.println("userA = " + userA);
        System.out.println("userB = " + userB);

        // ThreadA : 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }


    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}