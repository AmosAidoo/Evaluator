package solve;

import java.util.ArrayList;
import java.util.List;

/*
    The purpose of this class to evaluate basic expressions for your
    simple computations and numerical analysis
*/
public final class Evaluator { 
    /**
     * 
     * @param String func
     * @param double x
     * @return double answer
     */
    static double eval(String func, double x){
        //Split the function into individual components
        String[] components = func.split("\\s*[\\+\\-\\*\\/]\\s*");
        
        //Get all the operators in the function
        String operators = func.replaceAll("[^\\+\\-\\*\\/]", "");
        
        //Initialize the answer by filtering the first component
        double answer = filter(components[0], x);
        //List of numbers to be added
        List<Double> waiting = new ArrayList<>();
        
        //Pointer to the current operator
        int operatorPointer = 0;
        char nextOperator;
        //Loop through the rest of the components and evaluate them
        for (int i = 1; i < components.length; i++){
            nextOperator = operators.charAt(operatorPointer++);
            double currentValue = filter(components[i], x);
            switch (nextOperator) {
                case '+':
                    waiting.add(currentValue);
                    break;
                case '-':
                    waiting.add(currentValue * -1);
                    break;
                case '*':
                    if (waiting.isEmpty()) answer*=currentValue; //For the sake of operator precedence
                    else waiting.set(waiting.size() - 1, waiting.get(waiting.size() - 1) * currentValue);
                    break;
                case '/':
                    if (waiting.isEmpty()) answer/=currentValue; //For the sake of operator precedence
                    else waiting.set(waiting.size() - 1, waiting.get(waiting.size() - 1) * currentValue);
                    break;
                default:
                    break;
            } 
        }
        
        answer = waiting.stream().map((e) -> e).reduce(answer, (accumulator, _item) -> accumulator + _item);
        return answer;
    }
    
    /**
     * 
     * @param String expr, The expression 
     * @param double x
     * @return double filteredValue
     */
    private static double filter(String expr, double x){
        double filteredValue = 0; //Identifier to contain the filtered value
        if (expr.contains("x")){
            if (isTrignometric(expr)){
                double trigAnswer = 0, valueBetweenBracket;
                //Extract the expression between the brackets
                String betweenBrackets = expr.substring(expr.indexOf("(") + 1, expr.indexOf(")"));
                
                //Evaluate the expression between the brackets
                valueBetweenBracket = Math.toRadians(filter(betweenBrackets, x));
                
                if (expr.contains("sin")){
                    trigAnswer = Math.sin(valueBetweenBracket);
                } else if (expr.contains("cos")){
                    trigAnswer = Math.cos(valueBetweenBracket);
                } else if (expr.contains("tan")) {
                    trigAnswer = Math.tan(valueBetweenBracket);
                } 
                filteredValue = trigAnswer;
            } else if (containsPower(expr)){
                String numStr = expr.split("\\s*\\^\\s*")[0], powStr = expr.split("\\s*\\^\\s*")[1];
                double number, power;
                if (isExponential(expr)){
                    power = filter(powStr, x);
                    filteredValue = Math.exp(power);
                } else {
                    number = filter(numStr, x);
                    power = filter(powStr, x);
                    filteredValue = Math.pow(number, power);
                }
            } else if(containsLog(expr)) {
                double logAnswer = 0, valueBetweenBracket;
                //Extract the expression between the brackets
                String betweenBrackets = expr.substring(expr.indexOf("(") + 1, expr.indexOf(")"));
                
                //Evaluate the expression between the brackets
                valueBetweenBracket = filter(betweenBrackets, x);
                
                if (expr.contains("log")) {
                    int base = Integer.parseInt((expr = expr.replace("log", "")).substring(0, expr.indexOf("(")));
                    logAnswer = logk(base, valueBetweenBracket);
                } else if (expr.contains("ln")) {
                    logAnswer = Math.log(valueBetweenBracket);
                }
                return logAnswer;
            }
            else {
                double coefficient = expr.replace("x", "").equals("") ? 1 : Double.parseDouble(expr.replace("x", ""));
                filteredValue = coefficient * x;
            }
        } else {
            if (expr.matches("\\d*")){
                filteredValue = Double.parseDouble(expr);
            }
        }
        return filteredValue;
    }
    
    private static boolean isTrignometric(String expr){
        return expr.contains("sin") || expr.contains("cos") || expr.contains("tan");
    }
    
    private static boolean containsPower(String expr){
        return expr.contains("^");
    }
    
    private static boolean isExponential(String expr){
        return expr.contains("e");
    }
    
    private static boolean containsLog(String expr){
        return expr.contains("log") || expr.contains("ln");
    }
    
    private static double logk(int oldBase, double n) {
        return Math.log10(n) / Math.log10(oldBase);
    }
}
