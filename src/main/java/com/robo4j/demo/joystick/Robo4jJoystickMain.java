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

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robo4j.core.logging.SimpleLoggingUtil;
import com.robo4j.demo.joystick.layout.Joystick;
import com.robo4j.demo.joystick.layout.events.enums.JoystickEventEnum;
import com.robo4j.demo.joystick.layout.events.enums.JoystickLevelEnum;
import com.robo4j.demo.joystick.task.RoboAddressTask;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Miro Kopecky (@miragemiko)
 *
 * @since 24.04.2016
 */
public class Robo4jJoystickMain extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Robo4jJoystickMain.class);
    private static final int ROTATION_ANGEL = 90;
    private static final String NOT_AVAILABLE = "NOT AVAILABLE";
    private static final String NOT_CONNECTED = "BRICK IS NOT CONNECTED";
    private static final String CONNECT = "Connect";
    private static final String CLOSE = "Close";

    private StringProperty ipLabelProperty;
    private Label connectLabel;
    private Button buttonConnect;
    private GridPane gridOptions;

    //Speed Properties
    private Map<Integer, TextField> speedFieldMap;
    private Map<Integer, String> speedBasicValues;
    private Map<Integer, StringProperty> speedPropertyMap;



    private int speed;
    private String quadrant;

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.ipLabelProperty = new SimpleStringProperty();
        /* default speed */
        this.speed = 100;
        this.quadrant = "";

        speedBasicValues = getSpeedBasic();

        GridPane borderPane = new GridPane();
        gridOptions = getOptions();


        borderPane.add(getHBox(), 0, 0);
        borderPane.add(gridOptions, 0, 1);
        borderPane.add(getJoystickPane(), 0, 2);

        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setTitle("Magic Scene");
        primaryStage.show();

    }

    //Private Methdos
    private void displayAlertDialog(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Robo4j ERROR");
        alert.setContentText(text);
        alert.showAndWait();
    }

    private BorderPane getJoystickPane(){
        BorderPane result = new BorderPane();
        result.setPadding(new Insets(15, 12, 15, 12));
        Joystick joystickPane = new Joystick(ROTATION_ANGEL, JoystickLevelEnum.values().length);
        joystickPane.addEventHandler(JoystickEventEnum.QUADRANT_CHANGED.getEventType(), e -> {

                switch (e.getQuadrant()){
                    case NONE:
                        quadrant = "stop";
                        break;
                    case QUADRANT_I:
                        quadrant = "left";
                        break;
                    case QUADRANT_II:
                        quadrant = "move";
                        break;
                    case QUADRANT_III:
                        quadrant = "right";
                        break;
                    case QUADRANT_IV:
                        quadrant = "back";
                        break;
                }
        });

        joystickPane.addEventHandler(JoystickEventEnum.LEVEL_CHANGED.getEventType(), e -> {
            System.out.println(getClass().getSimpleName() + " quadrant: " + quadrant + " speed: " + speed);
        });
        result.setCenter(joystickPane);
        return result;
    }

    private HBox getHBox(){
        HBox result = new HBox();
        result.setPadding(new Insets(15, 12, 2, 12));
        result.setSpacing(10); // Gap between nodes
        result.setStyle("-fx-background-color: #336699;");

        buttonConnect = new Button(CONNECT);
        buttonConnect.setPrefSize(100, 20);
        buttonConnect.setOnAction((event) -> handleConnectButtonOnAction());

        Button buttonOptions = new Button("Opt");
        buttonOptions.setPrefSize(60, 20);
        buttonOptions.setOnAction((event) -> handleOptionButtonOnAction());


        connectLabel = new Label();
        connectLabel.setPrefSize(150, 20);
        connectLabel.setText("IP ADDRESS");
        result.getChildren().addAll(buttonConnect, buttonOptions, connectLabel);

        return result;
    }

    private Map<Integer, String> getSpeedBasic(){
        final Map<Integer, String> result = new HashMap<>();
        result.put(1, "200");
        result.put(2, "500");
        result.put(3, "800");
        return result;
    }

    private GridPane getOptions(){
        GridPane result = new GridPane();
        result.setPadding(new Insets(2, 12, 10, 12));
        result.setStyle("-fx-background-color: #336699;");
        speedPropertyMap = new HashMap<>();
        speedPropertyMap.put(0, new SimpleStringProperty("100"));

        speedFieldMap = new HashMap<>();
        result.add(new Text("Speed by Levels(Degrees/sec):"), 0, 0);
        for(int i = 1; i < JoystickLevelEnum.values().length; i++){
            result.add( new Text("Lave"+ i +":"), 0, i);
            TextField speedField = new TextField();
            SimpleStringProperty simpleProperty = new SimpleStringProperty(speedBasicValues.get(i));
            speedField.setText(speedBasicValues.get(i));
            speedField.setPrefColumnCount(5);
            speedFieldMap.put(i, speedField);
            speedPropertyMap.put(i, simpleProperty);
            result.add(speedFieldMap.get(i), 1, i);
        }
        return result;
    }


    private void handleOptionButtonOnAction(){
        if(gridOptions.isVisible()) {
            gridOptions.setVisible(false);
            speedFieldMap.entrySet().stream()
                    .forEach(e -> {
                        speedBasicValues.replace(e.getKey(), e.getValue().getText());
                        speedPropertyMap.get(e.getKey()).setValue(e.getValue().getText());
                    });
        } else {
            gridOptions.setVisible(true);

        }
    }
    private void handleConnectButtonOnAction(){
        SimpleLoggingUtil.print(getClass(), "handle connect");

    }


    private void runAsyncLabelUpdate(){
        final RoboAddressTask updateIpLabel = new RoboAddressTask("magic happens");
        ipLabelProperty.bind(updateIpLabel.messageProperty());

    }


}
