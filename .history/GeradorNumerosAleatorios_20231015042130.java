import java.util.Random;

public class GeradorNumerosAleatorios {
    public static void main(String[] args) {
        // Crie um objeto Random para gerar números aleatórios
        Random random = new Random();

        int somaTotal = 0;

        // Gere e exiba 5 números aleatórios positivos e some os dígitos
        for (int i = 0; i < 5; i++) {
            int numeroAleatorio = random.nextInt(Integer.MAX_VALUE);
            int somaDigitos = 0;

            // Calcule a soma dos dígitos do número
            while (numeroAleatorio > 0) {
                somaDigitos += numeroAleatorio % 10;
                numeroAleatorio /= 10;
            }

            somaTotal += somaDigitos;
            System.out.println("Número Aleatório #" + (i + 1) + ": " + numeroAleatorio);
            System.out.println("Soma dos Dígitos: " + somaDigitos);
        }

        // Exiba a soma total dos dígitos
        System.out.println("Soma Total dos Dígitos: " + somaTotal);
    }
}
