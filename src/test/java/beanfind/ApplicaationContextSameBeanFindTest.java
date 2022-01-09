package beanfind;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicaationContextSameBeanFindTest {

  //AppicationContext 를 스프링 컨테이너라고 한다.
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

  @Test
  @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
  void findBeanByTypeDuplicate() {
//	MemberRepository bean = ac.getBean(MemberRepository.class);
	Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
		() -> ac.getBean(MemberRepository.class)
		);
  }

  @Test
  @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
  void findBeanByName(){
	MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
	assertThat(memberRepository).isInstanceOf(MemberRepository.class);
  }
  //타입 똑같은거 밑에 두개 있어도 이름만 지정해주면 잘 나오네?

  @Test
  @DisplayName("특정 타입을 모두 조회하기")
  void findAllBeanByType(){
	Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
	for(String key : beansOfType.keySet()){
	  System.out.println("**********************************");
	  System.out.println("key = " + key +  " value = " + beansOfType.get(key));
	}
	assertThat(beansOfType.size()).isEqualTo(2);
  }



  @Configuration
  static class SameBeanConfig {

	@Bean
	public MemberRepository memberRepository1() {
	  return new MemoryMemberRepository();
	}

	@Bean
	public MemberRepository memberRepository2() {
	  return new MemoryMemberRepository();
	}
  }

}
