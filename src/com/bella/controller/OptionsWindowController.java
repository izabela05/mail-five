package com.bella.controller;

import com.bella.EmailManager;
import com.bella.view.ColorTheme;
import com.bella.view.FontSize;
import com.bella.view.ViewFactory;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXSlider;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.swing.text.View;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsWindowController extends BaseController implements Initializable {


        @FXML
        private JFXSlider fontSIzePicker;

        @FXML
        private ChoiceBox<ColorTheme> themePicker;

        @FXML
        private ImageView f1;

        @FXML
        private ImageView f2;

        public OptionsWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
            super(emailManager, viewFactory, fxmlName);
        }

        @FXML
        void applyButtonAction() {
                viewFactory.setColorTheme(themePicker.getValue());
                viewFactory.setFontSize(FontSize.values()[(int)(fontSIzePicker.getValue())]);
                viewFactory.updateStyles();

        }

        @FXML
        void cancelButtonAction() {
                Stage stage = (Stage) fontSIzePicker.getScene().getWindow();
                viewFactory.closeStage(stage);

        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                setUpThemePicker();
                setUpSizePicker();
        }

        private void setUpSizePicker() {
                fontSIzePicker.setMin(0);
                fontSIzePicker.setMax(FontSize.values().length - 1);
                fontSIzePicker.setValue(viewFactory.getFontSize().ordinal());
                fontSIzePicker.setMajorTickUnit(1);
                fontSIzePicker.setBlockIncrement(1);
                fontSIzePicker.setSnapToTicks(true);
                fontSIzePicker.setShowTickMarks(true);
                fontSIzePicker.setShowTickLabels(true);
                fontSIzePicker.setLabelFormatter(new StringConverter<Double>() {
                        @Override
                        public String toString(Double object) {
                                int i = object.intValue();
                                return FontSize.values()[i].toString();
                        }

                        @Override
                        public Double fromString(String s) {
                                return null;
                        }
                });
                fontSIzePicker.valueProperty().addListener((obs, oldVal, newVal) -> {
                        fontSIzePicker.setValue(newVal.intValue());
                });
        }

        private void setUpThemePicker() {
                themePicker.setItems(FXCollections.observableArrayList(ColorTheme.values()));
                themePicker.setValue(viewFactory.getColorTheme());
        }
}


