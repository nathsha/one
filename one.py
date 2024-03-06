import tkinter as tk
import networkx as nx
import matplotlib.pyplot as plt

class DFSMSimulator(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Deterministic Finite State Machine Simulator")
        self.geometry("600x400")

        self.create_input_widgets()
        self.create_graph_frame()

    def create_input_widgets(self):
        # Input frame
        input_frame = tk.Frame(self)
        input_frame.pack(side=tk.LEFT, padx=10)

        # Number of states
        self.num_states_label = tk.Label(input_frame, text="Number of states:")
        self.num_states_label.grid(row=0, column=0, sticky=tk.W)
        self.num_states_entry = tk.Entry(input_frame)
        self.num_states_entry.grid(row=0, column=1)

        # Initial state
        self.initial_state_label = tk.Label(input_frame, text="Initial state:")
        self.initial_state_label.grid(row=1, column=0, sticky=tk.W)
        self.initial_state_entry = tk.Entry(input_frame)
        self.initial_state_entry.grid(row=1, column=1)

        # Number of accepting states
        self.num_accepting_states_label = tk.Label(input_frame, text="Number of accepting states:")
        self.num_accepting_states_label.grid(row=2, column=0, sticky=tk.W)
        self.num_accepting_states_entry = tk.Entry(input_frame)
        self.num_accepting_states_entry.grid(row=2, column=1)

        # Input alphabet
        self.input_alphabet_label = tk.Label(input_frame, text="Input alphabet (comma-separated):")
        self.input_alphabet_label.grid(row=3, column=0, sticky=tk.W)
        self.input_alphabet_entry = tk.Entry(input_frame)
        self.input_alphabet_entry.grid(row=3, column=1)

        # Transitions
        self.transitions_label = tk.Label(input_frame, text="Transitions (semicolon-separated, e.g., q0,a,q1):")
        self.transitions_label.grid(row=4, column=0, sticky=tk.W)
        self.transitions_entry = tk.Entry(input_frame)
        self.transitions_entry.grid(row=4, column=1)

        # Input string
        self.input_label = tk.Label(input_frame, text="Enter input string:")
        self.input_label.grid(row=5, column=0, sticky=tk.W)
        self.input_entry = tk.Entry(input_frame)
        self.input_entry.grid(row=5, column=1)

        # Simulate button
        self.simulate_button = tk.Button(input_frame, text="Simulate", command=self.simulate)
        self.simulate_button.grid(row=6, column=0, columnspan=2)

        # Result
        self.result_label = tk.Label(input_frame, text="")
        self.result_label.grid(row=7, column=0, columnspan=2)

    def create_graph_frame(self):
        # Graph frame
        graph_frame = tk.Frame(self)
        graph_frame.pack(side=tk.RIGHT, padx=10, pady=10)

        self.graph_canvas = tk.Canvas(graph_frame, bg="white", width=400, height=400)
        self.graph_canvas.pack()

    def simulate(self):
        try:
            num_states = int(self.num_states_entry.get())
            initial_state = self.initial_state_entry.get()
            num_accepting_states = int(self.num_accepting_states_entry.get())
            input_alphabet = [char.strip() for char in self.input_alphabet_entry.get().split(',')]
            transitions = [tuple(t.strip().split(',')) for t in self.transitions_entry.get().split(';')]
            input_string = self.input_entry.get()

            # Draw DFA graph
            G = nx.DiGraph()
            G.add_nodes_from(range(num_states))
            for transition in transitions:
                G.add_edge(int(transition[0][1:]), int(transition[2][1:]), label=transition[1])

            pos = nx.spring_layout(G)
            nx.draw(G, pos, with_labels=True, arrows=True, node_size=700, node_color="skyblue", font_size=12, font_weight="bold", ax=self.graph_canvas)
            edge_labels = {(n1, n2): G[n1][n2]['label'] for (n1, n2) in G.edges}
            nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, ax=self.graph_canvas)

            current_state = initial_state
            for char in input_string:
                next_state = None
                for transition in transitions:
                    if transition[0] == current_state and transition[1] == char:
                        next_state = transition[2]
                        break
                if next_state is None:
                    self.result_label.config(text="Invalid input")
                    return
                current_state = next_state

            if current_state in [transition[0] for transition in transitions if transition[2] in [f'q{i}' for i in range(num_accepting_states)]]:
                self.result_label.config(text="Accepted")
            else:
                self.result_label.config(text="Rejected")
        except ValueError:
            self.result_label.config(text="Please enter valid numbers")

if __name__ == "__main__":
    app = DFSMSimulator()
    app.mainloop()
