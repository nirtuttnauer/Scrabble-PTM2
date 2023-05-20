package com.Seals.scrabble.Factory;

import com.Seals.scrabble.controller.LoginController;
import com.Seals.scrabble.controller.MenuController;
import com.Seals.scrabble.controller.iController;
import com.Seals.scrabble.factories.SceneFactory;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SceneFactoryTest {

    private SceneFactory sceneFactory;

//    @BeforeAll
//    public static void initToolkit() {
//        new JFXPanel(); // Initializes the JavaFX toolkit
//    }

    @BeforeEach
    void setUp() {
        sceneFactory = new SceneFactory();
    }

    @Test
    void setScene_WithValidViewName_LoadsSceneAndSetsController() {
        // Arrange
        Platform.runLater(() -> {
            String viewName = "LoginView";
            Stage stage = new Stage();

            sceneFactory.setScene(viewName);
            Scene scene = stage.getScene();

            // Assert
            assertNotNull(scene);

            FXMLLoader loader = (FXMLLoader) scene.getUserData();
            assertNotNull(loader);

            iController controller = loader.getController();
            assertNotNull(controller);
            assertEquals(LoginController.class, controller.getClass());
        });
    }

    @Test
    void getController_WithValidViewName_ReturnsAssociatedController() {
        // Arrange
        String viewName = "MenuView";
        MenuController expectedController = new MenuController();

        // Act
        sceneFactory.getControllers().put(viewName, expectedController);
        MenuController actualController = sceneFactory.getController(viewName);

        // Assert
        assertEquals(expectedController, actualController);
    }
}
