package com.tw.trains;

import java.io.IOException;
import java.util.List;

import com.tw.trains.model.ConferenceTrack;
import com.tw.trains.model.Talk;
import com.tw.trains.service.ConferenceTrackManageServiceImpl;
import com.tw.trains.service.parser.TestCaseParser;
import com.tw.trains.utils.TestCaseReadUtils;

public class ConferenceTrackManagementApplication {

    public static void main(String[] args) throws IOException {
        final List<String> testCases = TestCaseReadUtils.readAllTestCases("src/main/resources/testCase.txt");
        final List<Talk> talks = new TestCaseParser().parse(testCases);
        final List<ConferenceTrack> conferenceTracks =
                new ConferenceTrackManageServiceImpl().manageConferenceTrack(talks);
    }
}
