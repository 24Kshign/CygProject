package com.jack.mc.cyg.cygproject.cyg.threadpool;


import com.jack.mc.cyg.cygproject.cyg.util.CygLog;
import com.jack.mc.cyg.cygproject.cyg.util.CygObjectUtil;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * 有优先级的队列，分5个级别，向队列投递的时候可以指定优先级
 */
public class CygTaskQueue {

    private final List<CygTask> mTaskListPriorityLowest = new LinkedList<>();
    private final List<CygTask> mTaskListPriorityBelowNormal = new LinkedList<>();
    private final List<CygTask> mTaskListPriorityNormal = new LinkedList<>();
    private final List<CygTask> mTaskListPriorityAboveNormal = new LinkedList<>();
    private final List<CygTask> mTaskListPriorityHighest = new LinkedList<>();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public enum Priority {

        LOWEST(0),
        BELOW_NORMAL(1),
        NORMAL(2),
        ABOVE_NORMAL(3),
        HIGHEST(4);

        Priority(Integer value) {
            this.value = value;
        }

        private final Integer value;

        public Integer getValue() {
            return value;
        }

        public static Priority getEnum(Integer value) {
            for (Priority enumObject : values()) {
                if (CygObjectUtil.equals(enumObject.value, value)) {
                    return enumObject;
                }
            }
            CygLog.errorEx("无效的优先级，默认为NORMAL", CygLog.BELOW_CURRENT_STACK_LEVEL_1);
            return NORMAL;
        }
    }

    public final boolean offer(CygTask task, Priority priority) {
        if (task == null) {
            CygLog.error();
            return false;
        }

        if (priority == null) {
            priority = Priority.NORMAL;
        }

        switch (priority) {
            case LOWEST:
                return mTaskListPriorityLowest.add(task);
            case BELOW_NORMAL:
                return mTaskListPriorityBelowNormal.add(task);
            case NORMAL:
                return mTaskListPriorityNormal.add(task);
            case ABOVE_NORMAL:
                return mTaskListPriorityAboveNormal.add(task);
            case HIGHEST:
                return mTaskListPriorityHighest.add(task);
            default:
                return mTaskListPriorityAboveNormal.add(task);
        }
    }

    public interface PollTaskSelector {
        boolean isSelected(CygTask task);
    }

    public final CygTask poll(PollTaskSelector filter) {
        CygTask element = poll(mTaskListPriorityHighest, filter);
        if (element != null) {
            return element;
        }
        element = poll(mTaskListPriorityAboveNormal, filter);
        if (element != null) {
            return element;
        }
        element = poll(mTaskListPriorityNormal, filter);
        if (element != null) {
            return element;
        }
        element = poll(mTaskListPriorityBelowNormal, filter);
        if (element != null) {
            return element;
        }
        element = poll(mTaskListPriorityLowest, filter);
        if (element != null) {
            return element;
        }
        return null;
    }

    public final void remove(Object tag) {
        remove(mTaskListPriorityHighest, tag);
        remove(mTaskListPriorityAboveNormal, tag);
        remove(mTaskListPriorityNormal, tag);
        remove(mTaskListPriorityBelowNormal, tag);
        remove(mTaskListPriorityLowest, tag);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static CygTask poll(List<CygTask> taskList, PollTaskSelector filter) {
        for (int i = 0; i < taskList.size(); i++) {
            CygTask task = taskList.get(i);
            if (filter == null || filter.isSelected(task)) {
                taskList.remove(task);
                return task;
            }
        }
        return null;
    }

    private static void remove(List<CygTask> taskList, Object tag) {
        if (tag == null) {
            CygLog.error();
            return;
        }

        Iterator<CygTask> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            CygTask task = iterator.next();
            Object taskTag = task.getTag();
            if (taskTag != null) {
                if (taskTag.equals(tag)) {
                    iterator.remove();
                }
            }
        }
    }
}
