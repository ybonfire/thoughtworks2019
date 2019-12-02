package com.tw.trains.service.parser;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tw.trains.exception.TestCaseParseException;
import com.tw.trains.model.Talk;
import com.tw.trains.model.enums.TalkLengthUnitEnum;

public class TestCaseParser {
    /**  
    * @Description: 将文档中的测试用例解析成Talk对象集合 
    * @param: 输入的测试用例
    * @return: 解析结果
    * @TODO:
    */
    public List<Talk> parse(final List<String> testCases) {
        final Map<String, Talk> titleAndLengthMap = new HashMap<>();
        for (final String testCase : testCases) {
            final Talk talk = parseTestCase(testCase);
            if (titleAndLengthMap.containsKey(talk.getTalkTitle())) {
                throw new TestCaseParseException("have duplicated talkTitle {" + testCase + "}");
            } else {
                titleAndLengthMap.put(talk.getTalkTitle(), talk);
            }
        }

        return titleAndLengthMap.values().stream().collect(Collectors.toList());
    }

    /**  
    * @Description: 将每一列testCase解析成Talk对象
    * @param: 单行测试用例
    * @return: 解析结果 
    * @TODO:
    */
    private Talk parseTestCase(final String testCase) {
        final int lastSpaceIndex = testCase.trim().lastIndexOf(" ");
        if (lastSpaceIndex == -1) {
            throw new TestCaseParseException("failed to parse testCase {" + testCase + "}");
        }

        final String talkTitle = testCase.substring(0, lastSpaceIndex);
        final Map<TalkLengthUnitEnum, Integer> talkLengthMap = parseTalkLength(testCase.substring(lastSpaceIndex + 1));
        return new Talk(talkTitle, talkLengthMap.values().iterator().next(), talkLengthMap.keySet().iterator().next());
    }

    /**  
    * @Description: 解析testCase中的演讲时长，单位为min
    * @param: 演讲时长字符串
    * @return: 演讲时长
    * @TODO:
    */
    private Map<TalkLengthUnitEnum, Integer> parseTalkLength(final String talkLengthStr) {
        if (talkLengthStr.equals("lightning")) {
            // 演讲时长以lightning为单位
            return Collections.singletonMap(TalkLengthUnitEnum.LIGHTNING, 1);
        } else if (talkLengthStr.endsWith("min")) {
            // 演讲时长以min为单位
            try {
                return Collections.singletonMap(TalkLengthUnitEnum.MINUTES,
                        Integer.parseInt(talkLengthStr.substring(0, talkLengthStr.lastIndexOf("min"))));
            } catch (NumberFormatException e) {
                throw new TestCaseParseException(e.getMessage());
            }
        } else {
            throw new TestCaseParseException();
        }
    }
}
