public class Worker extends Thread {
    private final CustomBarrier barrier;
    private final int taskDuration;
    private final String taskName;

    Worker(String name, CustomBarrier barrier, int taskDuration, String taskName) {
        super(name);
        this.barrier = barrier;
        this.taskDuration = taskDuration;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            executeTask(1);
            barrier.await();

            executeTask(2);
            barrier.await();

            executeTask(3);
            barrier.await();

            System.out.println(getName() + "completed all phases!");
        } catch (InterruptedException e ) {
            System.out.println(getName() + "interrupted");
            Thread.currentThread().interrupt();
        }
    }
    private void executeTask(int phase) throws InterruptedException{
        System.out.println(getName()+ "starting phase "+ phase + "("+taskName+")");
        Thread.sleep(taskDuration);
        System.out.println(getName()+ "completed phase "+ phase );
    }
}

