import java.io.FileInputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
 
public class RaceTrack extends Application{
	Button startButton; 
	Button pauseButton;
	Button resetButton;
	boolean pauseClick = false;
	
	public static void main (String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Set up all the buttons rectangles (tracks), and cars in the correct positions
		primaryStage.setTitle("Richmond Raceway");
		
		startButton = new Button("Start");
		startButton.setTranslateX(-100);
		startButton.setTranslateY(-70);
		
		pauseButton = new Button("Pause");
		pauseButton.setTranslateX(0);
		pauseButton.setTranslateY(-70);
		
		resetButton = new Button("Reset");
		resetButton.setTranslateX(100);
		resetButton.setTranslateY(-70);

		Rectangle trackOne = new Rectangle(0, 0, 400, 15);
		trackOne.setTranslateY(-25);
		trackOne.setFill(Color.GRAY);

		Rectangle trackTwo = new Rectangle(0, 0, 400, 15);
		trackTwo.setTranslateY(15);
		trackTwo.setFill(Color.GRAY);
		
		Rectangle trackThree = new Rectangle(0, 0, 400, 15);
		trackThree.setTranslateY(55);
		trackThree.setFill(Color.GRAY);

		//FileInputStream carImageOne = new FileInputStream("C:\\Users\\ayala\\OneDrive\\Desktop\\car.png"); 
		Image carOne = new Image("https://i.imgur.com/BbUOeA9.png"); 
	    ImageView carOneView = new ImageView(carOne); 
	    carOneView.setTranslateX(-216);
	    carOneView.setTranslateY(-25);
	    
		//FileInputStream carImageTwo = new FileInputStream("C:\\Users\\ayala\\OneDrive\\Desktop\\car.png"); 
		Image carTwo = new Image("https://i.imgur.com/BbUOeA9.png"); 
	    ImageView carTwoView = new ImageView(carTwo); 
	    carTwoView.setTranslateX(-216);
	    carTwoView.setTranslateY(15);
	   
		//FileInputStream carImageThree = new FileInputStream("C:\\Users\\ayala\\OneDrive\\Desktop\\car.png"); 
		Image carThree = new Image("https://i.imgur.com/BbUOeA9.png"); 
	    ImageView carThreeView = new ImageView(carThree); 
	    carThreeView.setTranslateX(-216);
	    carThreeView.setTranslateY(55);
		
	    // Add all of the objects into the StackPane
		StackPane layout = new StackPane();
		layout.getChildren().add(startButton);
		layout.getChildren().add(pauseButton);
		layout.getChildren().add(resetButton);
		
		layout.getChildren().add(trackOne);
		layout.getChildren().add(trackTwo);
		layout.getChildren().add(trackThree);

		layout.getChildren().add(carOneView);
		layout.getChildren().add(carTwoView);
		layout.getChildren().add(carThreeView);
		
        Alert raceAlert = new Alert(AlertType.INFORMATION); 

		Thread moveCarOne = new Thread(new Runnable() {
			  @Override
	            public void run() {
	                Runnable updater = new Runnable() {
	                		@Override
	                		public void run() {
	                			
	                			// While the pause button is not pressed, move car one
	                			// a random amount of pixels forward. Once it reaches 184 pixels
	                			// (which is the end of the track), set the alert to car two winning and
	                			// stop the cars
	                			if (!pauseClick) {
	                				int value = (int) carOneView.getTranslateX();
	                				int randomVal = (int) (Math.random() * 10 - 1) + 1;
	                				int newValue = value + randomVal;
	                				carOneView.setTranslateX(newValue);
	                	    		if (carOneView.getTranslateX() >= 184) {
	                	    			raceAlert.setContentText("Car One Wins!");
	                	    			raceAlert.show();
	                					pauseClick = true;
	                	    		}
	                			}
	                		}
	                };

	                while (true) {
	                    try {
	                        Thread.sleep(50);
	                    } catch (InterruptedException ex) {}
	                    
	                    // Update the UI
	                    Platform.runLater(updater);
	                }
	            }
	        });
		
		Thread moveCarTwo = new Thread(new Runnable() {
			  @Override
	            public void run() {
	                Runnable updater = new Runnable() {
	                    @Override
	                    public void run() {
	                    	
	                    	// While the pause button is not pressed, move car two
                			// a random amount of pixels forward. Once it reaches 184 pixels
                			// (which is the end of the track), set the alert to car two winning and
                			// stop the cars
                			if (!pauseClick) {
                				int value = (int) carTwoView.getTranslateX();
                				int randomVal = (int) (Math.random() * 10 - 1) + 1;
                				int newValue = value + randomVal;
                				carTwoView.setTranslateX(newValue);
                				if (carTwoView.getTranslateX() >= 184) {
                					raceAlert.setContentText("Car Two Wins!");
                					raceAlert.show();
                					pauseClick = true;
	                	    	}
                			}
	                    }
	                };
	                while (true) {
	                    try {
	                        Thread.sleep(50);
	                    } catch (InterruptedException ex) {}

	                    // Update the UI
	                    Platform.runLater(updater);
	                }
	            }
	        });
		
		Thread moveCarThree = new Thread(new Runnable() {
			  @Override
	            public void run() {
	                Runnable updater = new Runnable() {
	                    @Override
	                    public void run() {
	                    	
	                    	// While the pause button is not pressed, move car three
                			// a random amount of pixels forward. Once it reaches 184 pixels
                			// (which is the end of the track), set the alert to car three winning and
                			// stop the cars
                			if (!pauseClick) {
                				int value = (int) carThreeView.getTranslateX();
                				int randomVal = (int) (Math.random() * 10 - 1) + 1;
                				int newValue = value + randomVal;
                				carThreeView.setTranslateX(newValue);
                				if (carThreeView.getTranslateX() >= 184) {
	                     	   		raceAlert.setContentText("Car Three Wins!");
	                     	   		raceAlert.show();
	            					pauseClick = true;
                				}
                			}
	                    }
	                };

	                while (true) {
	                    try {
	                        Thread.sleep(50);
	                    } catch (InterruptedException ex) {}

	                    // Update the UI
	                    Platform.runLater(updater);
	                }
	            }
	        });

		// Show all of the objects on the scene
		Scene scene = new Scene(layout, 500, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// if the start button is pressed, check if the threads are already alive.
		// If they are not, start all of them; if they are, set pauseClick to false
		// so the threads can continue running
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (!(moveCarOne.isAlive()) && !(moveCarTwo.isAlive()) && !(moveCarThree.isAlive())) {
					moveCarOne.start();
					moveCarTwo.start();
					moveCarThree.start();
				}
				else {
					pauseClick = false;
				}
			}
		});
		
		// if the reset button is pressed, put the cars back at -216 pixels, 
		// which is the start of the each track. Also set pauseClick to true
		// to halt the car's threads from running
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (moveCarOne.isAlive() && moveCarTwo.isAlive() && moveCarThree.isAlive()) {
					pauseClick = true;
				}
			    carOneView.setTranslateX(-216);
			    carTwoView.setTranslateX(-216);
			    carThreeView.setTranslateX(-216);

			}
		});
		
		// if the pause button is pressed, then set pauseClick to true so the 
		// threads stop running until start is pressed
		pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
					pauseClick = true;
			}
		});
	}
}
