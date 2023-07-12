public class Prueba {
    int n = 0, t, r = 1 + 3 * 4, m;
    String hola = "Hola";
    String mundo = "Mundo";
    String saludo = hola + mundo;
    boolean a = true;
    boolean b;

    public static void main() {
        new Prueba().inicial();
    }

    void inicial() {
        int fact = 5;
        System.out.println("Factorial: " + fact + " = " + factorial(fact));
        estructuraIf();
        estructuraSwitch();
        loopFor();
        loopWhile();
        loopDowhile();
        fact += 70;
    }

    void loopFor() {
        for (int i = 0; i < 7; i++) {
            System.out.println("i = " + i);
        }
        int i = 7, j;
        double k;
        for (i = 0, j = 6, k = 0; i < 7 && j >= 0; i = i + 1, j--, k += 0.1) {
            System.out.println("i = " + i + ", j = " + j + ", k = " + k);
        }
        for (String palabra = "Hola"; !palabra.equals("Hola!!!!"); palabra += "!") {
            System.out.println(palabra);
        }
    }

    void loopWhile() {
        int i = 0;
        while (i > 0 && t != 30) {
            i++;
        }
    }

    void loopDowhile() {
        int i = 0;
        do {
            i++;
        } while (i > 0 && t != 30);
    }

    void estructuraSwitch() {
        String i = "Adios";
        switch (i) {
            case "Hola":
                System.out.println("Saludo Hola");
                break;
            case "Adios": {
                System.out.println("Saludo Adios");
                break;
            }
            case "Bienvenido":
            default:
                System.out.println("Saludo random");
        }
    }

    void estructuraIf() {
        // IF ELSE IF ELSE
        if (hola == "Hola") {
            System.out.println("Hola");
        } else if (hola == "Mundo") {
            System.out.println("Mundo");
        } else {
            System.out.println("Hola Mundo");
        }
        // IF ELSE
        if (hola == "Hola") {
            System.out.println("Hola");
        } else {
            System.out.println("Hola Mundo");
        }
        // IF ELSE IF
        if (hola == "Hola") {
            System.out.println("Hola");
        } else if (hola == "Mundo") {
            System.out.println("Mundo");
        }
        // IF
        if (hola == "Hola") {
            System.out.println("Hola");
        }
    }

    int factorial(int n) {
        if (n > 0) {
            return n * factorial(n - 1);
        }
        return 1;
    }
}