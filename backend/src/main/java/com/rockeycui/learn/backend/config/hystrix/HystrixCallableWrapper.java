package com.rockeycui.learn.backend.config.hystrix;

import java.util.concurrent.Callable;

/**
 * @author cuishilei
 * @date 2019/3/7
 */
public interface HystrixCallableWrapper {
    /**
     * 包装Callable实例
     *
     * @param callable 待包装实例
     * @param <T>      返回类型
     * @return 包装后的实例
     */
    <T> Callable<T> wrap(Callable<T> callable);
}
