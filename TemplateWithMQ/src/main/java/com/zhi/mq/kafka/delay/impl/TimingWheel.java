package com.zhi.mq.kafka.delay.impl;

import java.util.concurrent.TimeUnit;

/**
 * @name: TimingWheel
 * @description: 时间轮
 * @type: JAVA
 * @since: 2021/8/24 2:58 下午
 * @author: zhidanfeng
 *
 * question:
 * 1、链表使用双向链表有什么好处
 */
public class TimingWheel {

    private final TimingWheel.TimingWheelBucket[] wheel;

    private final Thread workerThread;
    private final TimingWheel.Worker worker;
    private int currentTick;

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {

    }

    public TimingWheel() {
        this.worker = new TimingWheel.Worker();
        // 初始化时间轮的一维数组
        this.wheel = createWheel(6);
        this.workerThread = new Thread(this.worker);
    }

    private TimingWheel.TimingWheelBucket[] createWheel(int ticksPerWheel) {
        if (ticksPerWheel < 0) {
            throw new IllegalArgumentException("ticksPerWheel must be greater than 0: " + ticksPerWheel);
        } else if (ticksPerWheel> MAXIMUM_CAPACITY) {
            throw new IllegalArgumentException("ticksPerWheel may not be greater than 2^30: " + ticksPerWheel);
        } else {
            // 设置时间轮一圈的刻度为2的次幂
            ticksPerWheel = tableSizeFor(ticksPerWheel);
            TimingWheel.TimingWheelBucket[] wheel = new TimingWheelBucket[ticksPerWheel];
            for (int i = 0; i < wheel.length; i++) {
                wheel[i] = new TimingWheel.TimingWheelBucket();
            }
            return wheel;
        }
    }

    private int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public void submit(TimerTask task, long delay, TimeUnit timeUnit) {
        TimingWheel.TimingWheelTimeout timeout = new TimingWheel.TimingWheelTimeout(this, task);
    }

    private static final class TimingWheelBucket {

        private TimingWheel.TimingWheelTimeout head;
        private TimingWheel.TimingWheelTimeout tail;

        private TimingWheelBucket() {

        }
    }

    private static final class TimingWheelTimeout {
        /**
         * 当前任务在时间轮的所处刻度
         */
        private TimingWheel.TimingWheelBucket bucket;
        /**
         * 剩余圈数
         */
        private long remainingRounds;

        private TimingWheelTimeout(TimingWheel timingWheel, TimerTask task) {

        }
    }

    private static final class Worker implements Runnable {

        @Override
        public void run() {

        }
    }
}
