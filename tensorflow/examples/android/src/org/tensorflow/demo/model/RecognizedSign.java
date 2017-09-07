package org.tensorflow.demo.model;


import android.graphics.RectF;

public class RecognizedSign {

    private float centerX;
    private float centerY;
    private float height;
    private float width;
    private String index;
    private Float confidence;
    private long timestamp;

    public RecognizedSign(RectF location) {
        centerX = location.centerX();
        centerY = location.centerY();
        height = location.height();
        width = location.width();
    }

    public void setRecognition(String index, Float confidence) {
        this.index = index;
        this.confidence = confidence;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public String getIndex() {
        return index;
    }

    public Float getConfidence() {
        return confidence;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
