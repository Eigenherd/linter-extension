package com.eigenherd.mule.linter.extension.components;

import java.io.File;
import java.util.*;

import com.avioconsulting.mule.linter.model.Namespace;
import com.avioconsulting.mule.linter.model.configuration.ComponentIdentifier;
import com.avioconsulting.mule.linter.model.configuration.MuleComponent;

public class SetPayloadComponent extends MuleComponent {
	

	public final static ComponentIdentifier IDENTIFIER = new ComponentIdentifier("set-payload", Namespace.getCORE());

    public SetPayloadComponent(String componentName, String componentNamespace, Map<String, String> attributes, File file, List<MuleComponent> children) {
        super(componentName, componentNamespace, attributes, file, children);
    }
    
    public static boolean accepts(ComponentIdentifier identifier) {
        return IDENTIFIER.equals(identifier);
    }

    public String getValue() {
        return getAttributeValue("value");
    }
}
