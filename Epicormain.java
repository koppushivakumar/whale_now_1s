package epicor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Epicormain {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.print("Epicor");
		FileInputStream fis = new FileInputStream("Test_V2.docx");
		System.out.println(fis);
	}

}
