import main.GameWindow;

public class Program {
    public static void main(String[] args) {
        System.out.println("Hello world");
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();
    }
}
