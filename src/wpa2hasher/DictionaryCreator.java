package wpa2hasher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DictionaryCreator {
	public static void main(String[] args) {
		ArrayList<String>words = new ArrayList<String>();
		ArrayList<String>commaDelimitedPassNameHash = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))){
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                words.add(sCurrentLine);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        System.out.println("File has been read. Starting Threads...");
        try{Thread.sleep(1000);}catch(Exception ex){}
        
        int length = words.size();
        int split = length / 4;
        int[]first = {0,split};
        int[]second= {split,split*2};
        int[]third = {split*2,split*3};
        int[]fourth= {split*3,split*4};
        
        Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int x = first[0]; x < first[1]; x++) {
					for(int y = first[0]; y < first[1]; y++) {
						System.out.println(words.get(x)+","+new WPA2Hasher(words.get(x), words.get(y), true).generate()+","+words.get(y));
						commaDelimitedPassNameHash.add(words.get(x)+","+new WPA2Hasher(words.get(x), words.get(y), true).generate()+","+words.get(y));
					}
				}
			}
		});
        Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int x = second[0]; x < second[1]; x++) {
					for(int y = second[0]; y < second[1]; y++) {
						System.out.println(words.get(x)+","+new WPA2Hasher(words.get(x), words.get(y), true).generate()+","+words.get(y));
						commaDelimitedPassNameHash.add(words.get(x)+","+new WPA2Hasher(words.get(x), words.get(y), true).generate()+","+words.get(y));
					}
				}
			}
		});
        Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int x = third[0]; x < third[1]; x++) {
					for(int y = third[0]; y < third[1]; y++) {
						System.out.println(words.get(x)+","+new WPA2Hasher(words.get(x), words.get(y), true).generate()+","+words.get(y));
						commaDelimitedPassNameHash.add(words.get(x)+","+new WPA2Hasher(words.get(x), words.get(y), true).generate()+","+words.get(y));
					}
				}
			}
		});
        Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int x = fourth[0]; x < fourth[1]; x++) {
					for(int y = fourth[0]; y < fourth[1]; y++) {
						System.out.println(words.get(x)+","+new WPA2Hasher(words.get(x), words.get(y), true).generate()+","+words.get(y));
						commaDelimitedPassNameHash.add(words.get(x)+","+new WPA2Hasher(words.get(x), words.get(y), true).generate()+","+words.get(y));
					}
				}
			}
		});
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        ArrayList<Thread>threads=new ArrayList<>();
        threads.add(t1);
        threads.add(t2);
        threads.add(t3);
        threads.add(t4);
        
        for(Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        
        try(PrintWriter output = new PrintWriter(new FileWriter("commaDelimWPAHashes.csv"))){
        	for(String line : commaDelimitedPassNameHash) {
        		output.write(line+"\n");
        	}
        	output.flush();
        }catch(Exception ex) {};
        
        System.out.println("\nCompleted!");
	}
}