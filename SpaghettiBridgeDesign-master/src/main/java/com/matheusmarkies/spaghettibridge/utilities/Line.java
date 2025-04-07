/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.utilities;

/**
 *
 * @author Matheus Markies
 */
/**
 * A classe Line representa uma reta na forma padrão da equação linear: a*x + b*y + c = 0.
 * Os coeficientes a, b e c definem a inclinação e a posição da reta no plano cartesiano.
 */
public class Line {
    // Coeficientes da equação da reta
    double a;
    double b;
    double c;

    /**
     * Construtor que inicializa os coeficientes da reta.
     *
     * @param a coeficiente associado a x
     * @param b coeficiente associado a y
     * @param c termo constante
     */
    public Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Método estático que gera a equação da reta passando dois pontos.
     * Os pontos são representados pela classe Vector2D e a reta é definida a partir da
     * fórmula de determinante, onde:
     *   a = (y1 - y2)
     *   b = (x2 - x1)
     *   c = (x1*y2 - y1*x2)
     *
     * Isso garante que a reta passe pelos dois pontos informados.
     *
     * @param a primeiro ponto (Vector2D) da reta
     * @param b segundo ponto (Vector2D) da reta
     * @return uma instância de Line que representa a reta que passa pelos pontos a e b
     */
    public static Line getLineEquation(Vector2D a, Vector2D b) {
        // Inicializa a linha com coeficientes nulos
        Line line = new Line(0, 0, 0);

        // Calcula o coeficiente "a" da equação (diferença das ordenadas)
        line.a(-b.y() + a.y());
        // Calcula o coeficiente "b" da equação (diferença das abscissas)
        line.b(b.x() - a.x());
        // Calcula o termo constante "c" usando o determinante dos pontos
        line.c(a.x() * b.y() - a.y() * b.x());

        return line;
    }

    /**
     * Calcula a inclinação (m) da reta a partir dos coeficientes.
     * A inclinação é dada por: m = -(a / b)
     *
     * @return o valor da inclinação da reta
     */
    public double getM(){
        return -(this.a / this.b);
    }

    /**
     * Calcula o intercepto (n) da reta no eixo y.
     * O intercepto é dado por: n = -(c / b)
     *
     * @return o valor do intercepto da reta
     */
    public double getN(){
        return -(this.c / this.b);
    }

    /**
     * Getter para o coeficiente 'a'.
     *
     * @return o valor de a
     */
    public double a() {
        return a;
    }

    /**
     * Setter para o coeficiente 'a'.
     *
     * @param a novo valor para o coeficiente a
     */
    public void a(double a) {
        this.a = a;
    }

    /**
     * Getter para o coeficiente 'b'.
     *
     * @return o valor de b
     */
    public double b() {
        return b;
    }

    /**
     * Setter para o coeficiente 'b'.
     *
     * @param b novo valor para o coeficiente b
     */
    public void b(double b) {
        this.b = b;
    }

    /**
     * Getter para o coeficiente 'c'.
     *
     * @return o valor de c
     */
    public double c() {
        return c;
    }

    /**
     * Setter para o coeficiente 'c'.
     *
     * @param c novo valor para o coeficiente c
     */
    public void c(double c) {
        this.c = c;
    }

    /**
     * Sobrescrita do método hashCode para garantir que objetos do tipo Line possam ser usados em coleções que utilizam
     * hash, como HashMap e HashSet. O cálculo do hashCode é feito combinando os bits dos coeficientes a, b e c.
     *
     * @return o hash code para esta instância de Line
     */
    @Override
    public int hashCode() {
        int hash = 5;
        // Combinação dos bits dos coeficientes a, b e c para formar um hash único
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.a) ^ (Double.doubleToLongBits(this.a) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.b) ^ (Double.doubleToLongBits(this.b) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.c) ^ (Double.doubleToLongBits(this.c) >>> 32));
        return hash;
    }

    /**
     * Sobrescrita do método equals para comparar duas instâncias de Line.
     * Duas linhas são consideradas iguais se seus coeficientes a, b e c forem exatamente os mesmos.
     *
     * @param obj o objeto a ser comparado com esta instância
     * @return true se os objetos forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        // Verifica se os objetos são exatamente a mesma instância
        if (this == obj)
            return true;
        // Se o objeto comparado for nulo, retorna false
        if (obj == null)
            return false;
        // Verifica se os objetos pertencem à mesma classe
        if (getClass() != obj.getClass())
            return false;
        // Faz o casting e compara os coeficientes com precisão de ponto flutuante
        final Line other = (Line) obj;
        if (Double.doubleToLongBits(this.a) != Double.doubleToLongBits(other.a))
            return false;
        if (Double.doubleToLongBits(this.b) != Double.doubleToLongBits(other.b))
            return false;
        return Double.doubleToLongBits(this.c) == Double.doubleToLongBits(other.c);
    }

    /**
     * Sobrescrita do método toString para fornecer uma representação textual da linha.
     * Formata a equação da reta no formato: "Line: b*y + a*x + c = 0".
     *
     * @return a representação em String da reta
     */
    @Override
    public String toString() {
        return "Line: " + b + "y + " + a + "x +" + c + " = 0";
    }

}

/*

Comentários Gerais:

A classe Line é fundamental para a representação de equações lineares no plano.

O método estático getLineEquation calcula os coeficientes da equação da reta a partir de dois pontos, utilizando as fórmulas clássicas derivadas do determinante, o que é útil para análises estáticas em estruturas treliçadas.

Os métodos getM() e getN() extraem a inclinação e o intercepto da reta, facilitando a visualização e a interpretação geométrica.

As implementações de hashCode() e equals() garantem a correta comparação e armazenamento de objetos do tipo Line em estruturas de dados.

O método toString() fornece uma forma simples de visualizar a equação da reta, o que pode auxiliar na depuração e na apresentação dos resultados.

 */