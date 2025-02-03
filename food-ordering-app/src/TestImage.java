import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestImage extends Application {
    @Override
    public void start(Stage primaryStage) {
        ImageView imageView = new ImageView(new Image("file:/Users/askarkulahmetov/Downloads/FOOD/borsch.jpg"));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Test Image");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}