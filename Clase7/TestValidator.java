package Clase7;

public class TestValidator {
    public static void main(String[] args) {
        // Se crea una instancia del validador de paréntesis
        ParenthesisValidator validator = new ParenthesisValidator();

        // Lista de expresiones que deberían ser válidas (balanceadas y bien anidadas)
        String[] validCases = {
            "(a + b) * (c + d)", // Paréntesis correctamente balanceados
            "((a + b) * (c + d))", // Paréntesis anidados correctamente
            "()", // Paréntesis simples
            "()[]{}", // Paréntesis, corchetes y llaves balanceados
            "{[()]}", // Todos los tipos correctamente anidados
            "", // Cadena vacía (considerada válida)
            "public void method() { if (true) { return; } }" // Ejemplo de código con símbolos balanceados
        };

        // Lista de expresiones que deberían ser inválidas (errores de balanceo o anidamiento)
        String[] invalidCases = {
            "(a + b * (c + d)", // Falta un cierre de paréntesis
            ")(a + b)(", // Paréntesis en orden incorrecto
            "(", // Paréntesis sin cerrar
            ")", // Paréntesis sin abrir
            "(()", // Paréntesis sin cerrar
            "())", // Paréntesis de cierre extra
            "(()))", // Paréntesis de cierre extra
            "((())", // Paréntesis de apertura extra
            "())(", // Orden incorrecto
            "(()()(()", // Faltan cierres
            "(]", // Tipos de símbolos mezclados incorrectamente
            "([)]", // Anidamiento incorrecto
            "{[(])}" // Orden de cierre incorrecto
        };

        // Prueba de los casos válidos
        System.out.println("Testing valid cases:");
        for (String expr : validCases) {
            boolean expected = true; // Se espera que el resultado sea true
            boolean executed = validator.validate(expr); // Se ejecuta el validador
            System.out.println(expr); // Muestra la expresión analizada
            System.out.println("Expected: " + expected); // Muestra el resultado esperado
            System.out.println("Executed: " + executed); // Muestra el resultado obtenido
            System.out.println("-----------------------------"); // Línea divisoria
        }

        // Prueba de los casos inválidos
        System.out.println("\nTesting invalid cases:");
        for (String expr : invalidCases) {
            boolean expected = false; // Se espera que el resultado sea false
            boolean executed = validator.validate(expr); // Se ejecuta el validador
            System.out.println(expr); // Muestra la expresión analizada
            System.out.println("Expected: " + expected); // Muestra el resultado esperado
            System.out.println("Executed: " + executed); // Muestra el resultado obtenido
            System.out.println("-----------------------------"); // Línea divisoria
        }
    }
}