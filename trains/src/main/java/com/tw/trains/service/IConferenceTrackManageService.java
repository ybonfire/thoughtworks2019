package com.tw.trains.service;

import java.util.List;

import com.tw.trains.model.ConferenceTrack;
import com.tw.trains.model.Talk;

/**
 * @author: yuanbo
 * @description: ConferenceTrackManage接口。针对不同的需求编写具体的实现
 * 
 */
public interface IConferenceTrackManageService {
    List<ConferenceTrack> manageConferenceTrack(final List<Talk> allTalks);
}
