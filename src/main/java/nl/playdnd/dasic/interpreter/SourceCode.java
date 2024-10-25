package nl.playdnd.dasic.interpreter;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.dasic.statement.Statement;
import nl.playdnd.dasic.value.Value;

@Getter
@Setter
public class SourceCode {

    private String source; //String met DasicAI code
    private Map<String, Value> variables; //alle variabelen met hun waarde
    private Map<String, Integer> labels; //labels in code met index of de statement na de label

    private List <Statement> statements;

    private int currentStatement; //pointer naar huidige statement

    public SourceCode() {
        variables = new HashMap<String, Value>();
        labels = new HashMap<String, Integer>();
    }

    public SourceCode(String source) {
        this();
        this.source = source;
    }

}
