package com.nmurphy.tacsim;

import java.util.Arrays;
import java.io.*;

public class Tacsim {

    public static void main(String[] args) {

        // Declare memory/registers
        int[] memory_array = new int[256];
        int ACC = 0; // register
        int X = 0; // register
        boolean SR = false; // flag
        int PP = 0; // Points to position in memory.
        // Initialize memory
        Arrays.fill(memory_array, 0);

        // Read in the file
        try
        {
            FileInputStream fr = new FileInputStream(new File(args[0]));
            int i = 0;
            int data = 0;
            while((data = fr.read()) != -1)
            {
                memory_array[i] = data;
            }
            data = 0;
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("\nEnter the File name in the command line argument. " +
                    "\nFor example: java tacsim NameOfFile");
        }
        catch (IOException ex)
        {
            System.out.println("File not found!");
        }

        // Reading and decoding information in memory
        int cycles = 0;
        while(cycles < 10000)
        {
            if (memory_array[PP] == 0) {
            } else if (memory_array[PP] == 1) {
                PP++;
                ACC += memory_array[PP];
            } else if (memory_array[PP] == 2) {
                PP++;
                ACC += memory_array[PP];
            } else if (memory_array[PP] == 3) {
                PP++;
                SR = ACC > memory_array[PP];
            } else if (memory_array[PP] == 4){
                PP++;
                if(SR){
                    PP = memory_array[PP];
                }
            } else if (memory_array[PP] == 5) {
                PP++;
                PP = memory_array[PP];
            } else if (memory_array[PP] == 6 || memory_array[PP] == 7) {
                PP++;
                ACC = memory_array[PP];
            } else if (memory_array[PP] == 8) {
                PP++;
                ACC = memory_array[PP + memory_array[PP]];
            } else if (memory_array[PP] == 9) {
                PP++;
                X = ACC;
            } else if (memory_array[PP] == 10) {
                PP++;
                int ST = memory_array[PP];
                memory_array[ST] = ACC;
            } else if (memory_array[PP] == 11) {
                PP++;
                ACC = ACC & memory_array[PP];
            } else if (memory_array[PP] == 12) {
                PP++;
                ACC = ACC | memory_array[PP];
            } else if (memory_array[PP] == 13) {
                PP++;
                ACC = ACC ^ memory_array[PP];
            } else if (memory_array[PP] == 14) {
                PP++;
                if (SR) {
                    PP = memory_array[PP];
                } else {
                    PP++;
                }
            } else if (memory_array[PP] == 15) {
                break;
            }

            if(PP >= 255){
                System.out.println("Memory is at limit or has been exceeded.");
            }
            ++cycles;
        }

        // Do a Memory-Dump
        for(int i = 0; i < memory_array.length; ++i) {
            System.out.print(memory_array[i]);
            if (i == 15 || i == 31 || i == 47 || i == 63 || i == 79 || i == 95 || i == 111 || i == 127 || i == 143 || i == 159
            || i == 175 || i == 191 || i == 207 || i == 223 || i == 239 || i == 255){
                System.out.println();
            }
        }
    }
}
