import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class mipsAssembler {

    Map<String, String> r;
    Map<String, String> b;
    Map<String, String> im;
    Map<String, String> regs;
    Map<String, Integer> l;
    int PC;
    int nPC;

    public mipsAssembler () {
        r = new HashMap<>();
        r.put("add", "100000");
        r.put("addu", "100001");
        r.put("and", "100100");
        r.put("slt", "101010");

        b = new HashMap<>();
        b.put("beq", "000100");
        b.put("bne", "000101");

        im = new HashMap<>();
        im.put("addi", "001000");
        im.put("ori", "001101");

        regs = new HashMap<>();
        regs.put("r0", "00000");
        regs.put("at", "00001");
        regs.put("v0", "00010");
        regs.put("v1", "00011");
        regs.put("a0", "00100");
        regs.put("a1", "00101");
        regs.put("a2", "00110");
        regs.put("a3", "00111");
        regs.put("t0", "01000");
        regs.put("t1", "01001");
        regs.put("t2", "01010");
        regs.put("t3", "01011");
        regs.put("t4", "01100");
        regs.put("t5", "01101");
        regs.put("t6", "01110");
        regs.put("t7", "01111");
        regs.put("s0", "10000");
        regs.put("s1", "10001");
        regs.put("s2", "10010");
        regs.put("s3", "10011");
        regs.put("s4", "10100");
        regs.put("s5", "10101");
        regs.put("s6", "10110");
        regs.put("s7", "10111");
        regs.put("t8", "11000");
        regs.put("t9", "11001");

        l = new HashMap<>();

        PC = 0;
        nPC = 4;
    }

    // Assembles prog.asm --> prog.txt
    public void assemble () {
        ArrayList<String[]> lines = parseFile();
        PrintWriter out = writeUtility();
        // Second Pass: Convert to Binary & Calculate Addresses
        for (int i = 0; i < lines.size(); i++) {
            String lineOut = "";
            String[] attr = lines.get(i);
            if (r.containsKey(attr[0])) {
                lineOut += "000000" + regs.get(attr[2]) + regs.get(attr[3]) +
                        regs.get(attr[1]) + "00000" + r.get(attr[0]);
            } else if (b.containsKey(attr[0])) {
                lineOut += b.get(attr[0]) + regs.get(attr[1]) + regs.get(attr[2]) +
                        toBinary((l.get(attr[3]) - nPC >> 2), 16);
            } else if (im.containsKey(attr[0])) {
                lineOut += im.get(attr[0]) + regs.get(attr[2]) + regs.get(attr[1]) +
                        toBinary((Integer.parseInt(attr[3])), 16);
            } else if (attr[0].compareTo("j") == 0) {
                lineOut += "000010" + toBinary((l.get(attr[1]) >> 2), 26);
            } else if (attr[0].compareTo("sll") == 0) {
                lineOut += "00000000000" + regs.get(attr[2]) + regs.get(attr[1]) +
                        toBinary(Integer.parseInt(attr[3]), 5) + "000000";
            }
            advancePC(4);
            out.println(lineOut);
        }
        out.close();
    }

    private ArrayList<String[]> parseFile() {
        Scanner s = readUtility();
        ArrayList<String[]> lines = new ArrayList();
        // First pass: handle formatting, labels and pseudo-instructions
        while (s.hasNextLine()) {
            String c = s.nextLine();
            if (c.length() > 0 && c.charAt(0) != '.') {
                if (c.indexOf(':') != -1) {
                    l.put(c.substring(0, c.indexOf(':')), PC);
                    c = c.substring(c.indexOf(':'));
                }
                c = c.replaceAll("[^\\s\\w]", "");
                c = c.trim();
                if (c.length() > 0) {
                    String[] attr = c.split("[\\s]");
                    if (attr[0].compareTo("move") == 0) {
                        String[] toAdd = {"addu", attr[1], attr[2], "r0"};
                        lines.add(toAdd);
                    } else if (attr[0].compareTo("blt") == 0) {
                        String[] toAdd = {"slt", "at", attr[1], attr[2]};
                        lines.add(toAdd);
                        advancePC(4);
                        String[] toAdd2 = {"bne", "at", "r0", attr[3]};
                        lines.add(toAdd2);
                    } else {
                        lines.add(attr);
                    }
                    advancePC(4);
                }
            }
        }
        s.close();
        resetPC();
        return lines;
    }

    private void advancePC(int offset) {
        PC = nPC;
        nPC += offset;
    }

    private void resetPC() {
        PC = 0;
        nPC = 4;
    }

    private String toBinary(int x, int n) {
        String out = Integer.toBinaryString(x);
        char l;
        if (x < 0) l ='1';
        else l = '0';
        while (out.length() < n) {
            out = l + out;
        }
        return out;
    }

    private PrintWriter writeUtility() {
        PrintWriter p = null;
        try {
            p = new PrintWriter(new FileWriter("./prog.txt"));
        } catch (IOException e) {
            System.out.println("Error instantiating FileWriter.");
            System.exit(-1);
        }
        return p;
    }

    private Scanner readUtility() {
        Scanner s = null;
        try {
            s = new Scanner(new File("./prog.asm"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(-1);
        }
        return s;
    }
}
