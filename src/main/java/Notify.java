public class Notify {

    private final Object mon = new Object();
    private char letter = 'a';

    public void printA(){
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'a'){
                        mon.wait();
                    }
                    System.out.print(letter);
                    letter = 'b';
                    mon.notifyAll();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void printB(){
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'b'){
                        mon.wait();
                    }
                    System.out.print(letter);
                    letter = 'c';
                    mon.notifyAll();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void printC(){
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'c'){
                        mon.wait();
                    }
                    System.out.print(letter);
                    letter = 'a';
                    mon.notifyAll();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Notify notify = new Notify();

        new Thread(new Runnable() {
            @Override
            public void run() {
                notify.printA();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                notify.printB();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                notify.printC();
            }
        }).start();

    }
}
