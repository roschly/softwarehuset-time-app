package sh.ui;

//Class copied from LibraryUI

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import sh.app.*; 

abstract public class Screen {
	TimeAppUI timeAppUI;
	TimeApp timeApp; 

	abstract public void printMenu(PrintWriter out) throws IOException;

	abstract public boolean processInput(String input, PrintWriter out);
	
	public void setTimeAppUI(TimeAppUI timeAppUI) {
		this.timeAppUI = timeAppUI;
	}

	public static void displayList(ArrayList<String> list){
		int i = 1;
		for (String s : list){
			System.out.println(i + ". " + s);
			i++;
		}
	}	
}