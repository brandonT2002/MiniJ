package Components;

public class ErrorSm {
    int line;
    int column;
    String description;

    public ErrorSm(int line, int column, String description) {
        this.line = line;
        this.column = column;
        this.description = description;
    }

    public void print() {
        System.out.println(
                "Semantic Error in Line " + line + " Column " + column + ". " + description);
    }

    public String getHTML() {
        return "\t\t\t<tr>\n\t\t\t\t<td>" + toString() + "</td>\n\t\t\t</tr>\n";
    }

    public String toString() {
        return "Error Sintáctico en Línea " + line + " Columna " + column + ". " + description;
    }
}