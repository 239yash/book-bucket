package com.example.bookBucketBackend.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class BaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Filter In");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("Filter Out");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
