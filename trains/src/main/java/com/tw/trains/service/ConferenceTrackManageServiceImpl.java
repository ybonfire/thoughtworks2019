package com.tw.trains.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tw.trains.model.ConferenceTrack;
import com.tw.trains.model.Talk;

/**
 * @author: yuanbo
 * @description: 实现题目中的ConferenceTrack需求
 * 
 */
public class ConferenceTrackManageServiceImpl implements IConferenceTrackManageService {
    private static final int MORNING_SESSION_START_TIME = 9 * 60;
    private static final int MORNING_SESSION_END_TIME = 12 * 60;
    private static final int AFTERNOON_SESSION_START_TIME = 13 * 60;
    private static final int NETWORK_EVENT_EARLIER_END_TIME = 16 * 60;
    private static final int NETWORK_EVENT_LATER_END_TIME = 17 * 60;

    /**  
    * 管理会议日程，具体思路：
    * 1. 找出所有能安排在上午的演讲组合
    * 2. 遍历每个能安排在上午的组合，找出剩余演讲中所有能安排在下午的演讲组合 
    * 3. 构造会议日程
    * 
    * @Description:  安排会议日程 
    * @param:  演讲集合
    * @return: 会议日程  
    * @throws: 
    * @TODO:
    */
    @Override
    public List<ConferenceTrack> manageConferenceTrack(final List<Talk> allTalks) {
        final List<ConferenceTrack> conferenceTracks = new ArrayList<>();

        /** 找出所有能安排在上午的演讲的组合 **/
        final List<List<Talk>> talkCombinationsInMorningSession = findTalkCombinationsInMorningSession(allTalks);
        for (final List<Talk> talksInMorningSession : talkCombinationsInMorningSession) {
            /** 根据每一个安排在上午的演讲组合找出所有能安排在下午的演讲 **/
            final List<Talk> talksInAfternoonSession = allTalks.stream()
                    .filter(talk -> !talksInMorningSession.contains(talk)).collect(Collectors.toList());
            final List<List<Talk>> talkCombinationsInAfternoonSession =
                    findTalkCombinationsInAfternoonSession(talksInAfternoonSession);

            talkCombinationsInAfternoonSession.stream().forEach(talkCombinationInAfternoonSession -> {
                if (!talksInMorningSession.isEmpty() && !talkCombinationInAfternoonSession.isEmpty()) {
                    conferenceTracks.add(new ConferenceTrack(talksInMorningSession, talkCombinationInAfternoonSession));
                    System.out.println(conferenceTracks.get(conferenceTracks.size() - 1));
                }
            });
        }

        return conferenceTracks;
    }

    /**  
    * @Description: 找出所有能安排在上午的演讲组合    
    * @param: 演讲集合
    * @return: 所有能安排在上午的演讲组合
    * @throws: 
    * @TODO:
    */
    private List<List<Talk>> findTalkCombinationsInMorningSession(final List<Talk> talks) {
        /** 所有能在上午做演讲的talk组合 **/
        return findTalkCombinations(talks, MORNING_SESSION_END_TIME - MORNING_SESSION_START_TIME).parallelStream()
                // begin at 9am and must finish before 12 noon.
                .filter(this::isTalksInMorningLengthReasonable)
                // Each session contains multiple talks.
                .filter(this::hasMultipleTalksInSession).collect(Collectors.toList());
    }

    /**  
    * @Description: 找出所有能安排在下午的演讲组合    
    * @param: 演讲集合
    * @return: 所有能安排在下午的演讲集合
    * @throws: 
    * @TODO:
    */
    private List<List<Talk>> findTalkCombinationsInAfternoonSession(final List<Talk> talks) {
        /** 所有能在下午做演讲的talk组合 **/
        return findTalkCombinations(talks, NETWORK_EVENT_LATER_END_TIME - AFTERNOON_SESSION_START_TIME).parallelStream()
                // begin at 1pm and must finish no earlier than 4:00 and no later than 5:00. **/
                .filter(this::isTalksInAfternoonLengthReasonable)
                // Each session contains multiple talks.
                .filter(this::hasMultipleTalksInSession).collect(Collectors.toList());
    }

    /**  
    * @Description: 判断安排在上午的演讲时长是否合理 (安排在上午的演讲刚好能在12：00PM结束)
    * @param: 安排在上午的演讲集合
    * @return:   
    * @throws: 
    * @TODO:
    */
    private boolean isTalksInMorningLengthReasonable(final List<Talk> talksInMorningSession) {
        return MORNING_SESSION_START_TIME + calculateTalkLength(talksInMorningSession) == MORNING_SESSION_END_TIME;
    }

    /**  
    * @Description: 判断安排在下午的演讲时长是否合理 (安排在下午的演讲不会早于04:00PM结束)
    * @param: 安排在下午的演讲集合
    * @return:   
    * @throws: 
    * @TODO:
    */
    private boolean isTalksInAfternoonLengthReasonable(final List<Talk> talksInAfternoonSession) {
        return AFTERNOON_SESSION_START_TIME
                + calculateTalkLength(talksInAfternoonSession) >= NETWORK_EVENT_EARLIER_END_TIME;
    }

    /**  
    * @Description: 判断Session中是否包含多个talk   
    * @param:
    * @return:   
    * @throws: 
    * @TODO:
    */
    private boolean hasMultipleTalksInSession(final List<Talk> talks) {
        return talks.size() > 1;
    }

    /**  
    * @Description: 计算talks总共的演讲时长，单位分钟
    * @param:
    * @return:   
    * @throws: 
    * @TODO:
    */
    private int calculateTalkLength(final List<Talk> talks) {
        return talks.parallelStream().map(Talk::getTalkLengthInMinutes).reduce(0, Integer::sum);
    }

    /**  
    * 全组合算法：遍历数组，当前元素单独生成一个组合，然后将之前所有的组合都加入当前元素，生成新的组合，最终结果就是数组的全组合
    *   
    * @Description:  全组合算法，计算符合演讲时间的演讲组合。
    * @param1: talks 用于计算组合的演讲集合
    * @param2: maxTalksLength 能分配的最大演讲时间
    * @return:   
    * @throws: 
    * @TODO:
    */
    private List<List<Talk>> findTalkCombinations(final List<Talk> talks, final int maxTalksLength) {
        final List<List<Talk>> combinations = new ArrayList<>();

        for (final Talk talk : talks) {
            /** 如果单个演讲时间已经大于能分配的最大时间则不考虑将其加入结果 **/
            if (talk.getTalkLengthInMinutes() > maxTalksLength) {
                continue;
            }

            /** 当前元素单独生成一个组合  **/
            final List<List<Talk>> newCombinations =
                    Stream.of(Stream.of(talk).collect(Collectors.toList())).collect(Collectors.toList());

            /** 将之前所有的组合都加入当前元素，生成新的组合 **/
            for (final List<Talk> combination : combinations) {
                /** 如果之前的演讲加上当前演讲的总时间大于最大时间则不考虑将其加入结果 **/
                final int talksLength = calculateTalkLength(combination);
                if (talksLength + talk.getTalkLengthInMinutes() > maxTalksLength) {
                    continue;
                }

                final List<Talk> newCombination = combination.stream().collect(Collectors.toList());
                newCombination.add(talk);
                newCombinations.add(newCombination);
            }

            combinations.addAll(newCombinations);
        }

        return combinations;
    }
}
