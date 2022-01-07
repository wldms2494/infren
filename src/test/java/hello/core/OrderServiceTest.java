package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
  MemberService memberService;
  OrderService orderService;

@BeforeEach
public void beforeEach(){
  AppConfig appConfig = new AppConfig();
  memberService = appConfig.memberService();
  orderService = appConfig.orderService();
}
  @Test
  @DisplayName("정액할인 테스트")
  void createOrder(){
	Member memberA = new Member(1L, "memberA", Grade.VIP);
	memberService.join(memberA);
	Order order = orderService.createOrder(memberA.getId(), "itemA", 10000);
	Assertions.assertEquals(order.getDiscountPrice(),1000);
  }

}
