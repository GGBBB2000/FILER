import java.awt.Desktop;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
		ImageView[] iv = new ImageView[filesName.length];
		String[] filePath = new String[filesName.length];
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
				bt[i].setOnAction( (ActionEvent) -> {
					System.out.println(filesName[tmpNum].getName());
					try{
						Desktop.getDesktop().open(filesName[tmpNum]);
					}catch(Exception e){
					}
				});
			}else{
				System.out.println(filesName[i]);
				iv[i] = new ImageView("res//file.png");
				bt[i] = new Button(filesName[i].getName(),iv[i]);
				filePath[i] = filesName[i].getPath();
				final String tmp = filePath[i];
				final int num = filesName.length;
				int tmpNum = i;
				flow.getChildren().add(bt[i]);
				bt[i].setOnAction( (ActionEvent) -> {
					System.out.println(filesName[tmpNum].getName());
					for(int j = 0; j < num; j++){
						flow.getChildren().removeAll(bt[j]);
					}
					printContents(tmp);
				});
			}
		}
	}

	@FXML
	void onClick(ActionEvent e){
		flow.getChildren().removeAll(bt);
		printContents(formerPath);
	}
}
