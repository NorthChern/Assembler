package parser;

public class Parser {

    public Parser() {
    }

    public String[] parse(String line){
        //分解命令的逻辑

        String[] strings;
        String[] result;
        //1.A命令
        if(line.startsWith("@")){
            result = new String[2];
            result[0] = line.substring(0,1);
            result[1] = line.substring(1);
        }
        //C命令
        else {
            result = new String[3];
            String regex = "[=;]";
            strings = line.split(regex);
            if(line.contains("=") && line.contains(";")){
                result = strings;
            }
            if(line.contains("=") && !line.contains(";")){
                result[0] = strings[0];
                result[1] = strings[1];
                result[2] = "";
            }
            if(!line.contains("=") && line.contains(";")){
                result[0] = "";
                result[1] = strings[0];
                result[2] = strings[1];
            }
        }

        return result;
    }
}
