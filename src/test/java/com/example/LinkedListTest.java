package com.example;

import com.example.list.CustomList;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {
    @Test
    public void should_add_element_to_custom_list() {
        // given
        CustomList<String> customList = new CustomList<>();

        // when
        customList.add("A");

        // then
        assertThat(customList.size()).isEqualTo(1);

        // and when
        customList.add("B");
        customList.add("C");
        customList.add("D");
        assertThat(customList.get(2)).isEqualTo("C");
    }

    @Test
    public void should_retrieve_head_and_tail_of_the_custom_list() {
        // given
        CustomList<String> customList = new CustomList<>();

        // when
        customList.add("A");
        customList.add("B");

        // then
        assertThat(customList.peekFirst()).isEqualTo("A");
    }

    @Test
    public void should_add_element_to_list() {
        // given
        List<String> list = new LinkedList<>();

        // when
        list.add("A");

        // then
        assertThat(list.size()).isEqualTo(1);

        // and when
        list.remove("A");
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void should_retrieve_head_and_tail_of_the_list() {
        // given
        LinkedList<String> list = new LinkedList<>();

        // when
        list.add("A");
        list.add("B");

        // then
        assertThat(list.peekFirst()).isEqualTo("A");
        assertThat(list.peekLast()).isEqualTo("B");
    }
}
