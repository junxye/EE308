package one;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {

    private final String[] keys =
            {"auto", "short", "int", "long", "float", "double",
             "char", "struct", "union", "enum", "typedef", "const", "case",
             "unsigned", "signed", "extern", "register", "static", "volatile",
             "void", "if", "else", "switch", "for", "do", "return",
             "while", "goto", "continue", "break", "default", "sizeof"};
    private String file;
    private StringBuilder fileContent;
}
