package one;

import java.util.Scanner;

public class KeywordExtraction {

    public static void main(String[] args) {

        Levels levels = new Levels();
        String file;
        int level;
        Scanner in = new Scanner(System.in);
        file = in.nextLine();
        level = in.nextInt();

        switch (level){
            case 1: levels.levelOne(file);break;
            case 2: levels.levelTwo(file);break;
            case 3: levels.levelThree(file);break;
            case 4: levels.levelFour(file);break;
        }
    }
}
