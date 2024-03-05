# Eigenherd Mule Linter

## Overview

This linter provides static code analysis for Mule projects, to assist with finding bugs and ensuring the code quality standards of Eigenherd.

## Usage

### Installation
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

...

### Testing
Exeuting `mvn test` will run unit tests against the example Mule project [linter-test](linter-test).
