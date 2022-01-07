package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

  public static void main(String[] args) {
//	AppConfig appConfig = new AppConfig();
//	MemberService memberService = appConfig.memberService();
	ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
	MemberService memberService= applicationContext.getBean("memberService", MemberService.class);
	Member member = new Member(1L, "memberA", Grade.VIP);
	memberService.join(member);

	Member member1 = memberService.findById(1L);
	System.out.println("멤버이름은 " + member1.getName());
	System.out.println(member.getName());
  }
}
