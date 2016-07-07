package com.jack.mc.cyg.cygproject.cyg.threadpool;

/**
 *
 */
public abstract class CygTask {

    public abstract void execute();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public CygTask(Object sequenceToken, Object tag) {
        this.sequenceToken = sequenceToken;
        this.tag = tag;
    }

    public CygTask(Object sequenceTokenAndTag) {
        this(sequenceTokenAndTag, sequenceTokenAndTag);
    }

    public CygTask() {
        this(null);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final Object sequenceToken;
    private final Object tag;

    public Object getSequenceToken() {
        return sequenceToken;
    }

    public Object getTag() {
        return tag;
    }
}
