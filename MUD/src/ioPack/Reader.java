package ioPack;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Reader {

	private String path;
	
	public Reader(String file_path){
		path = file_path;
		
	}
	
	private int readLines() throws IOException{
		FileReader ftr = new FileReader(path);
		BufferedReader bf = new BufferedReader (ftr);
		
		int numberOfLines = 0;
		
		while (bf.readLine() != null){
			numberOfLines++;
			}	
		bf.close();
		return numberOfLines;
		}
		
	
	public String[] openFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader (fr);
		
		int numberOfLines = readLines();
		String[ ] textData = new String[numberOfLines];
		
		int i;

		for (i=0; i < numberOfLines; i++) {
		textData[ i ] = textReader.readLine();

		}
		textReader.close( );
		return textData;
	}
	
	
}
