package beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("모든 빈 출력하기")
  void findAllbean(){
	String[] beanDefinitionNames = ac.getBeanDefinitionNames();//extract - ctrl alt V
	//iterator =  iter + tap (for문 자동완성)
	for (String beanDefinitionName : beanDefinitionNames) {
	  Object bean = ac.getBean(beanDefinitionName);
	  System.out.println("name = " + beanDefinitionName + "// object = "+ bean);

	}
  }




  @Test
  @DisplayName("application 빈 출력하기")
  void findApplicationbean(){
	String[] beanDefinitionNames = ac.getBeanDefinitionNames();//extract - ctrl alt V
	//iterator =  iter + tap (for문 자동완성)
	for (String beanDefinitionName : beanDefinitionNames) {
	  BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

	  if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
		Object bean = ac.getBean(beanDefinitionName);
		System.out.println("name = " + beanDefinitionName + "// object = "+ bean);

	  }
	}
  }
}
