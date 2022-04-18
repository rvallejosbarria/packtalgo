package com.example.recursion;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import com.jayway.awaitility.core.ConditionTimeoutException;

public class FiboRecursiveTest {
    @Test
    public void fibo_recursive_test() {
        // given
        long number = 12;

        // when
        Fibo fibo = new FiboRecursive();
        Long result = fibo.fib(number);

        // then
        assertThat(result).isEqualTo(144L);
    }

    @Test
    public void fibo_tail_recursive_test() {
        // given
        long number = 12;

        // when
        Fibo fibo = new FiboTailRecursive();
        Long result = fibo.fib(number);

        // then
        assertThat(result).isEqualTo(144L);
    }

    @Test(expected = ConditionTimeoutException.class)
    public void fibo_recursive_test_huge_when_is_not_using_tail_recursion() {
        // given
        long number = 120;

        // when
        Fibo fibo = new FiboRecursive();
        AtomicLong result = new AtomicLong();
        Awaitility.await().atMost(new Duration(1, TimeUnit.MINUTES)).until(
            () -> result.set(fibo.fib(number))
        );

        // then
        assertThat(result.get()).isEqualTo(144L);
    }

    @Test
    public void fibo_tail_recursive_test_huge_should_success_when_using_tail_recursion() {
        // given
        long number = 120;

        // when
        Fibo fibo = new FiboTailRecursive();
        AtomicLong result = new AtomicLong();
        result.set(fibo.fib(number));

        // then
        assertThat(result.get()).isEqualTo(4376692037216111008L);
    }
}
