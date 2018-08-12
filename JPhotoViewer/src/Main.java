
/**
 * @author Barış MERAL
 * @since 2018.08.12
 * @version 1.0
 * @apiNote jdk10
 * @see java.lang.Runnable
 *
 */


public class Main  implements Runnable{

    public static void main(String[] args) {



        new Main().run();




    }

    @Override
    public void run() {
        new MainFrame().setVisible(true);
    }
}
