package com.eigenherd.mule.linter.extension.components;

import java.io.File;
import java.util.*;

import com.avioconsulting.mule.linter.model.configuration.ComponentIdentifier;
import com.avioconsulting.mule.linter.model.configuration.MuleComponent;

public class HttpComponent extends MuleComponent {
	public static final String NAMESPACE = "http://www.mulesoft.org/schema/mule/http";
	public final static ComponentIdentifier IDENTIFIER = new ComponentIdentifier("listener", NAMESPACE);

    public HttpComponent(String componentName, String componentNamespace, Map<String, String> attributes, File file, List<MuleComponent> children) {
        super(componentName, componentNamespace, attributes, file, children);
    }

    public static boolean accepts(ComponentIdentifier identifier) {
        return IDENTIFIER.equals(identifier);
    }

    public String getPath() {
        return getAttributeValue("path");
    }

    public String getMethod() {
        return getAttributeValue("method");
    }
}
