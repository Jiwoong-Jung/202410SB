package edu.du.sb1010.config;

import edu.du.sb1010.spring.MemberPrinter;
import edu.du.sb1010.spring.MemberSummaryPrinter;
import edu.du.sb1010.spring.VersionPrinter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"edu.du.sb1010.spring", "edu.du.sb1010.spring2"},
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ManualBean.class )}
)
public class AppCtx {

	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
