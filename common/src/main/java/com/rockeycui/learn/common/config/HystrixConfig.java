package com.rockeycui.learn.common.config;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.rockeycui.learn.common.hystrix.HystrixCallableWrapper;
import com.rockeycui.learn.common.hystrix.MdcAwareCallableWrapper;
import com.rockeycui.learn.common.hystrix.RequestAttributeAwareCallableWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

@Configuration
public class HystrixConfig {

    @Bean
    public HystrixConcurrencyStrategy concurrencyStrategy() {
        List<HystrixCallableWrapper> wrappers = new ArrayList<>();
        wrappers.add(new MdcAwareCallableWrapper());
        wrappers.add(new RequestAttributeAwareCallableWrapper());
        return new HystrixConcurrencyStrategy() {
            @PostConstruct
            private void init() {
                HystrixPlugins.reset();
                HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
            }

            @Override
            public <T> Callable<T> wrapCallable(Callable<T> callable) {
                return new CallableWrapperChain<>(callable, wrappers.iterator()).wrapCallable();
            }
        };
    }

    private static class CallableWrapperChain<T> {

        private final Callable<T> callable;

        private final Iterator<HystrixCallableWrapper> wrappers;

        CallableWrapperChain(Callable<T> callable, Iterator<HystrixCallableWrapper> wrappers) {
            this.callable = callable;
            this.wrappers = wrappers;
        }

        Callable<T> wrapCallable() {
            Callable<T> delegate = callable;
            while (wrappers.hasNext()) {
                delegate = wrappers.next().wrap(delegate);
            }
            return delegate;
        }
    }
}
