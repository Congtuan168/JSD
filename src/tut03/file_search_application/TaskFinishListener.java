package tut03.file_search_application;

import java.util.List;

interface TaskFinishListener {
    void onFinish(List<String> fileList);
}
