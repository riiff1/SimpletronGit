package paczka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Simpletron {

    private int accumulator;
    private int [] memory;
    private int instructionRegister;
    private int instructionCounter;
    private int operationCode;
    private int operand;

    public Simpletron ( )
    {
        initialiseVariables ();
    }


    public void runSimulator (String filePath) throws FileNotFoundException {

        File file = new File(filePath);
        Scanner in = new Scanner(file);
        int submittedInstruction = 0;
        int memoryPointer = 0;

        while (in.hasNext()) {
            submittedInstruction = in.nextInt();
            System.out.printf ("%d\t%s\t%d\n", memoryPointer, "?", submittedInstruction);
            if ( memoryPointer >= memory.length ) {
                System.out.println("Wiecej komend niz pamieci, zmien plik: " + filePath);
                System.out.println("Koniec programu");
                System.exit(1);
            }
            memory [ memoryPointer ] = submittedInstruction;
            memoryPointer++;
        }

        System.out.printf ("\n%s%s", "*** Program loading completed ***\n",
                "*** Program excecution begins  ***\n");

        for ( int code : memory )
        {
            if ( code != 0 )
            {
                load ();
                execute ( operand, convertFromDecimalToHex(operationCode) );
            }
        }
    }	// end method runSimulator

    private OperationCode convertFromDecimalToHex(int number) {
        String hexString = Integer.toHexString(number).toUpperCase();
        return OperationCode.operationCode(hexString);
    }

    public void initialiseVariables ( )
    {
        memory = new int [1000];
        instructionCounter = 0;

    }	// end method initialiseVariables

    public void load ( )
    {

        operationCode = memory [ instructionCounter ] / 100;
        operand = memory [ instructionCounter ] % 100;

    }	// end method load

    public void execute (int operands, OperationCode operation )
    {
        switch (operation)
        {
            case READ:
                Scanner input = new Scanner ( System.in );
                System.out.print ( "Please Enter a whole number (positive or negative): " );
                memory[operands] = input.nextInt ();
                break;
            case WRITE:
                System.out.println ("The result of the operation is " + memory [ operands] );
                break;

            case LOAD:
                accumulator = memory[operands];
                break;
            case STORE:
                memory[operands] = accumulator;
                break;

            case ADD:
                accumulator += memory[operands];
                break;
            case SUBSTRUCT:
                accumulator -= memory[operands];
                break;
            case DIVIDE:
                accumulator /=  memory[operands];
                break;
            case MULTIPLY:
                accumulator *= memory[operands];
                break;
            case DIVISION_WITH_REMAINDER:
                accumulator %= memory[operands];
                break;
            case POW:
                //accumulator = Math.pow(accumulator, memory[operands]);
                break;

            case BRANCH:
                instructionCounter = operands;
                instructionCounter--;
                break;
            case BRANCH_NEG:
                if ( accumulator < 0 ) {
                    instructionCounter = operands;
                }
                instructionCounter--;
                break;
            case BRANCH_ZERO:
                if ( accumulator == 0 ) {
                    instructionCounter = operands;
                }
                instructionCounter--;
                break;
            case HALT:
                dumpTheCore ();
                System.out.printf ("\n%s\n", "The program has ended...");
                System.exit ( 0 );
                break;

            case READ_SENTENCES:
                Scanner inputString = new Scanner ( System.in );
                System.out.print ( "Please Enter a whole number (positive or negative): " );
                String s = inputString.nextLine();
                memory[operands] = s.length();
                AtomicInteger count = new AtomicInteger(1);
                s.chars().forEach(i -> {memory[operands + count.get()] = i; count.getAndIncrement();});

                break;
            case WRITE_SENTENCES:
                char[] chars = new char[memory[operands]];
                for(int i= 1; i<= memory [ operands]; i++) {
                    chars[i-1] = (char) memory[operands+i];
                }
                System.out.println ("String is " + String.valueOf(chars) );
                break;
        }

        instructionCounter++;
    }	// end method exceute

    public void dumpTheCore ( )
    {
        System.out.printf ("\n%30s\n%30s\t%s%4d\n%30s\t%2d\n%30s\t%2d\n%30s\t%2d\n%30s\t%2d\n\n%30s\n", "REGISTERS:",
                "accumulator", "+", accumulator, "instruction counter", instructionCounter, "instruction register",
                instructionCounter, "operation code", operationCode, "operand", operand, "MEMORY:" );

        for ( int i = 0; i < 10; i++ )
        {
            System.out.printf ( "%6d", i);
        }

        System.out.println ();
        int counter = 0;
        for (int i = 0; i < 10; i++ )
        {
            if ( counter %10 == 0 )
                System.out.printf ("%2d ", counter);
            for (int j = 0; j < 10; j++)
            {
                // Lets apply some formatting to improve the display
                if ( memory [ counter ] == 0 )
                    System.out.printf ( "%s%s", "+", "0000 ");
                else
                    System.out.printf ("%s%4d ", "+", memory [counter]);
                counter++;

            }

            System.out.println ();

        }
    }
}
