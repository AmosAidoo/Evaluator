package solve;

public class Main {

    public static void main(String[] args) {
        var x = Evaluator.eval("4*cos(x)", 87);
        var y = Evaluator.eval("5x + 7*sin(x) + 29", 4);
        var z = Evaluator.eval("4*cos(2x)*sin(x)", 2);
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
    }
    
}
