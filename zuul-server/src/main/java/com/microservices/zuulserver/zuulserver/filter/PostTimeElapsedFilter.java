package com.microservices.zuulserver.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTimeElapsedFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PostTimeElapsedFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("post filter");

        Long startTime = (Long) request.getAttribute("startTime");
        Long finalTime = System.currentTimeMillis();
        Long time = finalTime - startTime;

        log.info(String.format("time elapsed in second %s seg.", time.doubleValue() / 1000.00));
        log.info(String.format("time elapsed in millisecond %s ms.", time));
        return null;
    }
}
