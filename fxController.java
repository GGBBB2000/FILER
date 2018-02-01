import java.awt.Desktop;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class fxController {
	Button bt[];
	String formerPath;

	@FXML
	FlowPane flow;

	@FXML
	void initialize(){
		printContents("/Users");
	}

	void printContents(String path){
		File dir = new File(path);
		formerPath = dir.getAbsolutePath() + "//..";
		File filesName[] = dir.listFiles();
		bt = new Button[filesName.length];
		for(int i = 0; i < filesName.length; i++){
			final int tmpNum = i;
			bt[i] = new Button(filesName[i].getName());
			flow.getChildren().add(bt[i]);
			if(filesName[i].isFile()){
				bt[i].setGraphic(new ImageView("res//fileNone.png"));
				bt[i].setOnAction( (ActionEvent) -> {
					System.out.println(filesName[tmpNum].getName());
					try{
						Desktop.getDesktop().open(filesName[tmpNum]);
					}catch(Exception e){
					}
				});
			}else{
				final String filePath = filesName[i].getPath();
				System.out.println(filesName[i]);
				bt[i].setGraphic(new ImageView("res//file.png"));
				bt[i].setOnAction( (ActionEvent) -> {
					System.out.println(filesName[tmpNum].getName());
					changeDirectory(filePath);
				});
			}
		}
	}

	@FXML
	void onClick(ActionEvent e){
		changeDirectory(formerPath);
	}

	void changeDirectory(String s) {
		flow.getChildren().removeAll(bt);
		printContents(s);
	}
}
