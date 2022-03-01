# Reflection Parameter Name Test

Testing access to debugger information via reflection.

## About

When comiled to binaries / JAR, by default parameter names are by default wiped. This means a reflective access to a JAR-provided class does not provide information on parameter names.  
The java compiler provides a debugging option to include parameter names. This a minimal test project to verify correct inclusion of parameter name information in precompiled artifact.

## Usage

 * Dependencies:  
Ensure the BookStoreInternals jar is on the classpath.  
```...```
 * Compiling:  
```javac Reflector.java```
 * Execution:  
```java Reflector```
