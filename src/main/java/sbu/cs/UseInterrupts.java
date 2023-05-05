package sbu.cs;
public class UseInterrupts {
    public static class SleepThread extends Thread {
        int sleepCounter;
        int Sec=0;
        public SleepThread(int sleepCounter) {
            super();
            this.sleepCounter = sleepCounter;
        }
        @Override
        public void run() {
            System.out.println(this.getName() + " is Active.");

            while (this.sleepCounter > 0 && !isInterrupted())
            {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    System.out.println(this.getName() + " has been interrupted");
                }
                finally {
                    this.sleepCounter--;
                    System.out.println("Number of sleeps remaining: " + this.sleepCounter);
                    Sec++;
                    if(Sec>=3){
                        System.out.println(this.getName() + " has been interrupted");
                        this.interrupt();
                    }
                }
            }
        }
    }
    public static class LoopThread extends Thread {
        int value;
        public LoopThread(int value) {
            super();
            this.value = value;
        }
        @Override
        public void run() {
            System.out.println(this.getName() + " is Active.");
            long start=System.currentTimeMillis();
            for (int i = 0; i < 10; i += 3)
            {
                i -= this.value;
                if(System.currentTimeMillis()-start > 3000){
                    this.interrupt();
                    System.out.println(this.getName() + " has been interrupted");
                    return;
                }

            }
        }
    }
    public static void main(String[] args) {
        SleepThread sleepThread = new SleepThread(5);
        sleepThread.start();
        LoopThread loopThread = new LoopThread(3);
        loopThread.start();
    }
}
