// Tjek om path passer 
package sh.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import sh.app.*; 

//Class copied from LibraryUI
public class TimeAppUI {

	private Screen screen;
	private TimeApp timeApp = new TimeApp();

	public TimeAppUI() {
		setScreen(new LoginScreen());
	}

	public void printMenu(PrintWriter out) throws IOException {
		getScreen().printMenu(out);
	}

	public boolean processInput(String input, PrintWriter out) throws IOException {
		boolean exit = getScreen().processInput(input, out);
		out.println();
		return exit;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out, true);
		TimeAppUI ui = new TimeAppUI();
		ui.basicLoop(in, out);
	}

	public void basicLoop(BufferedReader in, PrintWriter out)
			throws IOException {
		String selection;
		do {
			printMenu(out);
			selection = readInput(in);
		} while (!processInput(selection, out));
	}

	// Added public
	public void setScreen(Screen screen) {
		this.screen = screen;
		this.screen.setTimeAppUI(this);
	}

	Screen getScreen() {
		return screen;
	}

	TimeApp getTimeApp() {
		return timeApp;
	}

	public String readInput(BufferedReader in) throws IOException {
		return in.readLine();
	}
}
