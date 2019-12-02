package com.tw.trains;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tw.trains.utils.TimeUtils;

/**
 * @author: yuanbo
 * @description: TimeUtils工具类测试用例
 * 
 */
public class TimeUtilsTest {
    /**  
     * @Description:  时间格式化工具正确用例  
     * @param:
     * @return:   
     * @throws: 
     * @TODO:
     */
     @Test
     public void testTimeUtils() {
         // 中午12点
         final int testCase1 = 12 * 60;
         final String testCaseResult1 = TimeUtils.format(testCase1);
         assertEquals(testCaseResult1, "12:00PM");

         // 凌晨12点
         final int testCase2 = 0;
         final String testCaseResult2 = TimeUtils.format(testCase2);
         assertEquals(testCaseResult2, "12:00AM");

         // 凌晨12点半
         final int testCase3 = 30;
         final String testCaseResult3 = TimeUtils.format(testCase3);
         assertEquals(testCaseResult3, "12:30AM");

         // 上午11点半
         final int testCase5 = 11 * 60 + 30;
         final String testCaseResult5 = TimeUtils.format(testCase5);
         assertEquals(testCaseResult5, "11:30AM");

         // 下午九点45
         final int testCase6 = 21 * 60 + 45;
         final String testCaseResult6 = TimeUtils.format(testCase6);
         assertEquals(testCaseResult6, "09:45PM");

         // 超过一天，上午10点半
         int n = 7;
         final int testCase7 = (n * 24 * 60) + 10 * 60 + 30;
         final String testCaseResult7 = TimeUtils.format(testCase7);
         assertEquals(testCaseResult7, "10:30AM");

         // 超过一天， 凌晨12点
         final int testCase8 = n * 24 * 60;
         final String testCaseResult8 = TimeUtils.format(testCase8);
         assertEquals(testCaseResult8, "12:00AM");

         // 超过一天， 中午12点
         final int testCase9 = n * 24 * 60 + 12 * 60;
         final String testCaseResult9 = TimeUtils.format(testCase9);
         assertEquals(testCaseResult9, "12:00PM");
     }

     /**  
     * @Description: 时间格式化工具异常用例  
     * @param:
     * @return:   
     * @throws: 
     * @TODO:
     */
     @Test(expected = IllegalArgumentException.class)
     public void testTimeUtilsException() {
         // passedMinutes小于0
         final int testCase8 = -1;
         final String testCaseResult8 = TimeUtils.format(testCase8);
     }
}
