package uet.oop.stages;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SwitchButton extends StackPane {
	private final Rectangle back = new Rectangle(60, 20, Color.RED);
	private final Button button = new Button();

	private String buttonStyleOff = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE;";

	private String buttonStyleOn = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: #0c3b6e;";

	private boolean state;

	public boolean isState() {
		return state;
	}

	private void init() {
		state = true;

		getChildren().addAll(back, button);

		setMinSize(60, 30);

		back.maxWidth(60);
		back.minWidth(60);

		back.maxHeight(20);
		back.minHeight(20);

		back.setArcHeight(back.getHeight());
		back.setArcWidth(back.getHeight());
					back.setFill(Color.valueOf("#6d92b3"));

		Double r = 3.0;
		button.setShape(new Circle(r));

		setAlignment(button, Pos.CENTER_RIGHT);
		button.setMaxSize(30, 30);
		button.setMinSize(30, 30);
		button.setStyle(buttonStyleOn);
	}

	public SwitchButton() {
		init();
		EventHandler<Event> click = new EventHandler<Event>() {

			@Override
			public void handle(Event e) {
				if (state) {
					button.setStyle(buttonStyleOff);
					back.setFill(Color.valueOf("#ced5da"));
					setAlignment(button, Pos.CENTER_LEFT);
					state = false;
				} else {
					button.setStyle(buttonStyleOn);
					back.setFill(Color.valueOf("#6d92b3"));
					setAlignment(button, Pos.CENTER_RIGHT);
					state = true;
				}
			}
		};

		button.setFocusTraversable(false);

		setOnMouseClicked(click);
		button.setOnMouseClicked(click);

	}
}