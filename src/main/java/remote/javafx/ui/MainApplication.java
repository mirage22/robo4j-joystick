package remote.javafx.ui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import remote.javafx.ui.controller.CanvasRadarController;
import remote.javafx.ui.events.POVEvent;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        CanvasRadarController radar = new CanvasRadarController(90, 5);

        radar.getView().addEventHandler(POVEvent.POV_CHANGE_QUADRANT, e -> System.out.println(e.getQuadrant()));
        radar.getView().addEventHandler(POVEvent.POV_LEVEL_CHANGED, e -> System.out.println(e.getLevel()));

        root.getChildren().addAll(radar.getView());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String... args) {
        launch(args);
    }



}
