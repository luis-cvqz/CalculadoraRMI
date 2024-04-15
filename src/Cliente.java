import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class Cliente {
    public static void main(String[] args) {
        int puerto = 4545;
        String servidor = "localhost";

        try {
            Calculadora calculadora = (Calculadora) Naming.lookup("rmi://" + servidor + ":" + puerto + "/Calculadora");

            while (true) {
                String opt = JOptionPane.showInputDialog(
                    "Calcular\n" + 
                    "Suma............. (1)\n" +
                    "Resta............ (2)\n" +
                    "Multiplicación... (3)\n" +
                    "División......... (4)\n" +
                    "Cancelar para salir"
                );

                if (opt == null) {
                    break;
                }

                int a = Integer.parseInt(JOptionPane.showInputDialog("Igrese a"));
                int b = Integer.parseInt(JOptionPane.showInputDialog("Igrese b"));

                switch (opt) {
                    case "1":
                        JOptionPane.showMessageDialog(null, a + " + " + b + " = " + calculadora.sum(a, b));
                        break;
                    case "2":
                        JOptionPane.showMessageDialog(null, a + " - " + b + " = " + calculadora.res(a, b));
                        break;    
                    case "3":
                        JOptionPane.showMessageDialog(null, a + " * " + b + " = " + calculadora.mul(a, b));
                        break;
                    case "4":
                        JOptionPane.showMessageDialog(null, a + " / " + b + " = " + calculadora.div(a, b));
                        break;
                }
            }
        } catch (RemoteException | NotBoundException ex ) {
            JOptionPane.showMessageDialog(null, "No se pudo conectarl al servidor:\n" + ex);
        } catch (MalformedURLException ex ) {
            JOptionPane.showMessageDialog(null, "La URL está en formato incorrecto:\n" + ex);
        }
    }
}
