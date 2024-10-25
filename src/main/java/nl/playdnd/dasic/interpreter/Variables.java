package nl.playdnd.dasic.interpreter;

import java.util.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.dasic.value.Value;

@Getter
@Setter
public class Variables {

    private String source;
    private Map<String, Value> variables;
    private Map<String, Integer> labels;

    private BufferedReader lineIn;

    private int currentStatement;

    public Variables() {
        variables = new HashMap<String, Value>();
        labels = new HashMap<String, Integer>();

        InputStreamReader converter = new InputStreamReader(System.in);
        lineIn = new BufferedReader(converter);
    }

    public Variables(String source) {
        this();
        this.source = source;
    }

}
