package com.WiseApp;

/**
 * Main
 */
public class WiseApplication {
    WiseController wiseController = new WiseController();

    /**
     * 명언 앱을 최초 실행하는 메소드
     */
    void boot() {
        wiseController.launch();
    }

    public static void main(String[] args) {
        WiseApplication app = new WiseApplication();
        app.boot();
    }
}
