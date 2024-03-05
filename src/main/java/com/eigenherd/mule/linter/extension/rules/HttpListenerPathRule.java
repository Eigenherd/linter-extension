package com.eigenherd.mule.linter.extension.rules;

import java.util.*;

import com.eigenherd.mule.linter.extension.components.HttpComponent;

import com.avioconsulting.mule.linter.model.Application;
import com.avioconsulting.mule.linter.model.configuration.MuleComponent;
import com.avioconsulting.mule.linter.model.rule.RuleSeverity;
import com.avioconsulting.mule.linter.model.rule.RuleType;
import com.avioconsulting.mule.linter.model.rule.RuleViolation;

public class HttpListenerPathRule extends JavaRule {

	public static final String RULE_ID = "HTTP_LISTENER_API_PATH";
	public static final String RULE_NAME = "HTTP Listener API Path";
	public static final String RULE_VIOLATION_MESSAGE = "HTTP Listener API Path must be \"/api/*\"";

    public HttpListenerPathRule() {
        super(RULE_ID, RULE_NAME, RuleSeverity.MINOR, RuleType.CODE_SMELL);
    }

    @Override
    public List<RuleViolation> execute(Application application) {
        List<MuleComponent> httpComponents = application.findComponents(HttpComponent.IDENTIFIER.getName(), HttpComponent.IDENTIFIER.getNamespaceURI());
        List<RuleViolation> violations = new ArrayList<RuleViolation>();
        httpComponents.forEach(e -> { 
        	HttpComponent it = (HttpComponent) e;
        	if(it.getPath() != "/api/*") {
        		violations.add(new RuleViolation(this, it.getFile().getPath(), it.getLineNumber(), RULE_VIOLATION_MESSAGE));
        	} 
    	});
        return violations;
    }


}
