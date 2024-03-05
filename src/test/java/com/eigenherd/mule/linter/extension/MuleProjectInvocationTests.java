package com.eigenherd.mule.linter.extension;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.maven.shared.invoker.*;

public class MuleProjectInvocationTests {
	
	@Test
	public void invokeMuleProject() {
		Invoker invoker = new DefaultInvoker();
		InvocationResult result;
		
		{
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile( new File( "pom.xml" ) );
			request.setGoals(Arrays.asList("package", "install"));
			request.addArg("-DskipTests");
			try {
				result = invoker.execute( request );
				if ( result.getExitCode() != 0 )
				{
					fail("package & install failed");
				}
			} catch (MavenInvocationException e) {
				fail(e);
			}
		}

		{
			LinterOutputConsumer consumer = new LinterOutputConsumer();
			
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile( new File( "linter-test/pom.xml" ) );
			request.setGoals(Arrays.asList("validate"));
			request.setOutputHandler(consumer);
			try {
				result = invoker.execute( request );
				if ( result.getExitCode() != 0 )
				{
					fail("validate failed");
				}
			} catch (MavenInvocationException e) {
				fail(e);
			}
			
			List<String> missingPatterns = consumer.report();
			if (!missingPatterns.isEmpty()) {
				fail("Linter failed to output the expected patterns:\n" + String.join("\n", missingPatterns.stream().map(s -> "> "+s).collect(Collectors.toList())));
			}
		}
	}
}
