package com.example;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ArrayTest {
    @Test
    public void adding_to_array_on_a_specific_index() {
        // given
        int[] input = new int[10];

        // when
        input[0] = 1;

        input[9] = 99;

        // then lookup is O(1) - don't need to iterate over
        assertThat(input[9]).isEqualTo(99);
    }

    @Test
    public void should_add_element_to_array_list() {
        // given
        List<String> list = new ArrayList<>();

        // when
        list.add("A");

        // then
        assertThat(list.size()).isEqualTo(1);

        // and when
        list.remove("A");
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void should_get_element_by_the_index() {
        // given
        List<String> list = new ArrayList<>();

        // when
        list.add("A");
        list.add("B");

        // then
        assertThat(list.get(0)).isEqualTo("A");
        assertThat(list.get(1)).isEqualTo("B");
    }
}
