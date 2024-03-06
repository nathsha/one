import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DFSMSimulationGUI extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton simulateButton, resetButton;
    private JTextArea outputArea;

    // DFA state
    private static int dfaState;

    public DFSMSimulationGUI() {
        setTitle("DFSM Simulation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        add(new JLabel("Input String:"));
        inputField = new JTextField();
        add(inputField);

        simulateButton = new JButton("Simulate");
        simulateButton.addActionListener(this);
        add(simulateButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        add(resetButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        setVisible(true);
    }

    public static void main(String[] args) {
        new DFSMSimulationGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == simulateButton) {
            // Parse input string
            String inputString = inputField.getText().trim();

            // Perform DFSM simulation
            boolean result = isAccepted(inputString.toCharArray());
            outputArea.setText("Input string: " + inputString + "\nAccepted: " + result);
        } else if (e.getSource() == resetButton) {
            // Reset input field and output area
            inputField.setText("");
            outputArea.setText("");
        }
    }

    // DFSM logic
    static void start(char c) {
        if (c == 'a') {
            dfaState = 1;
        } else if (c == 'b') {
            dfaState = 3;
        } else {
            dfaState = -1;
        }
    }

    static void state1(char c) {
        if (c == 'a') {
            dfaState = 2;
        } else if (c == 'b') {
            dfaState = 4;
        } else {
            dfaState = -1;
        }
    }

    static void state2(char c) {
        if (c == 'b') {
            dfaState = 3;
        } else if (c == 'a') {
            dfaState = 1;
        } else {
            dfaState = -1;
        }
    }

    static void state3(char c) {
        if (c == 'b') {
            dfaState = 3;
        } else if (c == 'a') {
            dfaState = 4;
        } else {
            dfaState = -1;
        }
    }

    static void state4(char c) {
        dfaState = -1;
    }

    static int isAccepted(char str[]) {
        int len = str.length;
        for (int i = 0; i < len; i++) {
            if (dfaState == 0) start(str[i]);
            else if (dfaState == 1) state1(str[i]);
            else if (dfaState == 2) state2(str[i]);
            else if (dfaState == 3) state3(str[i]);
            else if (dfaState == 4) state4(str[i]);
            else return 0; // Invalid state
        }
        return (dfaState == 3) ? 1 : 0;
    }
}
