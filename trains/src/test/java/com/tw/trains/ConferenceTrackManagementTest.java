package com.tw.trains;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.tw.trains.model.ConferenceTrack;
import com.tw.trains.model.Talk;
import com.tw.trains.service.ConferenceTrackManageServiceImpl;
import com.tw.trains.service.parser.TestCaseParser;
import com.tw.trains.utils.TestCaseReadUtils;

/**
 * @author: yuanbo
 * @description: ConferenceTrackManagement测试用例
 * 
 */
public class ConferenceTrackManagementTest {

    /**  
    * @Description: 测试是否有重复结果 
    * @param:
    * @return:   
    * @throws: 
    * @TODO:
    */
    @Test
    public void testConferenceTrackDuplicatedResult() throws IOException {
        List<String> testCases = TestCaseReadUtils.readAllTestCases("C:\\Users\\lenovo\\Desktop\\tw\\input.txt");
        List<Talk> talks = new TestCaseParser().parse(testCases);
        final List<ConferenceTrack> result = new ConferenceTrackManageServiceImpl().manageConferenceTrack(talks);


        assertEquals(result.size(), result.stream().distinct().count());
    }
}
