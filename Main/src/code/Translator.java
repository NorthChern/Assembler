package code;

import java.util.HashMap;

public class Translator {

    private final HashMap<String, String> destTable = new HashMap<>();
    private final HashMap<String, String> compTable = new HashMap<>();
    private final HashMap<String, String> jumpTable = new HashMap<>();

    public Translator() {
        destTable.put("", "000");
        destTable.put("M", "001");
        destTable.put("D", "010");
        destTable.put("MD", "011");
        destTable.put("A", "100");
        destTable.put("AM", "101");
        destTable.put("AD", "110");
        destTable.put("AMD", "111");

        compTable.put("0", "0101010");
        compTable.put("1", "0111111");
        compTable.put("-1", "0111010");
        compTable.put("D", "0001100");
        compTable.put("A", "0110000");
        compTable.put("!D", "0001101");
        compTable.put("!A", "0110001");
        compTable.put("-D", "0001111");
        compTable.put("-A", "0110011");
        compTable.put("D+1", "0011111");
        compTable.put("A+1", "0110111");
        compTable.put("D-1", "0001110");
        compTable.put("A-1", "0110010");
        compTable.put("D+A", "0000010");
        compTable.put("D-A", "0010011");
        compTable.put("A-D", "0000111");
        compTable.put("D&A", "0000000");
        compTable.put("D|A", "0010101");

        compTable.put("M", "1110000");
        compTable.put("!M", "1110001");
        compTable.put("-M", "1110011");
        compTable.put("M+1", "1110111");
        compTable.put("M-1", "1110010");
        compTable.put("D+M", "1000010");
        compTable.put("D-M", "1010011");
        compTable.put("M-D", "1000111");
        compTable.put("D&M", "1000000");
        compTable.put("D|M", "1010101");

        jumpTable.put("", "000");
        jumpTable.put("JGT", "001");
        jumpTable.put("JEQ", "010");
        jumpTable.put("JGE", "011");
        jumpTable.put("JLT", "100");
        jumpTable.put("JNE", "101");
        jumpTable.put("JLE", "110");
        jumpTable.put("JMP", "111");
    }

    public String translate(String[] parsed){
        String result;
        //转换逻辑

        //1.A指令
        if(parsed.length == 2){
            result = "0";
            result += convertToFixedLengthBinary(parsed[1], 15);
        }
        //2.C命令
        else{
            result = "111";
            result += compTable.get(parsed[1]);
            result += destTable.get(parsed[0]);
            result += jumpTable.get(parsed[2]);
        }
        return result;
    }

    //填充二进制数为固定位
    public static String convertToFixedLengthBinary(String decimalStr, int length) {
        int decimal = Integer.parseInt(decimalStr);
        String binary = Integer.toBinaryString(decimal);
        // 使用String.format()进行格式化，%0<length>s表示用0进行左填充至指定长度
        return String.format("%" + length + "s", binary).replace(' ', '0');
    }
}
