# Eigenherd Mule Linter

## Overview

This is an extension for the `mule-linter-maven-plugin`. It provides static code analysis for Mule projects, to assist with finding bugs and ensuring the code quality standards of Eigenherd.

## Usage

### Installation
Copy the following plugin snippet from [linter-test/pom.xml](linter-test/pom.xml) into your pom.xml:
```xml
<!-- Linter Plugin -->
<plugin>
    <groupId>com.avioconsulting.mule</groupId>
    <artifactId>mule-linter-maven-plugin</artifactId>
    <version>1.1.0</version>
    ...
</plugin>
```

See also the example Mule project [linter-test](linter-test).

### Running the linter
The linter is run automatically during the Maven `validate` phase of your Mule project.

You can also start it manually using `mvn validate`.

### Output
The linter outputs its report to the console and additionally creates a JSON-formatted report file `<target>/reports/mule-linter-report.json`.

### Enabling and configuring rules
By editing `muleLinter.groovy` you can enable/disable rules and set their parameters.

For a comprehensive list of available and recommended rules see Quip.

## Development

### Defining new rules

See [src/main/java/](src/main/java/) for examples.

### Testing
Exeuting `mvn test` will run unit tests against the example Mule project [linter-test](linter-test).
