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

import com.revature.logstatus.LogHere;


/**
 * 
 * @author Adalah
 *
 * Great way to easily read and write things quickly in many different ways 
 * 
 * I do want to give credit to many unnamed StackOverflow users. Priceless resource!
 * I did not copy entire blocks of code for this class; however I did find many examples of how to do many of the methods below 
 * 
 */

public class ReadWrite{
	
	/* Readers and writers to be used later
	 * Saves declaring it in each method so it can be closed in the try-catch-finally without issue
	 */
	public static ObjectOutputStream out = null;
	public static ObjectInputStream in = null;
	public static FileWriter fw = null;
	public static FileReader fr = null;
	public static BufferedWriter bw = null;
	public static BufferedReader br = null;

	
	/* Reads just the first line of a given File
	 * takes a File 'resource' and returns the currentLine (first line of 'resource') as a String
	 */
    public static String readFirstLine(File resource) {

        String fileName = resource.getPath();
        String currentLine = null;

        try {
        	LogHere.info("readFirstLine");
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            currentLine = br.readLine();
            
        } catch(FileNotFoundException fnfe) {
			System.err.println("Could not find file '" + resource.getName() + "'");
			LogHere.warn(fnfe.getMessage());
        } catch(IOException ioe) {
            System.out.println("Error reading file '" + resource.getName() + "'"); 
            LogHere.warn(ioe.getMessage());
        }finally {
        	try {
				br.close();
			} catch(IOException ioe) {
	            System.out.println("Error reading file '" + resource.getName() + "'"); 
	            LogHere.warn(ioe.getMessage());
	        }
        }
        return currentLine;
    }


    /* Prints entire File. Used as a sort of check
     * takes a File 'resource' and System.out each currentLine 
     */
    public static void readFileStrings(File resource) {

        String fileName = resource.getPath();
        String currentLine = null;

        try {
        	LogHere.info("readFileStrings");
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
            }   
        } catch(FileNotFoundException fnfe) {
			System.err.println("Could not find file '" + resource.getName() + "'");
			LogHere.warn(fnfe.getMessage());
        } catch(IOException ioe) {
            System.out.println("Error reading file '" + resource.getName() + "'"); 
            LogHere.warn(ioe.getMessage());
        } finally {
        	try {
				br.close();
			}  catch(IOException ioe) {
	            System.out.println("Error reading file '" + resource.getName() + "'"); 
	            LogHere.warn(ioe.getMessage());
	        }
        }
    }

    /* Takes input and returns it as a String
     * reads in String from InputStreamRead System.in (console command)
     */
	public static String inputLine() {
		
		String line = "";
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			line = br.readLine();			
		} catch (IOException ioe) {
            System.out.println("Error getting input from the input stream"); 
			ioe.printStackTrace();
		} 
		// Crazy bug if I close the bufferedreader stream so I'm leaving it open 
//		finally {
//		try {
//			br.close();
//		} catch (IOException ioe) {
//            System.out.println("Error getting input from the input stream"); 
//			logger.warn(ioe.getMessage());			
//		}
//	}
		return line;
	}
	
	// Count number of lines in a file 
	// easy way to quickly check current number of users etc 
	public static int lineCount(File resource){
		int result = 0;

		try
		(
		   FileReader input = new FileReader(resource);
		   LineNumberReader lnr = new LineNumberReader(input);
		)
		{
		   while (lnr.skip(Long.MAX_VALUE) > 0){}

		   result = lnr.getLineNumber() + 1;
		} catch(FileNotFoundException fnfe) {
			System.err.println("Could not find file '" + resource.getName() + "'");
			LogHere.warn(fnfe.getMessage());
        } catch(IOException ioe) {
            System.out.println("Error reading file '" + resource.getName() + "'"); 
            LogHere.warn(ioe.getMessage());
        } 
		// Getting a weird bug here again so I'm leaving this open 
//		finally {
//        	try {
//        		lnr.close();
//        	}  catch (IOException ioe) {
//	            System.out.println("Error getting input from the input stream"); 
//				logger.warn(ioe.getMessage());			
//			}
//        }
		return result;
	}

	// Writes to a new file at the path given
	// creates new File resource with string-text toBeWritten
	/* Writes a String to a new File
	 * Takes a File 'resource' and writes the String toBeWritten 
	 */
	public static void writeToNewFile(String toBeWritten, File resource) {
		try {

			fw = new FileWriter(resource);
			bw = new BufferedWriter(fw);
			
			toBeWritten+= "\n";
			bw.write(toBeWritten);
			
		} catch(FileNotFoundException fnfe) {
			System.err.println("Could not find file '" + resource.getName() + "'");
			LogHere.warn(fnfe.getMessage());
        } catch(IOException ioe) {
            System.out.println("Error reading file '" + resource.getName() + "'"); 
            LogHere.warn(ioe.getMessage());
        } finally {
        	try {
        		bw.close();
        	} catch (IOException ioe) {
	            System.out.println("Error getting input from the input stream"); 
	            LogHere.warn(ioe.getMessage());			
			}
        }
	}

	// Copies text into another file
	// takes entire text of destination and writes into resource, line by line
	
	/* Copies a file to another location line by line
	 * Takes a File 'resource' and writes each line to the File 'destination'
	 * Note that the 'destination' is not deleted afterwards
	 */
	public static void writeToAFileFromAFile(File destination, File resource) {
		
		try{
			
			fr = new FileReader(resource);
			br = new BufferedReader(fr);
			String readIn;
			
			while((readIn = br.readLine()) != null) {
				writeToExistingFile(readIn, destination);			
			}
			
		
		} catch(FileNotFoundException fnfe) {
			System.err.println("Could not find file '" + resource.getName() + "'");
			LogHere.warn(fnfe.getMessage());
        } catch(IOException ioe) {
            System.out.println("Error reading file '" + resource.getName() + "'"); 
            LogHere.warn(ioe.getMessage());
        } finally {
        	try {
        		br.close();
        	} catch (IOException ioe) {
	            System.out.println("Error getting input from the input stream"); 
	            LogHere.warn(ioe.getMessage());			
			}
        }
		
	}

	// Writes to an existing file at the path given
	// appends File 'resource' with string-text toBeWritten
	
	/* Writes a String to an existing File
	 * Takes a File 'resource' and writes.appends the String 'toBeWritten' 
	 */
	public static void writeToExistingFile(String toBeWritten, File resource) {
		
		try {
			fw = new FileWriter(resource, true);
			bw = new BufferedWriter(fw);
			
			toBeWritten+= "\n";
			bw.write(toBeWritten);
						
		} catch (NoSuchFileException nsfe) {
			writeToNewFile(toBeWritten, resource);
			//logger.warn(nsfe.getMessage());
		}  catch(FileNotFoundException fnfe) {
			System.err.println("Could not find file '" + resource.getName() + "'");
			LogHere.warn(fnfe.getMessage());
        } catch(IOException ioe) {
            System.out.println("Error reading file '" + resource.getName() + "'"); 
            LogHere.warn(ioe.getMessage());
        } finally {
        	try {
        		bw.close();
        	} catch (IOException ioe) {
	            System.out.println("Error getting input from the input stream"); 
	            LogHere.warn(ioe.getMessage());			
			}
        } 
	}

	// Finds a string and removes it from file
	// given a String toBeRemoved at File 'resource', delete it, doing so by reading line by line 
	
	/* Deletes a given String from a given File
	 * Takes a File 'resource', reads each currentLine, finds the String toBeRemoved, and then writes the remaining currentLine s
	 */
	public static void deleteContentOfFile(String toBeRemoved, File resource) {
		
		File tempFile = new File("src/main/resources/tempfile.txt");
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
			
		} catch(FileNotFoundException fnfe) {
			System.err.println("Could not find file '" + resource.getName() + "'");
			LogHere.warn(fnfe.getMessage());
        } catch(IOException ioe) {
            System.out.println("Error reading file '" + resource.getName() + "'"); 
            LogHere.warn(ioe.getMessage());
        } finally {
        	try {
        		br.close();
        		bw.close();
        	} catch (IOException ioe) {
	            System.out.println("Error getting input from the input stream"); 
	            LogHere.warn(ioe.getMessage());			
			}
        }
	}

	// Ensures all readers and writers are closed
	// if read/write methods are open and not null, close them
	
	/* A clean up of any open readers / writers
	 * Used to close up any memory leaks (just in case)
	 * If any given BufferedReader,Writer, FileReader,Writer is open, this will close it, given they are not null
	 */
	public static void codeCleanUp() {
		try {
			
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


