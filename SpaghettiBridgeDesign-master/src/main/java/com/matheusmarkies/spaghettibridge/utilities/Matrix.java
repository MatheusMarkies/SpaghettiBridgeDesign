/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.utilities;

/**
 *
 * @author Matheus Markies
 *
  A classe Matrix fornece métodos utilitários para operações matriciais,
  incluindo a criação de matrizes identidade, zero e uma, multiplicação de matrizes,
  conversão de matrizes numéricas para String e inversão de matrizes utilizando
  o método de eliminação gaussiana com pivoteamento.
 */
public class Matrix {

    /**
      Gera a matriz identidade com as mesmas dimensões da matriz A.
      Em uma matriz identidade, os elementos na diagonal principal são 1 e os demais são 0.
     *
     * @param A Matriz de referência para determinar as dimensões da identidade.
     * @return Uma matriz identidade de mesma dimensão que A.
     */
    public static double[][] identity(double[][] A) {
        // Cria uma nova matriz I com o mesmo número de linhas e colunas de A.
        double[][] I = new double[A.length][A[0].length];
        // Itera por cada linha da matriz I.
        for (int i = 0; i < I.length; i++) {
            // Itera por cada coluna da matriz I.
            for (int j = 0; j < I[0].length; j++) {
                // Se a posição (i, j) está na diagonal (i == j), atribui 1.
                if (j == i) {
                    I[i][j] = 1;
                } else {
                    // Caso contrário, atribui 0.
                    I[i][j] = 0;
                }
            }
        }
        return I;
    }

    /**
      Cria uma matriz de zeros com as mesmas dimensões da matriz A.
     *
     * @param A Matriz de referência para determinar as dimensões da matriz de zeros.
     * @return Uma matriz preenchida com zeros.
     */
    public static double[][] zero(double[][] A) {
        // Cria uma nova matriz I com o mesmo número de linhas e colunas de A.
        double[][] I = new double[A.length][A[0].length];
        // Preenche todas as posições da matriz I com 0.
        for (int i = 0; i < I.length; i++)
            for (int j = 0; j < I[0].length; j++)
                I[i][j] = 0;
        return I;
    }

    /**
      Cria uma matriz de uns com as mesmas dimensões da matriz A.
     *
     * @param A Matriz de referência para determinar as dimensões da matriz de uns.
     * @return Uma matriz preenchida com uns.
     */
    public static double[][] one(double[][] A) {
        // Cria uma nova matriz I com o mesmo número de linhas e colunas de A.
        double[][] I = new double[A.length][A[0].length];
        // Preenche todas as posições da matriz I com 1.
        for (int i = 0; i < I.length; i++)
            for (int j = 0; j < I[0].length; j++)
                I[i][j] = 1;
        return I;
    }

    /**
      Realiza a multiplicação de duas matrizes numéricas A e B.
      O número de colunas de A deve ser igual ao número de linhas de B.
     *
      Algoritmo:
      1. Cria uma nova matriz de resultado com número de linhas de A e número de colunas de B.
      2. Para cada elemento da nova matriz, calcula o produto escalar da linha de A com a coluna de B.
     *
     * @param A Primeira matriz.
     * @param B Segunda matriz.
     * @return A matriz resultante da multiplicação de A por B.
     */
    public static double[][] multiply(double[][] A, double[][] B) {
        // Cria uma nova matriz com dimensões: linhas de A x colunas de B.
        double[][] newMatrix = new double[A.length][B[0].length];

        // Loop sobre cada coluna (índice i) do resultado.
        // Note que aqui a variável i percorre as colunas de B.
        for (int i = 0; i < newMatrix[0].length; i++) { // Para cada coluna do resultado
            // Loop sobre cada linha (índice j) do resultado.
            for (int j = 0; j < newMatrix.length; j++) { // Para cada linha do resultado

                // Inicializa a soma acumuladora para o elemento (j, i) da nova matriz.
                double a = 0;
                // Loop para calcular o produto escalar da j-ésima linha de A e i-ésima coluna de B.
                for (int k = 0; k < B.length; k++) {
                    // Soma o produto dos elementos correspondentes.
                    a += A[j][k] * B[k][i];
                }
                // Atribui o valor calculado ao elemento (j, i) da nova matriz.
                newMatrix[j][i] = a;
            }
        }
        return newMatrix;
    }

    /**
      Realiza a multiplicação de uma matriz numérica A por uma matriz de Strings B.
      O método cria uma representação simbólica da multiplicação, onde cada elemento resultante
      é uma string que representa a soma dos produtos dos coeficientes numéricos por termos simbólicos.
     *
      Algoritmo:
      1. Cria uma nova matriz de Strings com as mesmas dimensões baseadas em A e B.
      2. Para cada posição, concatena as expressões "coeficiente * termo" separadas por " + ".
     *
     * @param A Matriz numérica.
     * @param B Matriz de Strings.
     * @return Uma matriz de Strings representando a multiplicação simbólica de A por B.
     */
    public static String[][] multiply(double[][] A, String[][] B) {
        // Cria uma nova matriz de Strings com dimensões: linhas de A x colunas de B.
        String[][] newMatrix = new String[A.length][B[0].length];

        // Loop sobre cada coluna (índice i) da matriz B.
        for (int i = 0; i < B[0].length; i++) { // Para cada coluna do resultado
            // Loop sobre cada linha (índice j) da matriz A.
            for (int j = 0; j < A.length; j++) { // Para cada linha do resultado

                // Utiliza StringBuilder para construir a expressão simbólica para o elemento (j, i).
                StringBuilder a = new StringBuilder();
                // Loop para concatenar os termos da soma do produto escalar.
                for (int k = 0; k < B.length; k++) {
                    // Se já há conteúdo no StringBuilder, adiciona " + " como separador.
                    if (a.length() > 0) {
                        a.append(" + ").append(A[j][k]).append(" * ").append(B[k][i]);
                    } else {
                        // Se for o primeiro termo, apenas adiciona o termo sem o separador.
                        a.append(A[j][k]).append(" * ").append(B[k][i]);
                    }
                }
                // Atribui a expressão construída à posição (j, i) da nova matriz.
                newMatrix[j][i] = a.toString();
            }
        }
        return newMatrix;
    }

    /**
      Converte uma matriz numérica em uma representação String.
      Cada linha da matriz é convertida em uma string com os elementos separados por espaço,
      e as linhas são separadas por quebras de linha.
     *
     * @param A Matriz numérica a ser convertida.
     * @return Uma string que representa a matriz.
     */
    public static String toString(double[][] A) {
        StringBuilder matrix = new StringBuilder();
        // Itera sobre cada linha da matriz.
        for (double[] doubles : A) {
            // Itera sobre cada elemento na linha e adiciona à string com um espaço.
            for (int j = 0; j < A[0].length; j++) {
                matrix.append(doubles[j]).append(" ");
            }
            // Adiciona uma quebra de linha após cada linha da matriz.
            matrix.append("\n");
        }
        return matrix.toString();
    }

    /**
      Inverte uma matriz quadrada utilizando a eliminação gaussiana com pivoteamento.
      O algoritmo realiza os seguintes passos:
      1. Cria matrizes auxiliares: x para armazenar a inversa final e b, que inicialmente é a matriz identidade.
      2. Inicializa um vetor de índices para rastrear a ordem das linhas durante o pivoteamento.
      3. Realiza o processo de eliminação gaussiana para triangularizar a matriz.
      4. Efetua a substituição retroativa para calcular a inversa.

     * @param a A matriz quadrada a ser invertida.
     * @return A matriz inversa de a.
     */
    public static double[][] invert(double[][] a) {
        int n = a.length;
        // x armazenará a matriz inversa final.
        double[][] x = new double[n][n];
        // b é inicializada como a matriz identidade.
        double[][] b = new double[n][n];
        // index armazenará a ordem das linhas após pivoteamento.
        int[] index = new int[n];
        // Inicializa b como matriz identidade.
        for (int i = 0; i < n; ++i) {
            b[i][i] = 1;
        }

        // Realiza a eliminação gaussiana com pivoteamento, modificando a matriz 'a' e atualizando os índices.
        gaussian(a, index);

        // Processo de eliminação progressiva:
        // Para cada linha, a partir da primeira até a penúltima, ajusta a matriz identidade 'b'
        // para levar em conta as transformações aplicadas na matriz 'a' durante a eliminação.
        for (int i = 0; i < n - 1; ++i) {
            // Para cada linha abaixo da i-ésima, elimina a contribuição da i-ésima variável.
            for (int j = i + 1; j < n; ++j) {
                // Atualiza os elementos de b considerando o fator de eliminação armazenado em a[index[j]][i].
                for (int k = 0; k < n; ++k) {
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];
                }
            }
        }

        // Processo de substituição retroativa:
        // Calcula cada linha da matriz inversa começando da última linha e voltando para a primeira.
        for (int i = 0; i < n; ++i) {
            // Calcula o último elemento da linha i da inversa.
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            // Realiza a substituição retroativa para cada linha anterior.
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                // Subtrai as contribuições já calculadas para as variáveis posteriores.
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                // Divide pelo coeficiente diagonal para isolar a variável.
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    /**
      Realiza a eliminação gaussiana com pivoteamento parcial em uma matriz quadrada.
      O objetivo deste método é modificar a matriz 'a' para uma forma triangular superior
      e atualizar o vetor 'index' que rastreia a ordem das linhas, de modo a minimizar erros numéricos.
     *
      Passos do algoritmo:
      1. Inicializa o vetor index com a ordem natural das linhas.
      2. Calcula os maiores valores absolutos de cada linha para normalização.
      3. Para cada coluna, seleciona a linha com o maior quociente entre o valor absoluto do elemento
         e o valor máximo da linha, e troca os índices para colocar essa linha na posição corrente.
      4. Realiza a eliminação das entradas abaixo da diagonal, normalizando os fatores de eliminação.
     *
     * @param a     A matriz a ser transformada.
     * @param index Vetor que armazena a ordem das linhas após o pivoteamento.
     */
    public static void gaussian(double[][] a, int[] index) {
        int n = index.length;
        // Vetor c armazena o valor máximo (normalizador) de cada linha.
        double[] c = new double[n];

        // Inicializa o vetor index com a ordem natural.
        for (int i = 0; i < n; ++i) {
            index[i] = i;
        }

        // Para cada linha, encontra o maior valor absoluto dos elementos, que será usado para normalização.
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) {
                    c1 = c0;
                }
            }
            c[i] = c1;
        }

        // Processo de pivoteamento e eliminação:
        // Para cada coluna, determina a linha com o maior fator de pivoteamento e troca os índices.
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            // Seleciona a linha pivô comparando o quociente entre o valor absoluto do elemento e o normalizador da linha.
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]) / c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }
            // Troca as linhas no vetor index para posicionar a linha com maior pivô na posição corrente.
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            // Eliminação dos elementos abaixo do pivô.
            for (int i = j + 1; i < n; ++i) {
                // Calcula o fator de eliminação.
                double pj = a[index[i]][j] / a[index[j]][j];
                // Armazena o fator na posição correspondente, que será usado na substituição.
                a[index[i]][j] = pj;
                // Atualiza os elementos restantes da linha, subtraindo a contribuição do pivô.
                for (int l = j + 1; l < n; ++l) {
                    a[index[i]][l] -= pj * a[index[j]][l];
                }
            }
        }
    }
}
/*
Comentários Gerais:

O método identity cria uma matriz identidade baseada na dimensão da matriz A, essencial para operações de inversão e transformações.

O método zero gera uma matriz preenchida com zeros, útil para inicializações ou operações onde se deseja "zerar" valores.

O método one preenche uma matriz com uns, podendo ser utilizado em operações que requeiram uma matriz de constantes unitárias.

O método multiply(double[][], double[][]) implementa a multiplicação de matrizes pelo cálculo do produto escalar entre linhas e colunas, respeitando as regras da álgebra linear.

O método multiply(double[][], String[][]) gera uma representação simbólica da multiplicação, concatenando expressões em forma textual, útil para depuração e apresentação de fórmulas.

O método toString converte uma matriz numérica em uma string legível, facilitando a visualização do conteúdo da matriz.

O método invert utiliza eliminação gaussiana com pivoteamento parcial para calcular a inversa de uma matriz quadrada, dividindo o processo em eliminação progressiva e substituição retroativa.

O método gaussian é responsável pela triangularização da matriz com pivoteamento, garantindo maior estabilidade numérica ao lidar com matrizes com elementos de magnitudes variadas. Cada método foi projetado para ser modular e reutilizável, facilitando a análise e a depuração de algoritmos em aplicações de análise estrutural com métodos numéricos.
*/
