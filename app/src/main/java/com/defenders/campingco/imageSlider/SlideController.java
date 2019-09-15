package com.defenders.campingco.imageSlider;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.ArrayList;



public class SlideController {


    ArrayList<Integer> timeIntervals = new ArrayList<>();
    long intervalOfTick = 10; // 10 millis
    int currentPlayingIndex = 0;
    private CountDownTimer countDownTimer;
    private long timeElapsed;
    private long progressPercent;

    public SlideController() {
    }

    public void setTimeIntervals(ArrayList<Integer> timeIntervals) {
        this.timeIntervals = timeIntervals;
    }

    public void setIntervalOfTick(long intervalOfTick) {
        this.intervalOfTick = intervalOfTick;
    }

    public void start() {
        if (timeIntervals.size() > 0 && intervalOfTick >= 10) {
            playFor(0);
        } else {
            throw new IllegalArgumentException("Invalid slide info. Provide tickInterval>=10 and at least one timeIntervals");
        }
    }

    public void stop() {
        if (countDownTimer != null) {
            sendStopEvent();
            countDownTimer.cancel();
        }
    }

    private void playFor(int index) {

        currentPlayingIndex = index;
        sendSlideNumber(index);
        countDownTimer = new CountDownTimer(timeIntervals.get(index), intervalOfTick) {
            @Override
            public void onTick(long millisUntilFinished) {
                sendProgressChanged(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                int nextIndex = getNextIndexToPlay(currentPlayingIndex);
                playFor(nextIndex);

            }
        }.start();

    }

    private void sendSlideNumber(int index) {
        if (slideEventListener != null) {
            slideEventListener.changeSlideTo(index);
        }
    }

    private void sendProgressChanged(long millisUntilFinished) {
        if (slideEventListener != null) {
            timeElapsed = timeIntervals.get(currentPlayingIndex) - millisUntilFinished;
            progressPercent = (1000 * timeElapsed) / timeIntervals.get(currentPlayingIndex);
            slideEventListener.progressChangedTo(millisUntilFinished, progressPercent);
        }
    }

    private void sendStopEvent() {
        if (slideEventListener != null) {
            slideEventListener.onSlideStopped();
        }
    }

    private int getNextIndexToPlay(int currentPlayingIndex) {
        if (currentPlayingIndex == (timeIntervals.size() - 1)) {
            return 0;
        }
        return currentPlayingIndex + 1;
    }

    private SlideEventListener slideEventListener;

    public void setSlideEventListener(SlideEventListener slideEventListener) {
        this.slideEventListener = slideEventListener;
    }


    public interface SlideEventListener {

        void changeSlideTo(int slideNumber);

        void progressChangedTo(long elapsedTime, long percent);

        void onSlideStopped();

    }

}
