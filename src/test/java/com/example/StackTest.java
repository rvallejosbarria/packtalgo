package com.example;

import org.junit.Test;
import java.util.Stack;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StackTest {
    @Test
    public void when_push_to_stack_then_should_retrieve_it() {
        // given
        Stack<String> stack = new Stack<>();

        // when
        stack.push("A");

        // then
        assertThat(stack.size()).isEqualTo(1);

        // and when
        String element = stack.pop();
        assertThat(element).isEqualTo("A");
        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    public void when_push_to_stack_then_should_retrieve_elements_in_the_proper_order() {
        // given
        Stack<String> stack = new Stack<>();

        // when
        stack.push("A");
        stack.push("B");
        stack.push("C");

        // then
        assertThat(stack.pop()).isEqualTo("C");
        assertThat(stack.pop()).isEqualTo("B");
        assertThat(stack.pop()).isEqualTo("A");
    }

    @Test
    public void when_push_and_peek_element_should_not_remove_it() {
        // given
        Stack<String> stack = new Stack<>();

        // when
        stack.push("A");

        // then
        assertThat(stack.size()).isEqualTo(1);
        assertThat(stack.peek()).isEqualTo("A");
        assertThat(stack.size()).isEqualTo(1);
    }
}
