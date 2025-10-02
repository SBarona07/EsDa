package Clase7;

import java.util.Stack;

/**
 * Clase que valida si los paréntesis, corchetes y llaves en una expresión están balanceados y correctamente anidados.
 */
public class ParenthesisValidator {
    // No se requieren atributos adicionales

    /**
     * Constructor vacío. No requiere inicialización especial.
     */
    public ParenthesisValidator() {
        // Sin inicialización especial
    }

    /**
     * Valida si una expresión tiene los paréntesis, corchetes y llaves correctamente balanceados y anidados.
     * 
     * @param expression La expresión a validar.
     * @return true si la expresión es válida, false en caso contrario.
     */
    public boolean validate(String expression) {
        Stack<String> stackValidator = new Stack<>(); // Pila para símbolos de apertura
        String[] tokens = expression.split(""); // Divide la expresión en caracteres individuales
        boolean isValid = true; // Bandera para indicar si la expresión es válida

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            switch (token) {
                // Si es símbolo de apertura, lo apila
                case "(":
                case "[":
                case "{":
                    stackValidator.push(token);
                    break;
                // Si es símbolo de cierre, verifica si corresponde con el último abierto
                case ")":
                    if (stackValidator.isEmpty() || !stackValidator.pop().equals("(")) {
                        isValid = false; // No corresponde o no hay apertura previa
                    }
                    break;
                case "]":
                    if (stackValidator.isEmpty() || !stackValidator.pop().equals("[")) {
                        isValid = false;
                    }
                    break;
                case "}":
                    if (stackValidator.isEmpty() || !stackValidator.pop().equals("{")) {
                        isValid = false;
                    }
                    break;
                default:
                    // Ignorar otros caracteres
                    break;
            }
            // Si ya es inválido, termina el ciclo
            if (!isValid) break;
        }
        // Al final, la pila debe estar vacía y no haber sido marcado como inválido
        return isValid && stackValidator.isEmpty();
    }
}