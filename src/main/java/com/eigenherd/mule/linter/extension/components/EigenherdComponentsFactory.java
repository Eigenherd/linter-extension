package com.eigenherd.mule.linter.extension.components;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import com.avioconsulting.mule.linter.model.configuration.ComponentIdentifier;
import com.avioconsulting.mule.linter.model.configuration.MuleComponent;
import com.avioconsulting.mule.linter.spi.ComponentsFactory;


public class EigenherdComponentsFactory implements ComponentsFactory {

	@SuppressWarnings({ "rawtypes" })
	private static Class[] componentClasses = {HttpComponent.class, SetPayloadComponent.class};
	
	private static Optional<Class> findComponentClass(ComponentIdentifier componentIdentifier) {
    	return Arrays.stream(componentClasses).filter(c -> {
    		try {
				@SuppressWarnings("unchecked")
				Method method = c.getMethod("accepts", ComponentIdentifier.class);
				return (boolean) method.invoke(null, componentIdentifier);
			} catch (Exception e) {
	    		return false;
			}
    	}).findFirst();
	};

    @Override
    public Set<ComponentIdentifier> registeredComponents() {
        return null;
    }

	@Override
    public boolean hasComponentFor(ComponentIdentifier componentIdentifier) {
    	return findComponentClass(componentIdentifier).isPresent();
    }

    @Override
    public MuleComponent getComponentFor(ComponentIdentifier componentIdentifier, Map<String, String> attributes, File file) {
        return getComponentFor(componentIdentifier, attributes, file, null);
    }

    @Override
	public MuleComponent getComponentFor(ComponentIdentifier componentIdentifier, Map<String, String> attributes, File file, List<MuleComponent> children) {
    	
    	Optional<Class> optional = findComponentClass(componentIdentifier);
    	try {
			Constructor cons = optional.get().getConstructor(String.class, String.class, Map.class, File.class, List.class);
			return (MuleComponent) cons.newInstance(componentIdentifier.getName(), componentIdentifier.getNamespaceURI(), attributes, file, children);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
    }
}
