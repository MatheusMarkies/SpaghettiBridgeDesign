/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.utilities;

import java.io.Serializable;

/**
 *
 * @author Matheus Markies
 */
/**
 * A classe Vector2D representa um vetor bidimensional e implementa a interface Serializable
 * para permitir sua serialização. Ela fornece métodos para operações matemáticas comuns
 * em vetores, como adição, subtração, multiplicação, normalização, cálculo de distância,
 * produto escalar e outros, facilitando a manipulação e análise de vetores em 2D.
 */
public class Vector2D implements Serializable {

    // Coordenadas X e Y do vetor.
    private double X;
    private double Y;

    /**
     * Construtor da classe Vector2D que inicializa o vetor com os valores informados.
     *
     * @param X valor da coordenada X do vetor.
     * @param Y valor da coordenada Y do vetor.
     */
    public Vector2D(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    /**
     * Atualiza o valor da coordenada Y do vetor.
     *
     * @param Y novo valor para a coordenada Y.
     */
    public void y(double Y) {
        this.Y = Y;
    }

    /**
     * Atualiza o valor da coordenada X do vetor.
     *
     * @param X novo valor para a coordenada X.
     */
    public void x(double X) {
        this.X = X;
    }

    /**
     * Retorna o valor da coordenada Y do vetor.
     *
     * @return valor de Y.
     */
    public double y() {
        return Y;
    }

    /**
     * Retorna o valor da coordenada X do vetor.
     *
     * @return valor de X.
     */
    public double x() {
        return X;
    }

    /**
     * Realiza a adição de dois vetores.
     * A soma é feita componente a componente.
     *
     * @param a primeiro vetor.
     * @param b segundo vetor.
     * @return um novo vetor resultante da soma de a e b.
     */
    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.x() + b.x(), a.y() + b.y());
    }

    /**
     * Realiza a subtração de dois vetores.
     * A subtração é feita componente a componente.
     *
     * @param a vetor de onde será subtraído.
     * @param b vetor que será subtraído.
     * @return um novo vetor resultante da subtração de b de a.
     */
    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.x() - b.x(), a.y() - b.y());
    }

    /**
     * Realiza a multiplicação componente a componente de dois vetores.
     * Essa operação multiplica o X de um vetor pelo X do outro, e o mesmo para Y.
     *
     * @param a primeiro vetor.
     * @param b segundo vetor.
     * @return um novo vetor resultante da multiplicação componente a componente de a e b.
     */
    public static Vector2D multiply(Vector2D a, Vector2D b) {
        return new Vector2D(a.x() * b.x(), a.y() * b.y());
    }

    /**
     * Multiplica um vetor por um escalar (double).
     * Cada componente do vetor é multiplicado pelo valor escalar.
     *
     * @param a vetor a ser multiplicado.
     * @param m valor escalar.
     * @return um novo vetor resultante da multiplicação de a pelo escalar m.
     */
    public static Vector2D multiply(Vector2D a, double m) {
        return new Vector2D(a.x() * m, a.y() * m);
    }

    /**
     * Multiplica um vetor por um escalar (float).
     * Cada componente do vetor é multiplicado pelo valor escalar.
     *
     * @param a vetor a ser multiplicado.
     * @param m valor escalar do tipo float.
     * @return um novo vetor resultante da multiplicação de a pelo escalar m.
     */
    public static Vector2D multiply(Vector2D a, float m) {
        return new Vector2D(a.x() * m, a.y() * m);
    }

    /**
     * Realiza a divisão componente a componente de dois vetores.
     * Divide o componente X de a pelo componente X de b, e o mesmo para Y.
     *
     * @param a vetor numerador.
     * @param b vetor denominador.
     * @return um novo vetor resultante da divisão componente a componente de a por b.
     */
    public static Vector2D divide(Vector2D a, Vector2D b) {
        return new Vector2D(a.x() / b.x(), a.y() / b.y());
    }

    /**
     * Calcula a distância euclidiana entre dois vetores (pontos) no plano 2D.
     * Utiliza a fórmula da distância: √[(x2 - x1)² + (y2 - y1)²].
     *
     * @param a primeiro vetor (ponto).
     * @param b segundo vetor (ponto).
     * @return a distância entre os pontos representados pelos vetores a e b.
     */
    public static double distance(Vector2D a, Vector2D b) {
        return Math.sqrt((a.x() - b.x()) * (a.x() - b.x()) + (a.y() - b.y()) * (a.y() - b.y()));
    }

    /**
     * Calcula o ponto médio (centro) entre dois vetores (pontos) no plano 2D.
     *
     * @param a primeiro vetor (ponto).
     * @param b segundo vetor (ponto).
     * @return um novo vetor representando o centro entre a e b.
     */
    public static Vector2D getCenter(Vector2D a, Vector2D b) {
        return new Vector2D(b.x() + (a.x() - b.x()) / 2, b.y() + (a.y() - b.y()) / 2);
    }

    /**
     * Calcula a magnitude (norma) do vetor.
     * A magnitude é dada pela raiz quadrada da soma dos quadrados das componentes.
     *
     * @param a vetor cujo comprimento será calculado.
     * @return a magnitude do vetor a.
     */
    public static double magnetude(Vector2D a) {
        return Math.sqrt(a.x() * a.x() + a.y() * a.y());
    }

    /**
     * Normaliza o vetor, retornando um novo vetor com a mesma direção, mas com magnitude 1.
     * A normalização é feita dividindo cada componente pela magnitude do vetor.
     *
     * @param a vetor a ser normalizado.
     * @return um novo vetor normalizado (unidade).
     */
    public static Vector2D normalize(Vector2D a) {
        return new Vector2D(a.x() / magnetude(a), a.y() / magnetude(a));
    }

    /**
     * Calcula o produto escalar (ou dot product) de dois vetores.
     * O produto escalar é a soma dos produtos das componentes correspondentes.
     *
     * @param a primeiro vetor.
     * @param b segundo vetor.
     * @return o valor do produto escalar entre a e b.
     */
    public static double scalar(Vector2D a, Vector2D b) {
        return (a.x() * b.x() + a.y() * b.y());
    }

    /**
     * Calcula o cosseno do ângulo entre dois vetores, utilizando o produto escalar.
     * A fórmula é: dot(a, b) = (a · b) / (|a| * |b|).
     *
     * @param a primeiro vetor.
     * @param b segundo vetor.
     * @return o cosseno do ângulo formado pelos vetores a e b.
     */
    public static double dot(Vector2D a, Vector2D b) {
        return Vector2D.scalar(a, b) / (Vector2D.magnetude(a) * Vector2D.magnetude(b));
    }

    /**
     * Calcula a distância mínima de um vetor (ponto) a uma reta.
     * Utiliza a fórmula da distância de um ponto à reta:
     * |a*x + b*y + c| / √(a² + b²), onde a, b e c são os coeficientes da equação da reta.
     *
     * @param line a reta representada pela classe Line.
     * @param vec o vetor (ponto) do qual se deseja calcular a distância.
     * @return a distância do ponto representado por vec à reta.
     */
    public static double distanceVectorToLine(Line line, Vector2D vec) {
        double distance = line.a() * vec.x() + line.b() * vec.y() + line.c();
        return Math.abs(distance) / Math.sqrt(line.a() * line.a() + line.b() * line.b());
    }

    /**
     * Restringe (clampa) as componentes do vetor para um intervalo definido entre min e max.
     * Isso garante que o valor de cada componente esteja dentro dos limites especificados.
     *
     * @param vec vetor a ser limitado.
     * @param min valor mínimo permitido.
     * @param max valor máximo permitido.
     * @return um novo vetor cujas componentes foram restringidas ao intervalo [min, max].
     */
    public static Vector2D clamp(Vector2D vec, double min, double max) {
        return new Vector2D(Math.max(min, Math.min(max, vec.x())), Math.max(min, Math.min(max, vec.y())));
    }

    /**
     * Restringe a magnitude do vetor para um intervalo definido.
     * Se a magnitude do vetor for menor que min ou maior que max, ela é ajustada para estar dentro
     * desse intervalo, mantendo a direção original.
     *
     * Algoritmo:
     * 1. Calcula a magnitude atual do vetor.
     * 2. Aplica o clamp na magnitude para obter o novo valor desejado.
     * 3. Calcula o fator de escala dividindo a nova magnitude pela magnitude original.
     * 4. Retorna um novo vetor multiplicado pelo fator de escala.
     *
     * @param vec vetor cuja magnitude será restringida.
     * @param min valor mínimo permitido para a magnitude.
     * @param max valor máximo permitido para a magnitude.
     * @return um novo vetor com a magnitude restringida.
     */
    public static Vector2D clampMagnetude(Vector2D vec, double min, double max) {
        double newMagnetude = Math.max(min, Math.min(max, magnetude(vec)));
        double z = newMagnetude / magnetude(vec);
        return new Vector2D(z * vec.x(), z * vec.y());
    }

    /**
     * Converte o vetor para uma representação em String no formato "X:<valor>,Y:<valor>".
     *
     * @return uma string que representa o vetor.
     */
    @Override
    public String toString() {
        return "X:" + x() + ",Y:" + y();
    }

    /**
     * Compara este vetor com outro objeto para verificar igualdade.
     * Dois vetores são considerados iguais se ambos tiverem as mesmas componentes X e Y.
     *
     * @param x o objeto a ser comparado com este vetor.
     * @return true se o objeto for um vetor com as mesmas componentes, false caso contrário.
     */
    @Override
    public boolean equals(Object x) {
        if (x == null) {
            return false;
        }
        if (this.getClass() != x.getClass()) {
            return false;
        }
        Vector2D that = (Vector2D) x;
        return (this.x() == that.x() && this.y() == that.y());
    }

    /**
     * Calcula o código hash do vetor, combinando os valores das componentes X e Y.
     * Este método é importante para o correto funcionamento do vetor em estruturas de dados baseadas em hash.
     *
     * @return o hash code do vetor.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.X) ^ (Double.doubleToLongBits(this.X) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.Y) ^ (Double.doubleToLongBits(this.Y) >>> 32));
        return hash;
    }
}

/*
Comentários Gerais:

A classe Vector2D encapsula a representação e operações de vetores em 2D, facilitando o trabalho com pontos e direções no plano.

Os métodos de acesso (getters e setters) para as componentes X e Y permitem manipulação direta dos valores.

Os métodos estáticos implementam operações matemáticas comuns: adição, subtração, multiplicação (tanto componente a componente quanto por escalar), divisão, cálculo da distância euclidiana, ponto médio, magnitude, normalização, produto escalar e cosseno do ângulo entre vetores.

Os métodos clamp e clampMagnetude garantem que os valores do vetor ou sua magnitude sejam limitados a um intervalo desejado, o que é útil em contextos onde se deseja restringir os valores para evitar excessos.

Os métodos equals e hashCode asseguram que objetos Vector2D possam ser comparados e utilizados em coleções que dependem de hashing, mantendo a consistência com os contratos do Java.

O método toString fornece uma representação textual simples, facilitando a depuração e a visualização dos valores do vetor. Cada método foi detalhadamente implementado para oferecer operações robustas e eficientes, essenciais para o processamento de vetores em aplicações de análise estática e modelagem de estruturas treliçadas.
*/