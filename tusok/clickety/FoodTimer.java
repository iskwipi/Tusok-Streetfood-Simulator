package com.github.iskwipi.tusok.clickety;

import javafx.animation.AnimationTimer;

public abstract class FoodTimer extends AnimationTimer{
    private boolean running = false;
    private long startTime = 0;
    private long secondsElapsed = 0;

    public void setRunning(boolean running){
        this.running = running;
    }

    public boolean isRunning(){
        return running;
    }

    public void setStartTime(long startTime){
        this.startTime = startTime;
    }

    public long getStartTime(){
        return startTime;
    }

    public void setSecondsElapsed(long secondsElapsed){
        this.secondsElapsed = secondsElapsed;
    }

    public long getSecondsElapsed(){
        return secondsElapsed;
    }

    @Override
    public void stop(){
        super.stop();
        setRunning(false);
    }
}
