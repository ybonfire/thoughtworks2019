package com.tw.trains.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author: yuanbo
 * @description:  测试用例读取工具类
 * 
 */
public final class TestCaseReadUtils {
    private TestCaseReadUtils() {}

    /**  
    * @Description: 读取文档中的测试用例  
    * @param: 文档路径
    * @return:
    * @throws: 
    * @TODO:
    */
    public static List<String> readAllTestCases(final String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }
}
