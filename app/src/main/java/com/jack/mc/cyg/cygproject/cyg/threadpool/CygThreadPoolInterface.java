package com.jack.mc.cyg.cygproject.cyg.threadpool;

/**
 *
 */
public interface CygThreadPoolInterface {
    void postTask(CygTask task, CygTaskQueue.Priority priority);
    void postTask(CygTask task);
    void removeTask(Object tag);
}
