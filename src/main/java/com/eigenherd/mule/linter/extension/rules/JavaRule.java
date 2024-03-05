package com.eigenherd.mule.linter.extension.rules;

import java.lang.reflect.InvocationTargetException;
import com.avioconsulting.mule.linter.model.rule.Rule;
import com.avioconsulting.mule.linter.model.rule.RuleSeverity;
import com.avioconsulting.mule.linter.model.rule.RuleType;

/*
 * A wrapper for class Rule, required for Java-Groovy-compatibility.
 */
public abstract class JavaRule extends Rule{
	

	public static final String RULE_ID = "_JavaRule";
	public static final String RULE_NAME = "_JavaRule";
	public static final String RULE_VIOLATION_MESSAGE = "";
	
	public JavaRule(String ruleId, String ruleName, RuleSeverity severity, RuleType ruleType) {
		super(ruleId, ruleName, severity, ruleType);
	}

	public JavaRule(String ruleId, String ruleName) {
		super(ruleId, ruleName);
	}

	@Override
	public Object invokeMethod(String name, Object args) {
		throw new RuntimeException("invokeMethod not implemented");
	}

	@Override
	public Object getProperty(String propertyName) {
		String getterName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
		java.lang.reflect.Method method;
		try {
			method = this.getClass().getMethod(getterName);
			return method.invoke(this);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setProperty(String propertyName, Object newValue) {
		throw new RuntimeException("setProperty not implemented");
		
	}
}
