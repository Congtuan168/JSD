package tut03.file_search_application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class file_search_application implements Runnable {
    private int startIndex;
    private int endIndex;
    private File[] listFiles;
    private List<String> fileList = new ArrayList<>();
    private TaskFinishListener listener;

    public file_search_application(int startIndex, int endIndex, File[] listFiles, TaskFinishListener listener) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.listFiles = listFiles;
        this.listener = listener;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            fileList.add(listFiles[i].getAbsolutePath());
        }
        listener.onFinish(fileList);
    }
}

