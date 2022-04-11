package com.example;

import org.junit.Test;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QueueTest {
    @Test
    public void should_add_element_to_the_queue_and_retrieve_it() {
        // given
        Queue<String> queue = new LinkedBlockingQueue<>();

        // when
        queue.offer("A");
        String element = queue.poll();

        // then
        assertThat(element).isEqualTo("A");
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    public void should_add_element_to_the_queue_and_retrieve_it_but_not_delete() {
        // given
        Queue<String> queue = new LinkedBlockingQueue<>();

        // when
        queue.offer("A");
        queue.offer("B");
        queue.poll();
        String element = queue.peek();

        // then
        assertThat(element).isEqualTo("B");
        assertThat(queue.isEmpty()).isFalse();
    }
}
