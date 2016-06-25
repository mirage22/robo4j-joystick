package com.robo4j.demo.joystick.layout;

import org.junit.Before;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AbstractApplicationFXTest extends Application {

    protected static final Joystick JOYSTICK = new Joystick(90, 3);

    @Before
    public void inittest() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new JFXPanel();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new AbstractApplicationFXTest().start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(10000); // Time to use the app, with out this, the thread
                             // will be killed before you can tell.
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(JOYSTICK));
        primaryStage.show();
    }

}
