package parser;

import code.Translator;

import java.io.*;

public class RAndW {

    private String inputFile;
    private String outputFile;

    public RAndW(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public RAndW() {
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public void readAndWrite() throws IOException {

        FileReader fr = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(fr);
        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter writer = new BufferedWriter(fw);

        String line;
        Parser parser = new Parser();
        Translator translator = new Translator();

        //读取每一行
        while ((line = reader.readLine()) != null) {
            //预处理每行
            line = line.trim();     //去除空白字符
            if (line.isEmpty() || line.startsWith("//")) {
                //忽略空白行或注释行
                continue;
            }

            //将命令分解为各个部分
            String[] parsed = parser.parse(line);

            //转化为二进制
            String binLine = translator.translate(parsed);

            //写入文件
            writer.write(binLine + "\r\n");
        }

        writer.close();
        fw.close();
        reader.close();
        fr.close();
    }

}
