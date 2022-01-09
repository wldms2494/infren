package beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FIxDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {

  //상속 관계 테스트
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  @DisplayName("부모 출력하면 자식들도 나오냐")
	//내가 먼저 작성해본 테스트
  void findBeanByParentsClass() {
	Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
	for (String key : beansOfType.keySet()) {
	  System.out.println("key - " + key);
	}
  }

  @Test
  @DisplayName("부모 타입으로 조회시 , 자식이 둘이상있으면 noUniqueException발생?")
  void findBeanByParentTypeDuplicate() {
//    DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
	assertThrows(NoUniqueBeanDefinitionException.class,
		() -> ac.getBean(DiscountPolicy.class));
  }

  @Test
  @DisplayName("부모 타입으로 조회시 , 자식이 둘이상있으면 빈 이름을 지정하면 된다")
  void findBeanByParentTypeBeanName() {
	DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
	assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("특정 하위 타입으로 조회")
  void findBeanBySubType(){
    RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
    assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
  }

  @Configuration
  static class TestConfig {

	@Bean
	public DiscountPolicy rateDiscountPolicy() {
	  return new RateDiscountPolicy();
	}

	@Bean
	public DiscountPolicy fixDiscountPolicy() {
	  return new FIxDiscountPolicy();
	}
  }
}
