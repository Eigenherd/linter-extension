package com.eigenherd.mule.linter.extension;

import java.io.IOException;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

import org.apache.maven.shared.invoker.*;

public class LinterOutputConsumer implements InvocationOutputHandler {

	protected Set<Pattern> watchPatterns;
	int watchingState = 0;
	
	protected void watchFor(Pattern pattern) {
		watchPatterns.add(pattern);
	}
	
	public LinterOutputConsumer() {
		watchPatterns = new HashSet<Pattern>();
		
		watchFor(Pattern.compile(Pattern.quote("[WARNING]  - [CRITICAL] FLOW_SUBFLOW_NAMING")));
	}
	
	@Override
	public void consumeLine(String line) throws IOException {
		System.err.println("  "+line);
		
		if (watchingState == 0) {
			if (line.startsWith("[INFO] Rule validation results"))
				watchingState = 1;
		} else if (watchingState == 1) {
			if (line.startsWith("[INFO] ****************************************************************************")) {
				watchingState = 2;
			} else {
				ArrayList<Pattern> found = new ArrayList<>();
				for (Pattern p : watchPatterns) {
					if (p.matcher(line).find()) {
						found.add(p);
					}
				}
				watchPatterns.removeAll(found);
			}
		}
	}

	public List<String> report() {
		return watchPatterns.stream().map(p -> p.toString()).collect(Collectors.toList());
	}
}
