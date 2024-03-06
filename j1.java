import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DFASimulation extends Application {
    private final DFA dfa = new DFA();
    private TextField inputField;
    private Label outputLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DFA Simulation");

        Label inputLabel = new Label("Input:");
        inputField = new TextField();
        Button processButton = new Button("Process Input");
        processButton.setOnAction(e -> processInput());
        outputLabel = new Label();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(inputLabel, inputField, processButton, outputLabel);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void processInput() {
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            outputLabel.setText("Enter the string!");
            return;
        }
        String result = dfa.processInput(input);
        outputLabel.setText(result);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class DFA {
    private final String[] states = {"q0", "q1", "q2", "q3"};
    private final String[][] transitions = {
            {"q1", "q2"},
            {"q0", "q3"},
            {"q3", "q2"},
            {"q3", "q3"}
    };
    private final String initialState = "q0";
    private final String acceptingState = "q2";

    public String processInput(String input) {
        String currentState = initialState;
        for (char symbol : input.toCharArray()) {
            if (symbol != 'a' && symbol != 'b') {
                return "Wrong Language!";
            }
            currentState = transitions[getIndex(currentState)][getIndex(symbol)];
        }
        return currentState.equals(acceptingState) ? "Your input was accepted by the DFA" : "Your input was rejected by the DFA";
    }

    private int getIndex(char symbol) {
        return symbol == 'a' ? 0 : 1;
    }

    private int getIndex(String state) {
        for (int i = 0; i < states.length; i++) {
            if (states[i].equals(state)) {
                return i;
            }
        }
        return -1;
    }
}
