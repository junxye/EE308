package one;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    public static Messages getFile(String file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();    // 每行读入
        StringBuilder fileContent = new StringBuilder();    // 处理后的文本内容

        while(line != null){
            line = line.replaceAll("\\s*#.*", " ");     // 去除头文件指令
            line = line.replaceAll("\\s*//.*", " ");  // 去除注释
            line = removeString(line);  // 去除字符串
            fileContent.append(line);
            line = bufferedReader.readLine();
        }
        line = fileContent.toString();
        fileContent = new StringBuilder();
        fileContent.append(line.replaceAll("\\s*(/\\*).*(\\*/)\\s*", "").replaceAll("\\s+", " "));
        Messages messages = new Messages(file, fileContent);

        fileReader.close();
        bufferedReader.close();
        return messages;
    }

    public static String removeString(String s){
        String quote = "\"", fakeQuote = "\\\"";
        if (!s.contains(quote)) return s;
        int left = s.indexOf(quote), right = s.substring(left + 1).indexOf(quote) + left + 1;
        while(right != -1){
            if (s.indexOf(fakeQuote) == right - 1){
                right = s.substring(right + 1).indexOf(quote) + right + 1;
                continue;
            }
            s = s.replace(s.substring(left, right + 1), " ");
            left = s.indexOf(quote);
            if (left == -1) break;
            right = s.substring(left + 1).indexOf(quote) + left + 1;
        }
        return s;
    }
}
