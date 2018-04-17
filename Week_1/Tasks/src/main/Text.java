package main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {
	public static int number(String search) {
		String lines = null;
		try {
			lines = read();
		} catch(Exception e){
			System.err.println(e);
		}
		
		int result = 0;
		Pattern p = Pattern.compile(search);
		Matcher m = p.matcher(lines);
		while(m.find())
			result++;
		return result;
	}
	
	protected static String read() throws IOException{
		Path path = FileSystems.getDefault().getPath("src", "text", "what.txt");
		return Files.readAllLines(path, StandardCharsets.UTF_8).toString();
	}
}
