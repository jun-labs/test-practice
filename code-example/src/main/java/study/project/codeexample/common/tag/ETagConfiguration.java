package study.project.codeexample.common.tag;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class ETagConfiguration {

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> filterFilterRegistrationBean() {
        FilterRegistrationBean<ShallowEtagHeaderFilter> filterFilterRegistrationBean = getShallowRegistrationBean();
        filterFilterRegistrationBean.setEnabled(true);
        filterFilterRegistrationBean.setAsyncSupported(true);
        filterFilterRegistrationBean.addUrlPatterns("/api/e-tags/*");
        filterFilterRegistrationBean.setName("etagFilter");
        return filterFilterRegistrationBean;
    }

    private FilterRegistrationBean<ShallowEtagHeaderFilter> getShallowRegistrationBean() {
        ShallowEtagHeaderFilter filter = new ShallowEtagHeaderFilter();
        filter.setWriteWeakETag(true);
        return new FilterRegistrationBean<>(filter);
    }
}

