package com.eigenherd.mule.linter.extension.rules;

import java.util.*;

import com.eigenherd.mule.linter.extension.components.SetPayloadComponent;

import com.avioconsulting.mule.linter.model.Application;
import com.avioconsulting.mule.linter.model.configuration.MuleComponent;
import com.avioconsulting.mule.linter.model.rule.RuleSeverity;
import com.avioconsulting.mule.linter.model.rule.RuleType;
import com.avioconsulting.mule.linter.model.rule.RuleViolation;

public class MultilineDataWeaveRule extends JavaRule {

	public static final String RULE_ID = "MULTILINE_DATAWEAVE";
	public static final String RULE_NAME = "No Multiline Expression for Set Payload etc.";
	public static final String RULE_VIOLATION_MESSAGE = "Use Transform Message component for multiline expressions!";

    public MultilineDataWeaveRule() {
        super(RULE_ID, RULE_NAME, RuleSeverity.MINOR, RuleType.CODE_SMELL);
    }

    @Override
    public List<RuleViolation> execute(Application application) {
        List<MuleComponent> components = application.findComponents(SetPayloadComponent.IDENTIFIER.getName(), SetPayloadComponent.IDENTIFIER.getNamespaceURI());
        List<RuleViolation> violations = new ArrayList<RuleViolation>();
        components.forEach(e -> { 
        	SetPayloadComponent it = (SetPayloadComponent) e;
        	if(it.getValue().contains("\n")) {
        		violations.add(new RuleViolation(this, it.getFile().getPath(), it.getLineNumber(), RULE_VIOLATION_MESSAGE));
        	} 
    	});
        return violations;
    }


}
