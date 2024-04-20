package com.islacristina.aplicaciongestionincidencias;

import com.islacristina.aplicaciongestionincidencias.controllers.DashboardController;
import com.islacristina.aplicaciongestionincidencias.controllers.LoginController;
import com.islacristina.aplicaciongestionincidencias.controllers.MainController;
import com.islacristina.aplicaciongestionincidencias.repositories.ProcedenciaRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
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
		Parent root = loader.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();

		LoginController loginController = loader.getController();
		loginController.setPrimaryStage(primaryStage);
	}
}
