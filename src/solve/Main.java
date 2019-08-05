package solve;

public class Main {

    public static void main(String[] args) {
        var a = Evaluator.eval("4*cos(x)", 87);
        var b = Evaluator.eval("5x + 7*sin(x) + 29", 4);
        var c = Evaluator.eval("4*cos(2x)*sin(x)", 2);
        var d = Evaluator.eval("x^3 + x + 1", 2);
        var e = Evaluator.eval("e^x + 2*cos(3x) - 1", 3);
        var f = Evaluator.eval("log3(3x)", 15);
        
        System.out.println(a); //Output is 0.20934382497177587
        System.out.println(b); //Output is 49.48829531620888
        System.out.println(c); //Output is 0.139257933130305
        System.out.println(d); //Output is 11.0
        System.out.println(e); //Output is 21.060913604377944
        System.out.println(f); //Output is 3.464973520717927
    }
    
}
