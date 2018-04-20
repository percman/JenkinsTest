package com.revature.readwrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.NoSuchFileException;

public class ReadWrite{

	// Declared here so it can be easily found and path changed if need be
	public static File tempFile = new File("src/main/resources/tempfile.txt");
	
	// Readers and writers to be used later
	// not sure on static declarations, but it stopped yelling at me when I did this so..
	public static ObjectOutputStream out = null;
	public static ObjectInputStream in = null;
	public static FileWriter fw = null;
	public static FileReader fr = null;
	public static BufferedWriter bw = null;
	public static BufferedReader br = null;
	
	// Typically used so the entire file isn't loaded all at once
	// ex: read first line into array, delete the line, repeat 
    public static String readFirstLine(File resource) {

        String fileName = resource.getPath();
        String currentLine = null;

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            currentLine = br.readLine();
            
            br.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" +  fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'"); 
        }
        return currentLine;
    }

    // Prints entire file
    // used as a sort of check
    public static void readFileStrings(File resource) {

        String fileName = resource.getPath();
        String currentLine = null;

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
            }   
            br.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +  fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" + fileName + "'"); 
        }
    }

    // User input 
    // consoleinput = aquireline();
	public static String aquireLine() {
		
		String line = "";
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			line = br.readLine();
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		return line;
	}
	
	// Count number of lines in a file 
	// easy way to quickly check current number of users etc 
	public static int lineCount(File file){
		int result = 0;
		try
		(
		   FileReader input = new FileReader(file);
		   LineNumberReader count = new LineNumberReader(input);
		)
		{
		   while (count.skip(Long.MAX_VALUE) > 0)
		   {
		      // Loop just in case the file is > Long.MAX_VALUE or skip() decides to not read the entire file
		   }

		   result = count.getLineNumber() + 1;                                    // +1 because line index starts at 0
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// Writes to a new file at the path given
	// creates new File resource with string-text toBeWritten
	public static void writeToNewFile(String toBeWritten, File resource) {
		try {

			fw = new FileWriter(resource);
			bw = new BufferedWriter(fw);
			
			toBeWritten+= "\n";
			bw.write(toBeWritten);
			
		} catch (FileNotFoundException fnfe) {
			System.err.println("Could not find file " + resource.getName());
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
	}

	// Copies text into another file
	// takes entire text of destination and writes into resource, line by line
	public static void writeToAFileFromAFile(File destination, File resource) {
		
		try{
			
			fr = new FileReader(resource);
			br = new BufferedReader(fr);
			String readIn;
			
			while((readIn = br.readLine()) != null) {
				writeToExistingFile(readIn, destination);			
			}
			
		
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

	// Writes to an existing file at the path given
	// appends File 'resource' with string-text toBeWritten
	public static void writeToExistingFile(String toBeWritten, File resource) {
		
		try {
			fw = new FileWriter(resource, true);
			bw = new BufferedWriter(fw);
			
			toBeWritten+= "\n";
			bw.write(toBeWritten);
			
			bw.close();
			
		} catch (NoSuchFileException nsfe) {
			writeToNewFile(toBeWritten, resource);
			System.out.println("New user file created.");
		} catch (FileNotFoundException fnfe) {
			System.err.println("Could not find file " + resource.getName() + ". Created new file safely.");
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
	}

	// Finds a string and removes it from file
	// given a String toBeRemoved at File 'resource', delete it, doing so by reading line by line 
	public static void deleteContentOfFile(String toBeRemoved, File resource) {
		
		String currentLine;
		
		try {
			fr = new FileReader(resource);
			fw = new FileWriter(tempFile);
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);

			while((currentLine = br.readLine()) != null) {
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(toBeRemoved)) continue;
			    bw.write(currentLine + System.getProperty("line.separator"));
			}			
			
			codeCleanUp();
			
			// Below is a workaround. Apparently you cannot rename it directly from resource
			// Still having trouble deleting and renaming files. Windows problem I guess (?)
			resource.delete();
			tempFile.renameTo(resource);
			tempFile.delete();
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// Ensures all readers and writers are closed
	// if read/write methods are open and not null, close them
	public static void codeCleanUp() {
		try {
			
			if(tempFile.exists()) tempFile.delete();
			if(br != null)  br.close();
			if(bw != null)  bw.close();
			if(fw != null) fw.close();
			if(out != null) out.close();
			if(in != null) in.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch(NullPointerException npe) {
			npe.printStackTrace();
			System.out.println("This error was successfully handled.");
		}
	}
	
}


