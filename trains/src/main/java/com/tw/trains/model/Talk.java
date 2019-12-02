package com.tw.trains.model;

import com.tw.trains.model.enums.TalkLengthUnitEnum;

/**
 * @author: yuanbo
 * @description: 演讲对象 
 * 
 */
public class Talk {
    /**
     *  演讲主题
     */
    private String talkTitle;
    /**
     *  演讲时长
     */
    private int talkLength;
    /**
     *  演讲时长单位
     */
    private TalkLengthUnitEnum talkLengthUnit;

    public Talk(String talkTitle, int talkLength, TalkLengthUnitEnum talkLengthUnit) {
        super();
        this.talkTitle = talkTitle;
        this.talkLength = talkLength;
        this.talkLengthUnit = talkLengthUnit;
    }

    public String getTalkTitle() {
        return talkTitle;
    }

    public void setTalkTitle(String talkTitle) {
        this.talkTitle = talkTitle;
    }

    public int getTalkLength() {
        return talkLength;
    }

    public void setTalkLength(int talkLength) {
        this.talkLength = talkLength;
    }

    public TalkLengthUnitEnum getTalkLengthUnit() {
        return talkLengthUnit;
    }

    public void setTalkLengthUnit(TalkLengthUnitEnum talkLengthUnit) {
        this.talkLengthUnit = talkLengthUnit;
    }

    /**  
    * @Description: 获取以分钟为单位的演讲时长   
    * @param:
    * @return:   
    * @throws: 
    * @TODO:
    */
    public int getTalkLengthInMinutes() {
        return talkLength * TalkLengthUnitEnum.converToMinuts(talkLengthUnit);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + talkLength;
        result = prime * result + ((talkLengthUnit == null) ? 0 : talkLengthUnit.hashCode());
        result = prime * result + ((talkTitle == null) ? 0 : talkTitle.hashCode());
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
        Talk other = (Talk) obj;
        if (talkLength != other.talkLength)
            return false;
        if (talkLengthUnit != other.talkLengthUnit)
            return false;
        if (talkTitle == null) {
            if (other.talkTitle != null)
                return false;
        } else if (!talkTitle.equals(other.talkTitle))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return new StringBuffer(talkTitle).append(" ").append(talkLengthUnit == TalkLengthUnitEnum.LIGHTNING
                ? talkLengthUnit.getValue() : String.valueOf(talkLength) + talkLengthUnit.getValue()).toString();
    }
}
