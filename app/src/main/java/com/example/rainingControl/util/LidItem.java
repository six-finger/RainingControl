package com.example.rainingControl.util;

public class LidItem {
    private String lidType, lidName;
    private float area, depth, ratio, volume;

    public LidItem(String lidType, String lidName, float area, float depth, float ratio, float volume){
        this.lidType = lidType;
        this.lidName = lidName;
        this.area = area;
        this.depth = depth;
        this.ratio = ratio;
        this.volume = volume;
    }

    public String getLidType() {
        return lidType;
    }

    public String getLidName() {
        return lidName;
    }

    public float getArea() {
        return area;
    }

    public float getDepth() {
        return depth;
    }

    public float getRatio() {
        return ratio;
    }

    public float getVolume() {
        return volume;
    }


}
