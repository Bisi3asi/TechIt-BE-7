package com.ll.domain.wise;

import com.ll.domain.wise.controller.WiseController;
import java.util.Scanner;

public class Application {
    WiseController wiseController = new WiseController(new Scanner(System.in));

    public void boot() throws Exception {
        wiseController.start();
    }

    public static void main(String[] args) throws Exception {
        Application application = new Application();
        application.boot();
    }
}
