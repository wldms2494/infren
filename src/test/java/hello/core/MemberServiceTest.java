package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
  @Test
  void join(){
	AppConfig appConfig = new AppConfig();
	 MemberService memberService = appConfig.memberService();

	//given
	Member member = new Member(1L, "memberA", Grade.VIP);
	//when
	memberService.join(member);
	Member foundMember = memberService.findById(member.getId());
	//then
	Assertions.assertThat(member).isEqualTo(foundMember);
  }

}
