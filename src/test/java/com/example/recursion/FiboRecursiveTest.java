package com.example.recursion;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
}
