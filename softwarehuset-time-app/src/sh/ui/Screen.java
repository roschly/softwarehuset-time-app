package sh.ui;

//Class copied from LibraryUI

import java.io.IOException;
import java.io.PrintWriter;
import sh.app.*; 

abstract public class Screen {

	TimeAppUI timeAppUI;
	TimeApp timeApp; 

	abstract public void printMenu(PrintWriter out) throws IOException;

	public void setTimeAppUI(TimeAppUI timeAppUI) {
		this.timeAppUI = timeAppUI;
	}

	abstract public boolean processInput(String input, PrintWriter out);
}