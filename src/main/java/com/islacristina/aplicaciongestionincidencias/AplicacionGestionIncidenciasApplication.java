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
		FXMLLoader mainLoader = new FXMLLoader(AplicacionGestionIncidenciasApplication.class.getResource("/main.fxml"));
		mainLoader.setControllerFactory(applicationContext::getBean);
		Parent root = mainLoader.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

		// Ahora que la ventana se ha mostrado, podemos maximizarla
		primaryStage.setMaximized(true);
	}
}
