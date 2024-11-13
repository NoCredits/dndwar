package nl.playdnd.dasic.interpreter;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.arena.BattleMap;
import nl.playdnd.character.DnDCharacter;
import nl.playdnd.character.InlineValues;
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

    public InlineValues stats;

    public DnDCharacter character;


    public SourceCode(DnDCharacter character) {
        variables = new HashMap<String, Value>();
        labels = new HashMap<String, Integer>();
        stats = new InlineValues();
        this.character = character;
        
    }

    public SourceCode(DnDCharacter character, String source) {
        this(character);
        this.source = source;
    }


}
