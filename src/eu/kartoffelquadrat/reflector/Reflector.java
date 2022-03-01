package eu.kartoffelquadrat.reflector;

import eu.kartoffelquadrat.bookstoreinternals.GlobalStockImpl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * Minimal demo application to verify access of debugger information using reflection.
 * @author Maximilian Schiedermeier
 */
class Reflector {

    /**
     * Reflective access on all operations (including parameter name info) of a class extracted from the
     * BookStoreInternals JAR (GlobalStockImpl)
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Reflective access on all methods of AssortmentImpl:");
        Class globalStockImplClass = GlobalStockImpl.class;
        for (Method method : globalStockImplClass.getDeclaredMethods()) {
            // Print method name:
            System.out.println(buildOperationString(method));
        }
    }

    /**
     * Build string for method signature (including parameter names if provided).
     */
    private static String buildOperationString(Method method) {

        // String prefix: the operation accessibility, return type, colon, name and opening parentheses.
        StringBuilder methodString = new StringBuilder("");
        methodString.append(Modifier.isPublic(method.getModifiers()) ? " + " : " - ");
        methodString.append(method.getReturnType().getTypeName()).append(":").append(method.getName()).append("(");

        // Add all parameters, comma separated.
        StringBuilder parameterBuilder = new StringBuilder();
        for (Parameter parameter : method.getParameters()) {
            parameterBuilder.append(buildParameterString(parameter)).append(", ");
        }
        // remove trailing ", "
        if(parameterBuilder.length() > 0)
            parameterBuilder.setLength(parameterBuilder.length()-2);
        methodString.append(parameterBuilder);

        // Add closing parenthesis
        methodString.append(")");
        return methodString.toString();
    }

    /**
     * Build string of form "Type:Name", where name is defaulted to "_?_" if not present.
     *
     * @param parameter as the parameter to stringify.
     */
    private static String buildParameterString(Parameter parameter) {

        // String prefix: Parameter type and a separating colon.
        String parameterString = parameter.getType().getName() + ":";

        // String suffix: Name, if present.
        parameterString += (parameter.isNamePresent() ? parameter.getName() : "_?_");
        return parameterString;
    }
}
