import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.fxml.FXML;
import java.io.File;
import javafx.scene.paint.Color;
import javafx.application.HostServices;
import java.awt.Desktop;
public class fxController implements Initializable {
	ImageView iv[];
	Button bt[];
	String formerPath;
	String filePath[];
	Desktop dt = Desktop.getDesktop();
	Label alart = new Label("アクセスが禁じられています");
	int filesNum;

	@FXML
	FlowPane flow;
	Button backButton;
	Pane upperPane;

	@Override
	public void initialize(URL url,ResourceBundle rb){
		printContents("/Users");
	}
	
	public void printContents(String path){
		if(alart != null){
			flow.getChildren().remove(alart);
		}
		int count = 0;
		//formerPath = path + "//..";
		File dir = new File(path);
		formerPath = dir.getAbsolutePath() + "//..";
		File filesName[] = dir.listFiles();
		bt = new Button[filesName.length];
		iv = new ImageView[filesName.length];
		filePath = new String[filesName.length];
		filesNum = filesName.length;
		flow.setPadding(new Insets(5, 0, 5, 0));
		flow.setVgap(4);
		flow.setHgap(4);
		flow.setPrefWrapLength(680); // preferred width allows for two columns
		flow.setStyle("-fx-background-color: DAE6F3;");
		for(int i = 0; i < filesName.length; i++){
			if(filesName[i].isFile()){
				iv[i] = new ImageView("res//fileNone.png");
				bt[i] = new Button(filesName[i].getName(),iv[i]);
				filePath[i] = filesName[i].getPath();
				flow.getChildren().add(bt[i]);
				int tmpNum = i;
				bt[i].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						System.out.println(filesName[tmpNum].getName());
						try{
							dt.open(filesName[tmpNum]);
						}catch(Exception e){
						}
					}
				});
			}else{
				iv[i] = new ImageView("res//file.png");
				bt[i] = new Button(filesName[i].getName(),iv[i]);
				filePath[i] = filesName[i].getPath();
				final String tmp = filePath[i];
				final int num = filesName.length;
				int tmpNum = i;
				flow.getChildren().add(bt[i]);
				bt[i].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						System.out.println(filesName[tmpNum].getName());
						for(int j = 0; j < num; j++){
							flow.getChildren().removeAll(bt[j]);
						}
						printContents(tmp);
					}
				});
			}
		}
	}
	public void onClick(ActionEvent e){
		for(int j = 0; j < filesNum; j++){
			flow.getChildren().removeAll(bt[j]);
		}
		printContents(formerPath);
	}
}
