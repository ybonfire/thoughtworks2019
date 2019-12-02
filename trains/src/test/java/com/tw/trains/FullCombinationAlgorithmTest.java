package com.tw.trains;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * @author: yuanbo
 * @description: 全组合算法测试用例
 * 
 */
public class FullCombinationAlgorithmTest {

    /**  
    * @Description: 测试全组合算法  
    * @param:
    * @return:   
    * @throws: 
    * @TODO:
    */
    @Test
    public void testFullCombinationAlgorithm() {
        final List<List<Character>> result = findCombinations("case");
        result.forEach(combination -> {
            combination.forEach(System.out::print);
            System.out.println();
        });
    }

    private static List<List<Character>> findCombinations(String test) {
        final List<List<Character>> result = new ArrayList<>();

        final char[] chars = test.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            final List<List<Character>> newCombinations =
                    Stream.of(Stream.of(chars[i]).collect(Collectors.toList())).collect(Collectors.toList());
            for (final List<Character> combination : result) {
                final List<Character> newCombination = combination.stream().collect(Collectors.toList());
                newCombination.add(chars[i]);
                newCombinations.add(newCombination);
            }
            result.addAll(newCombinations);
        }

        return result;
    }
}
