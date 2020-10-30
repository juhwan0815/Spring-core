package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;

        memberService.join(new Member(memberId, "memberA", Grade.VIP));

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}

