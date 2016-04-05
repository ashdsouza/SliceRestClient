package com.word.count;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

public class StringLogic {

	private static String word;
//	private static String fileName = "src/txtfiles.tar.bz2";
	private static String fileName = "src/0paine.txt";
	private static Count count = new Count();
	public StringLogic(String word) {
		this.word = word;
	}
	
	public Count findOccurances() throws IOException, Exception {
//		getBufferedReaderForCompressedFile();
		findCount(fileName);
//		printHashMap();
		return count;
	}
	
	public static void findCount(String internalFile) throws Exception {
		BufferedReader brIn = null;
		// Open the file
		try {
			FileInputStream fstream = new FileInputStream(internalFile);
			brIn = new BufferedReader(new InputStreamReader(fstream));
			String strLine = "";
			Integer countStringOcc = 0;	
			Integer countExactMatch = 0;
			//Read File Line By Line
			while ((strLine = brIn.readLine()) != null)   {
				strLine = strLine.trim(); 	//delete leading and trailing spaces	
				String sentence[] = strLine.split(" "); 	//split on space in the sentence; assuming:- words are seperated by space
				for(int i=0 ; i < sentence.length; i++) {
					if(sentence[i].equals(word)) {
						countExactMatch++;
					}
					if(sentence[i].equalsIgnoreCase(word)) {
						countStringOcc++;
					}
				} 
			}
			if(count.getCountExactMatch() != null) { count.setCountExactMatch(count.getCountExactMatch() + countExactMatch); }
			else { count.setCountExactMatch(countExactMatch); }
			if(count.getCountInFiles() != null) { count.setCountInFiles(count.getCountInFiles() + countStringOcc); }
			else { count.setCountInFiles(countStringOcc); }
		} catch(Exception e) {
			System.out.println("Error occured while performing File I/O " + e.getMessage());
			throw new Exception("Error occured while performing File I/O " + e.getMessage());
		} finally {
			//Close the input stream
			brIn.close();
		}
	}
	
	//1st method........................
//	public static BufferedReader getBufferedReaderForCompressedFile() throws CompressorException, IOException{
//	    FileInputStream fin = new FileInputStream(fileName);
//	    BufferedInputStream bis = new BufferedInputStream(fin);
//	    CompressorInputStream input = new CompressorStreamFactory().createCompressorInputStream(bis);
//	    BufferedReader br2 = new BufferedReader(new InputStreamReader(input));
//	    //printFolder(br2, br2.readLine());
//	    return br2;
//	}
	
	public static void getBufferedReaderForCompressedFile() throws Exception {
		//2nd method......................
		// 1st InputStream from your compressed file
//		FileInputStream in = new FileInputStream(fileName);
//		// wrap in a 2nd InputStream that deals with compression
//		BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);
//		// wrap in a 3rd InputStream that deals with tar
//		TarArchiveInputStream tarIn = new TarArchiveInputStream(bzIn);
//		ArchiveEntry entry = null;
//
//		while (null != (entry = tarIn.getNextEntry())){
//		    if (entry.getSize() < 1){
//		        continue;
//		    }
//		    // use your parser here, the tar inputStream deals with the size of the current entry
//		    System.out.println("Tar Entry = " + tarIn);
//		    findCount(tarIn.getCurrentEntry().getName());
//		}
//		tarIn.close();
		
		
		//3rd method....................
//		FileInputStream fin = new FileInputStream(fileName);
//		BufferedInputStream in = new BufferedInputStream(fin);
//		FileOutputStream out = new FileOutputStream("txtfiles.tar");
//		BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);
//		final byte[] buffer = new byte[Integer.MAX_VALUE];
//		int n = 0;
//		while (-1 != (n = bzIn.read(buffer))) {
//		    out.write(buffer, 0, n);
//		}
//		out.close();
//		bzIn.close();
	}
	
//	public static void printHashMap() {
//		System.out.println("For Word = " + word);
//		for(Map.Entry<String, Integer> entry : wordCount.entrySet()) {
//			System.out.println("FileName = " + entry.getKey() + " WordCount = " + entry.getValue());
//		}
//	}
	
//	public static void printFolder(String root) throws IOException {
//		System.out.println("Here........." + root);
//		File f = new File(root);
//		if(f.isDirectory()) {
//			printFolder(br, br.readLine());
//		}
//		System.out.println(br.readLine());
//	}
	
}
