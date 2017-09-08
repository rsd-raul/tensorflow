package org.tensorflow.demo.model;


import android.graphics.RectF;

public class RecognizedSign {

    private float centerX;
    private float centerY;
    private float height;
    private float width;
    private String detected;
    private Float confidence;

    public RecognizedSign(RectF location) {
        centerX = location.centerX();
        centerY = location.centerY();
        height = location.height();
        width = location.width();
    }

    public void setRecognition(String detected, Float confidence) {
        this.detected = detected;
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

    public String getDetected() {
        return detected;
    }

    public Float getConfidence() {
        return confidence;
    }
}
