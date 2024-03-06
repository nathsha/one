import tkinter as tk

class DFA:
    def __init__(self):
        self.states = {"q0", "q1", "q2", "q3"}
        self.transitions = {
            "q0": {"a": "q1", "b": "q2"},
            "q1": {"a": "q0", "b": "q3"},
            "q2": {"a": "q3", "b": "q2"},
            "q3": {"a": "q3", "b": "q3"}
        }
        self.initial_state = "q0"
        self.accepting_state = "q2"

    def process_input(self, input_string):
        current_state = self.initial_state
        for symbol in input_string:
            if symbol not in {"a", "b"}:
                return "Wrong Language!"
            current_state = self.transitions[current_state].get(symbol, None)
            if current_state is None:
                return "Your input was rejected by the DFA"
        return "Your input was accepted by the DFA" if current_state == self.accepting_state else "Your input was rejected by the DFA", current_state

class Application(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("DFA Simulation")
        self.geometry("400x200")

        self.dfa = DFA()
        self.canvas = tk.Canvas(self, width=400, height=200)
        self.canvas.pack()

        tk.Label(self, text="Input:").pack()
        self.input_entry = tk.Entry(self)
        self.input_entry.pack()

        self.process_button = tk.Button(self, text="Process Input", command=self.process_input)
        self.process_button.pack()

        self.output_label = tk.Label(self, text="")
        self.output_label.pack()

    def process_input(self):
        input_string = self.input_entry.get().strip()
        if not input_string:
            self.output_label.config(text="Enter the string!")
            return
        result, final_state = self.dfa.process_input(input_string)
        self.output_label.config(text=result)
        self.animate(final_state)

    def animate(self, final_state):
        self.canvas.delete("all")
        self.canvas.create_oval(50, 50, 100, 100, outline="black", fill="white")  # q0
        self.canvas.create_oval(200, 50, 250, 100, outline="black", fill="white")  # q1
        self.canvas.create_oval(350, 50, 400, 100, outline="black", fill="white")  # q2
        self.canvas.create_oval(200, 150, 250, 200, outline="black", fill="white")  # q3

        state_positions = {
            "q0": (75, 75),
            "q1": (225, 75),
            "q2": (375, 75),
            "q3": (225, 175)
        }

        if final_state in state_positions:
            x, y = state_positions[final_state]
            self.canvas.create_oval(x - 10, y - 10, x + 10, y + 10, outline="black", fill="green")

if __name__ == "__main__":
    app = Application()
    app.mainloop()
