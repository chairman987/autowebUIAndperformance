package MulHttpTask;

import Model.Task;

import java.util.Hashtable;

/**
 * 1.多步骤任务
 */
public interface IMutHttpTaskTest {
    public void excuteTask(Task task);

    Hashtable<String, String> getReturnSet();
}
