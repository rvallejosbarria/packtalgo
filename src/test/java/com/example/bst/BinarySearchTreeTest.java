package com.example.bst;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BinarySearchTreeTest {
    @Test
    public void test_binary_search_tree() {
        // given
        BinarySearchTree b = new BinarySearchTree();

        // when
        b.insert(3);
        b.insert(8);
        b.insert(1);
        b.insert(4);
        b.insert(6);
        b.insert(2);
        b.insert(10);
        b.insert(9);
        b.insert(20);
        b.insert(25);
        b.insert(15);
        b.insert(16);

        // then
        List<Integer> results = b.getInOrder();

        List<Integer> expected = new LinkedList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(6);
        expected.add(8);
        expected.add(9);
        expected.add(10);
        expected.add(15);
        expected.add(16);
        expected.add(20);
        expected.add(25);

        assertThat(results).isEqualTo(expected);
        assertThat(b.find(4)).isTrue();
        assertThat(b.find(100)).isFalse();
    }

    @Test
    public void test_binary_search_tree_deletion_simple() {
        // given
        BinarySearchTree b = new BinarySearchTree();

        // when
        b.insert(3);
        b.insert(8);
        b.insert(1);

        // then
        b.delete(8);
        List<Integer> results = b.getInOrder();

        List<Integer> expected = new LinkedList<>();
        expected.add(1);
        expected.add(3);

        assertThat(results).isEqualTo(expected);
    }

    @Test
    public void test_binary_search_tree_removing_complex() {
        // given
        BinarySearchTree b = new BinarySearchTree();

        // when
        b.insert(3);
        b.insert(8);
        b.insert(1);
        b.insert(4);
        b.insert(6);
        b.insert(2);
        b.insert(10);
        b.insert(9);
        b.insert(20);
        b.insert(25);
        b.insert(15);
        b.insert(16);

        // then
        b.delete(10);
        List<Integer> results = b.getInOrder();

        List<Integer> expected = new LinkedList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(6);
        expected.add(8);
        expected.add(9);
        expected.add(15);
        expected.add(16);
        expected.add(20);
        expected.add(25);

        assertThat(results).isEqualTo(expected);
        assertThat(b.find(4)).isTrue();
        assertThat(b.find(100)).isFalse();
    }
}
