public class Timer implements Runnable{
    private int time;

    public Timer(int time){
        this.time = time;
    }


    public void run() {
        System.out.println(Thread.currentThread().getName() + " starting.");
        while (time>0) {
            try {
                Thread.sleep(1000); // millisecond
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            if (Thread.interrupted()) {
				System.out.println("Done");
				//return is used to end the method
				return;
			}

            time--;
        }
    }

    public int getTimer(){
        return time;
    }

    public void setTimer(int t){
        time = t;
    }
    
}