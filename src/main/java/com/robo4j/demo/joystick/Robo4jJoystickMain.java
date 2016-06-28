/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This Robo4jJoystickMain.java is part of Robo4j and robo4j-joystick
 *
 *     robo4j-joystick is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     robo4j-joystick is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Robo4j and robo4j-joystick .  If not, see <http://www.gnu.org/licenses/>.
 */

package com.robo4j.demo.joystick;

import com.robo4j.core.control.ControlPad;
import com.robo4j.demo.joystick.layout.Joystick;
import com.robo4j.demo.joystick.layout.events.enums.JoystickEventEnum;
import com.robo4j.demo.joystick.task.RoboAddressTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main Class of the Robo4j-joystick JavaFx demo
 *
 * initiation of:
 *  1. JavaFx Application
 *     joystick logic and related JavaFx features
 *
 *  2. Robo4j controlPad
 *     initation of core robot logic and resources
 *
 *
 * @author Choustoulakis Nikolaos (@eppnikos)
 * @author Miro Kopecky (@miragemiko)
 *
 * @since 25/06/16
 */

public class Robo4jJoystickMain extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Robo4jJoystickMain.class);
    private static final int ROTATION_ANGEL = 90;
    private static final String CONNECT = "Connect";
    private static final String CLOSE = "Close";
    private static final int LEVELS = 3;
    private ControlPad controlPad;
    private StringProperty ipLabelProperty;
    private Label connectLabel;
    private Button buttonConnect;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.controlPad = new ControlPad("com.robo4j.demo.joystick");
        this.ipLabelProperty = new SimpleStringProperty();

        BorderPane borderPane = new BorderPane();

        HBox hBox = getHBox();
        borderPane.setTop(hBox);

        Joystick joystickPane = new Joystick(ROTATION_ANGEL, LEVELS);
        joystickPane.addEventHandler(JoystickEventEnum.QUADRANT_CHANGED.getEventType(), e -> {
            switch (e.getQuadrant()){
                case NONE:
                    controlPad.sendCommandLine("A:stop");
                    break;
                case QUADRANT_I:
                    controlPad.sendCommandLine("A:right");
                    break;
                case QUADRANT_II:
                    controlPad.sendCommandLine("A:back");
                    break;
                case QUADRANT_III:
                    controlPad.sendCommandLine("A:left");
                    break;
                case QUADRANT_IV:
                    controlPad.sendCommandLine("A:move");
                    break;
                default:
                    throw new IllegalStateException("no such quadrant");
            }
        });
        joystickPane.addEventHandler(JoystickEventEnum.LEVEL_CHANGED.getEventType(),
                e -> logger.info("LEVEL" +  e.getJoystickLevel()));
        borderPane.setCenter(joystickPane);

        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setTitle("Robo4j-Joystick Canvas");
        primaryStage.show();

    }


    public static void main(String... args) {
        launch(args);
    }



    //Private Methdos

    private HBox getHBox(){
        HBox result = new HBox();
        result.setPadding(new Insets(15, 12, 15, 12));
        result.setSpacing(10); // Gap between nodes
        result.setStyle("-fx-background-color: #336699;");

        buttonConnect = new Button(CONNECT);
        buttonConnect.setPrefSize(100, 20);
        buttonConnect.setOnAction((event) -> handleConnectButtonOnAction());


        connectLabel = new Label();
        connectLabel.setPrefSize(100, 20);
        connectLabel.setText("IP ADDRESS");


        result.getChildren().addAll(buttonConnect, connectLabel);

        return result;
    }

    private void handleConnectButtonOnAction(){
        final boolean available = controlPad.getConnectionState();
        if(!controlPad.isActive() && available){
            controlPad.activate();
            connectLabel.textProperty().bind(ipLabelProperty);
            buttonConnect.setText(CLOSE);

            Platform.runLater(this::runAsyncLabelUpdate);
        } else if(controlPad.isActive()){
            controlPad.sendCommandLine("A:exit");
            buttonConnect.setText(CONNECT);


        } else {
            logger.info("NOT AVAILABLE BRICK");
        }


    }

    /**
     * submitting task to the internal Robo4j bus
     */
    private void runAsyncLabelUpdate(){
        final RoboAddressTask updateIpLabel = new RoboAddressTask(controlPad);
        ipLabelProperty.bind(updateIpLabel.messageProperty());
        controlPad.executeToSensorBus(updateIpLabel);

    }


}
