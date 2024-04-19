package com.islacristina.aplicaciongestionincidencias;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import org.hibernate.annotations.Parent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AplicacionGestionIncidenciasApplication extends Application {
	public static ConfigurableApplicationContext applicationContext;
	public static Parent rootNode;
	public static Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		applicationContext = SpringApplication.run(AplicacionGestionIncidenciasApplication.class);
		FXMLLoader loader = new FXMLLoader(AplicacionGestionIncidenciasApplication.class.getResource("/login.fxml"));
		loader.setControllerFactory(applicationContext::getBean);
		Scene scene = new Scene(loader.load(), 800, 800, false, SceneAntialiasing.BALANCED);

		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
}
