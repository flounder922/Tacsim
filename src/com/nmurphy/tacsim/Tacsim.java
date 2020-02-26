package com.nmurphy.tacsim;

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Tacsim
{
    public static void main(String[] args)
    {

        // Declare memory/registers
        int[] memory_array = new int[256];
        int ACC = 0; // register
        int X = 0; // register
        boolean SR = false; // flag
        int PP = 0; // Points to position in memory.
        // Initialize memory
        for (int i = 0; i < memory_array.length; ++i)
        {
            memory_array[i] = 0;
        }

        // Read in the file
        try
        {
            //System.out.println("Reading data into memory!!");
            Scanner scanner = new Scanner(new File(args[0]));
            int i = 0;
            while (scanner.hasNextInt()) {
                memory_array[i] = scanner.nextInt();
                ++i;
            }
            scanner.close();
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
        //System.out.println("Finished reading data into memory!\n");

        // Reading and decoding information in memory
        int cycles = 0;
        while(cycles < 10000)
        {
            //System.out.println("PP: " + PP);
            if (PP >= 256)
                break;
            if (memory_array[PP] == 0) {
                PP++;
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
                ACC = memory_array[PP + X];
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
                PP++;
                if(memory_array[PP] == 0)
                    break;
            }

            if(PP >= 255){
                System.out.println("Memory is at limit or has been exceeded.");
            }
            ++cycles;
            PP++;
        }

        // Do a Memory-Dump
        System.out.println("Memory Dump");
        for (int i = 0; i < 16; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 16; i < 32; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 32; i < 48; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 48; i < 64; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 64; i < 80; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 80; i < 96; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 96; i < 112; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 112; i < 128; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 128; i < 144; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 144; i < 160; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 160; i < 176; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 176; i < 192; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 192; i < 208; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 208; i < 224; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 224; i < 240; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
        for (int i = 240; i < 256; ++i) {
            System.out.print(memory_array[i] + "  ");
        }
        System.out.println();
    }
}