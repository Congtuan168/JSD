package tut03.task_thread;


public class task_thread implements Runnable {
    private String characters;
    public task_thread (String characters) {
        this.characters = characters;
    }
    @Override
    public void run() {
        try {
                System.out.println("Task "+ Thread.currentThread().getName() + ": "+characters+ "      ");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
