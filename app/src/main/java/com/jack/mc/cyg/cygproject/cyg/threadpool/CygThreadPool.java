package com.jack.mc.cyg.cygproject.cyg.threadpool;


import com.jack.mc.cyg.cygproject.cyg.util.CygLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池
 */
public class CygThreadPool implements CygThreadPoolInterface {

    public CygThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveSeconds,
                         ThreadFactory threadFactory, boolean createCoreThreadImmediately) {
        mMaxWorkingThreadCount = maximumPoolSize;
        mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE,
                keepAliveSeconds, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), threadFactory);

        if (createCoreThreadImmediately) {
            for (int i = 0; i < corePoolSize; i++) {
                postTask(new CygTask() {
                    @Override
                    public void execute() {
                        // 空任务，作用是直接创建 corePoolSize 个线程
                    }
                }, CygTaskQueue.Priority.LOWEST);
            }
        }
    }

    public CygThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveSeconds) {
       this(corePoolSize, maximumPoolSize, keepAliveSeconds, Executors.defaultThreadFactory(), true);
    }

    public CygThreadPool(int corePoolSize, int maximumPoolSize) {
        this(corePoolSize, maximumPoolSize, 300);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final ThreadPoolExecutor mThreadPoolExecutor;
    private final CygTaskQueue mQueue = new CygTaskQueue();
    private final int mMaxWorkingThreadCount;
    private int mCurrentWorkingThreadCount = 0;

    private final Runnable mThreadPoolRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                CygTask task;
                Object sequenceToken;

                // 获取任务
                synchronized(CygThreadPool.this) {
                    task = mQueue.poll(mPollTaskSelector);
                    if (task == null) {
                        mCurrentWorkingThreadCount--;
                        break;
                    }
                    sequenceToken = task.getSequenceToken();
                    if (sequenceToken != null) {
                        mRunningSequenceTokenSet.add(sequenceToken);
                    }
                }

                // 执行任务
                try {
                    task.execute();
                } catch (Exception e) {
                    CygLog.error("task.run error", e);
                }

                // 执行完任务的后续处理
                synchronized(CygThreadPool.this) {
                    if (sequenceToken != null) {
                        mRunningSequenceTokenSet.remove(sequenceToken);
                    }
                }
            }
        }
    };

    // 保证相同SequenceToken的任务同时只有一个在执行
    private final Set<Object> mRunningSequenceTokenSet = new HashSet<>();

    private boolean isSameSequenceTokenTaskRunning(CygTask task) {
        // 这个函数不需要 synchronized
        Object sequenceToken = task.getSequenceToken();
        return sequenceToken != null && mRunningSequenceTokenSet.contains(sequenceToken);
    }

    private final CygTaskQueue.PollTaskSelector mPollTaskSelector = new CygTaskQueue.PollTaskSelector() {
        @Override
        public boolean isSelected(CygTask task) {
            return !isSameSequenceTokenTaskRunning(task);
        }
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public final synchronized void postTask(CygTask task, CygTaskQueue.Priority elementPriority) {
        if (task == null) {
            CygLog.error();
            return;
        }

        if (!mQueue.offer(task, elementPriority)) {
            CygLog.error("mQueue.offer error");
            return;
        }

        if (mCurrentWorkingThreadCount >= mMaxWorkingThreadCount) {
            // 线程太多了，不能再起线程了
            return;
        }

        if (isSameSequenceTokenTaskRunning(task)) {
            return;
        }

        try {
            mThreadPoolExecutor.submit(mThreadPoolRunnable);
            mCurrentWorkingThreadCount++;
        } catch (Exception e) {
            CygLog.warn("mThreadPoolExecutor.submit error", e);
        }
    }

    @Override
    public final void postTask(CygTask task) {
        postTask(task, CygTaskQueue.Priority.NORMAL);
    }

    @Override
    public final synchronized void removeTask(Object tag) {
        mQueue.remove(tag);
    }

    public final synchronized void shutdown() {
        mThreadPoolExecutor.shutdown();
    }

    public final synchronized List<Runnable> shutdownNow() {
        return mThreadPoolExecutor.shutdownNow();
    }
}
