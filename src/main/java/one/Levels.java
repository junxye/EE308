package one;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Levels {

    private Messages messages;
    private static String charMatches = "[a-zA-Z0-9]";

    public void levelOne(String file) {
        getMessage(file);
        Pattern p = Pattern.compile("([A-Za-z]+)");
        Matcher m = p.matcher(messages.getFileContent());
        long tol = 0;
        while (m.find()) {
            for (String str : messages.getKeys())
                if (str.equals(m.group())) {
                    tol++;
                    break;
                }
        }
        System.out.println("num: " + tol);
    }

    public void levelTwo(String file) {
        levelOne(file);
        int tol = 0;
        List<Integer> cases = new ArrayList<>();
        String findSwitch = "switch";
        int begin = messages.getFileContent().indexOf(findSwitch);
        int last = 0;
        while (begin != -1){
            begin += last;
            // 排除非关键词可能性
            if (keyJudge(messages.getFileContent().substring(begin - 1, begin + 7))) {
                last = begin + 6;
                begin = messages.getFileContent().substring(last).indexOf(findSwitch);
                continue;
            }
            tol++;
            String line = messages.getFileContent().substring(begin);
            int left = 0, right = line.indexOf("}"), count = 0;
            while (noEnd(line.substring(0, right + 1))){
                if (!line.substring(left, right + 1).contains(findSwitch))
                    right = line.substring(right + 1).indexOf("}") + right + 1;
                else {
                    count += getCaseNumber(line.substring(left, line.indexOf(findSwitch)));
                    while (noEnd(line.substring(left).substring(line.indexOf(findSwitch), right + 1)))
                        right = line.substring(right + 1).indexOf("}") + right + 1;
                    left = right + 1;
                    right = line.substring(left).indexOf("}") + right + 1;
                }
            }
            count += getCaseNumber(line.substring(left, right + 1));
            cases.add(count);
            last = begin + 6;
            begin = messages.getFileContent().substring(last).indexOf(findSwitch);
        }
        System.out.print("switch num: " + tol + "\ncase num: ");
        for (int number : cases) {
            System.out.print(number + "  ");
        }
        System.out.println();
    }

    public void levelThree(String file) {
        elseCount(file, false);
    }

    public void levelFour(String file) {
        elseCount(file, true);
    }

    public void getMessage(String file){
        try {
            messages = FileUtil.getFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean noEnd(String line){
        int i = line.length() - line.replaceAll("[{]", "").length();
        int j = line.length() - line.replaceAll("[}]", "").length();
        return i != j;
    }

    public int getCaseNumber(String line){
        int cut = line.replaceAll("[^a-zA-Z0-9]case[^a-zA-Z0-9]", "  ").length();
        return (line.length() - cut) / 4;
    }

    public void elseCount(String file, boolean needElseIf){
        levelTwo(file);
        int elseNumber = 0, elseIfNumber = 0;
        String findIf = "if", findElse = "else", content = messages.getFileContent().toString();
        int index = content.indexOf(findIf), last = 0;
        while (index != -1){
            index += last;
            if ((index > 4 && content.substring(index - 5, index - 1).matches(findElse)) ||
                    (index > 0 && keyJudge(content.substring(index - 1, index + 3)))){
                last = index + 2;
                index = content.substring(last).indexOf(findIf);
                continue;
            }
            // fine else
            int end = content.substring(index).indexOf(findElse);
            int endLast = index;
            boolean hasElse = false;
            while (end != -1){
                hasElse = true;
                end += endLast;
                if (keyJudge(content.substring(end - 1, end + 5)) || noEnd(content.substring(index, end))){
                    endLast = end + 4;
                    end = content.substring(endLast).indexOf(findElse);
                    continue;
                }
                if (content.substring(end).length() > 6 && content.substring(end + 5, end + 7).matches(findIf))
                    elseIfNumber++;
                else elseNumber++;
                break;
            }
            if (!hasElse) break;
            last = index + 2;
            index = content.substring(last).indexOf(findIf);
        }
        System.out.println("if-else num: " + elseNumber);
        if (needElseIf) System.out.println("if-elseif-else num: " + elseIfNumber);
    }

    public boolean keyJudge(String line){
        return line.substring(0, 1).matches(charMatches) ||
                line.substring(line.length() - 1, line.length()).matches(charMatches);
    }
}
