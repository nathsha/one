import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DFSMSimulator extends JFrame {
    private JTextField inputField;
    private JButton simulateButton;
    private JTextArea outputArea;

    private static final int Q0 = 0;
    private static final int Q1 = 1;
    private static final int Q2 = 2;
    private static final int Q3 = 3;

    private int currentState;

    public DFSMSimulator() {
        setTitle("DFSM Simulator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        simulateButton = new JButton("Simulate");
        outputArea = new JTextArea();

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Input:"));
        inputPanel.add(inputField);
        inputPanel.add(simulateButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulate();
            }
        });

        currentState = Q0;
    }

    private void simulate() {
        String input = inputField.getText();
        boolean accepted = runFSM(input);
        if (accepted)
            outputArea.append("Accepted\n");
        else
            outputArea.append("Rejected\n");
    }

    private boolean runFSM(String input) {
        for (char c : input.toCharArray()) {
            switch (currentState) {
                case Q0:
                    if (c == 'a') currentState = Q1;
                    else return false;
                    break;
                case Q1:
                    if (c == 'a') currentState = Q1;
                    else if (c == 'b') currentState = Q2;
                    else return false;
                    break;
                case Q2:
                    if (c == 'b') currentState = Q2;
                    else if (c == 'a') currentState = Q3;
                    else return false;
                    break;
                case Q3:
                    if (c == 'a') currentState = Q3;
                    else return false;
                    break;
            }
        }
        return currentState == Q2 || currentState == Q3;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DFSMSimulator().setVisible(true);
            }
        });
    }
}
