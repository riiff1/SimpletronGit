package paczka;

import sun.java2d.pipe.SpanIterator;

import java.io.FileNotFoundException;

public class Mainek {

    public static void main(String[] args) {
/*        Simpletron simpletron = new Simpletron();
        try {
            simpletron.runSimulator("C:\\Users\\rafal.zasada\\Desktop\\mojeProgramyIntelliJ\\Simpletron\\src\\main\\java\\dodajDwieLiczby.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        String s = Integer.toString(4)+ Integer.toString(5);
        int a = Integer.valueOf(s);
        System.out.println((char) a);
        char b = '-';
        System.out.println((int) b);

        char[] tab = " ".toCharArray();
        for(char c : tab) {
            System.out.println((int) c);
        }
    }
}
