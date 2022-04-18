package com.example.recursion;

public class FiboRecursive implements Fibo {

    @Override
    public long fib(long index) {
        if (index == 0) {
            return 0;
        } else if (index == 1) {
            return 1;
        } else {
            return fib(index - 1) + fib(index - 2);
        }
    }

}
