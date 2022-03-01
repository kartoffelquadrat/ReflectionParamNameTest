# Reflection Parameter Name Test

Testing access to debugger information via reflection.

## About

When comiled to binaries / JAR, by default parameter names are by default wiped. This means a reflective access to a JAR-provided class does not provide information on parameter names.  
The java compiler provides a debugging option to include parameter names. This a minimal test project to verify correct inclusion of parameter name information in precompiled artifact.

## Usage

 * Dependencies:  
Ensure the [BookStoreInternals jar](https://github.com/kartoffelquadrat/BookStoreInternals) is on the classpath.  
 * Compile:  
```javac Reflector.java```
 * Execute:  
```java Reflector```

## Output

Output, if BookStore is compiled with instructions to include debug info:  

```xml
    <plugin>
	<artifactId>maven-compiler-plugin</artifactId>
	<version>3.8.1</version>
	<configuration>
	    <source>1.8</source>
	    <target>1.8</target>
	    <!-- Inlcude parameter names so artifacts from this library are easier to acces by reflection-->
	    <fork>true</fork>
	    <compilerArgs>
		<arg>-parameters</arg>
		<arg>-g:source,lines,vars</arg>
	    </compilerArgs>
	</configuration>
    </plugin>
```

```
Reflective access on all methods of AssortmentImpl:
 + java.lang.String:toString()
 + eu.kartoffelquadrat.bookstoreinternals.GlobalStock:getInstance()
 + int:getStock(java.lang.String:city, java.lang.Long:isbn)
 + void:setStock(java.lang.String:city, java.lang.Long:isbn, java.lang.Integer:amount)
 + java.util.Collection:getStoreLocations()
 + java.util.Map:getEntireStoreStock(java.lang.String:city)
 - void:populateWithDummyData()
```

Output, if BookStore is compiled without extra compiler instructions:  

```
Reflective access on all methods of AssortmentImpl:
 + java.lang.String:toString()
 + eu.kartoffelquadrat.bookstoreinternals.GlobalStock:getInstance()
 + int:getStock(java.lang.String:_?_, java.lang.Long:_?_)
 + void:setStock(java.lang.String:_?_, java.lang.Long:_?_, java.lang.Integer:_?_)
 + java.util.Collection:getStoreLocations()
 + java.util.Map:getEntireStoreStock(java.lang.String:_?_)
 - void:populateWithDummyData()
```
