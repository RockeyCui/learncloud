package com.rockeycui.learn.common.hystrix;


import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author cuishilei
 * @date 2019/3/7
 */
public class MdcAwareCallableWrapper implements HystrixCallableWrapper {

    @Override
    public <T> Callable<T> wrap(Callable<T> callable) {
        return new MdcAwareCallable<>(callable, MDC.getCopyOfContextMap());
    }

    private class MdcAwareCallable<T> implements Callable<T> {

        private final Callable<T> delegate;

        private final Map<String, String> contextMap;

        public MdcAwareCallable(Callable<T> callable, Map<String, String> contextMap) {
            this.delegate = callable;
            this.contextMap = contextMap != null ? contextMap : new HashMap();
        }

        @Override
        public T call() throws Exception {
            try {
                MDC.setContextMap(contextMap);
                return delegate.call();
            } finally {
                MDC.clear();
            }
        }
    }
}
