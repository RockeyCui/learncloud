package com.rockeycui.learn.backend.config.hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author cuishilei
 * @date 2019/3/7
 */
@Component
public class RequestContextHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    @Resource
    private List<HystrixCallableWrapper> wrappers;

    @PostConstruct
    private void init() {
        HystrixPlugins.reset();
        HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return new CallableWrapperChain<>(callable, wrappers.iterator()).wrapCallable();
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
