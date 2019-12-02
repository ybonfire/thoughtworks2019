package com.tw.trains.model;

import java.util.List;

import com.tw.trains.utils.TimeUtils;

/**
 * @author: yuanbo
 * @description: 会议日程对象
 * 
 */
public class ConferenceTrack {
    /**
     *  安排在上午的演讲
     */
    private List<Talk> talksInMorningSession;
    /**
     *  安排在下午的演讲
     */
    private List<Talk> talksInAfternoonSession;

    public ConferenceTrack(List<Talk> talksInMorningSession, List<Talk> talksInAfternoonSession) {
        super();
        this.talksInMorningSession = talksInMorningSession;
        this.talksInAfternoonSession = talksInAfternoonSession;
    }

    public List<Talk> getTalksInMorningSession() {
        return talksInMorningSession;
    }

    public void setTalksInMorningSession(List<Talk> talksInMorningSession) {
        this.talksInMorningSession = talksInMorningSession;
    }

    public List<Talk> getTalksInAfternoonSession() {
        return talksInAfternoonSession;
    }

    public void setTalksInAfternoonSession(List<Talk> talksInAfternoonSession) {
        this.talksInAfternoonSession = talksInAfternoonSession;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((talksInAfternoonSession == null) ? 0 : talksInAfternoonSession.hashCode());
        result = prime * result + ((talksInMorningSession == null) ? 0 : talksInMorningSession.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConferenceTrack other = (ConferenceTrack) obj;
        if (talksInAfternoonSession == null) {
            if (other.talksInAfternoonSession != null)
                return false;
        } else if (!talksInAfternoonSession.equals(other.talksInAfternoonSession))
            return false;
        if (talksInMorningSession == null) {
            if (other.talksInMorningSession != null)
                return false;
        } else if (!talksInMorningSession.equals(other.talksInMorningSession))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();

        // morning session begin at 9am
        int passedMinutesFromMidNight = 9 * 60;
        for (final Talk talk : talksInMorningSession) {
            sb.append(TimeUtils.format(passedMinutesFromMidNight)).append(" ").append(talk).append("\n");
            passedMinutesFromMidNight += talk.getTalkLengthInMinutes();
        }

        // lunch
        sb.append(TimeUtils.format(passedMinutesFromMidNight)).append(" ").append("Lunch").append("\n");

        // afternoon session begin at 1pm
        passedMinutesFromMidNight = 13 * 60;
        for (final Talk talk : talksInAfternoonSession) {
            sb.append(TimeUtils.format(passedMinutesFromMidNight)).append(" ").append(talk).append("\n");
            passedMinutesFromMidNight += talk.getTalkLengthInMinutes();
        }

        // network event
        sb.append(TimeUtils.format(passedMinutesFromMidNight)).append(" ").append("Networking Event").append("\n");

        return sb.toString();
    }
}
