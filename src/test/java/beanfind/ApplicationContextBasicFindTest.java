package beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByName() {
	MemberService memberService = ac.getBean("memberService", MemberService.class);
//	System.out.println("memberService = " + memberService);
//	System.out.println("memberService = " + memberService.getClass());
	Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("빈 이름말고 타입으로만 조회")
  void findBeanByType() {
	MemberService memberService = ac.getBean( MemberService.class);
	Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

  }

  @Test
  @DisplayName("인텊이스  말고 구체 타입으로 조회")
  void findBeanByName2() {
	//근데 구체 타입으로 조회하면 유연성이 많이 떨어진다. 인터페이스로 ㄱㄱ
	MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
	Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("빈이름으로 조회 X")
  void findBeanByNameX(){
	//ac.getBean("xxxx", MemberService.class);
	org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, ()->
		ac.getBean("xxxx",MemberService.class));
	//딱 저 예외가 터져야함. 아니면 테스트코드 실패
  }



}
