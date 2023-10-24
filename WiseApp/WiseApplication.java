package WiseApp;

/**
 * Main
 */
public class WiseApplication {
    WiseController wiseController = new WiseController();

    void boot() {
        System.out.println(" == 명언 앱 ==");
        wiseController.launch();
    }

    public static void main(String[] args) {
        WiseApplication app = new WiseApplication();
        app.boot();
    }
}
