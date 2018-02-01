import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class fx extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception{
		stage = primaryStage;
		primaryStage.setTitle("Filer");
		Scene myScene = new Scene(FXMLLoader.load(getClass().getResource("fxController.fxml")));
		primaryStage.setScene(myScene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
