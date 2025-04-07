/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.calculator;

import com.matheusmarkies.spaghettibridge.material.Material;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies

 * Classe responsável por realizar os cálculos de deformação de barras em uma estrutura treliçada.
 * Utiliza propriedades do material, geometria das barras e forças atuantes para estimar deformações
 * segundo a Teoria da Elasticidade (Lei de Hooke).
 */
public class Deformation {

    /**
     * Calcula a deformação (alongamento ou encurtamento) de uma barra submetida a uma força axial.
     *
     * Fórmula aplicada:
     *     ΔL = (L * F) / (E * A)
     * Onde:
     *     L = comprimento da barra (m)
     *     F = força axial na barra (N)
     *     E = módulo de elasticidade do material (Pa)
     *     A = área da seção transversal da barra (m²)
     *
     * @param material Material da barra (contém E e diâmetro do fio)
     * @param bar      Barra na qual será calculada a deformação
     * @return Deformação linear da barra (variação de comprimento em metros)
     */
    public static double getBarDeformation(Material material, Bar bar) {
        // Cálculo do raio da seção circular de cada fio
        double radius = material.getDiameter() / 2;

        // Área da seção transversal total (A = πr² * número de fios)
        double A = radius * radius * Math.PI * bar.getNumberOfWires();

        // Aplicação da fórmula da deformação axial
        double deformation = (
                Vector2D.distance(bar.getNodeStart().getPosition(), bar.getNodeEnd().getPosition()) // L
                        * bar.getBarForce()                                                                // F
        ) / (material.getElasticityModulus() * A);                                            // E * A

        return deformation;
    }

    /**
     * Realiza uma exponenciação repetida do valor fornecido, multiplicando-o por ele mesmo N vezes.
     * Obs: Não é uma função de potência tradicional, e sim uma multiplicação sucessiva.
     *
     * Exemplo: exp(2, 3) = ((2*2)*2) = 8
     *
     * @param value Valor base
     * @param times Número de vezes que o valor será multiplicado por ele mesmo
     * @return Resultado da exponenciação iterativa
     */
    public static double exp(double value, int times) {
        for (int i = 0; i < times; i++)
            value *= value;
        return value;
    }

    /**
     * Calcula a linha elástica da deformação em uma barra sob carga axial distribuída,
     * utilizando uma fórmula simbólica para a deflexão em função da posição x ao longo da barra.
     *
     * Fórmula usada:
     *     y(x) = (1/24) * (2 * P * L * x³ - P * x⁴ - P * L³)
     * Onde:
     *     P = força axial na barra (N)
     *     L = comprimento da barra (m)
     *     x = posição ao longo da barra (m)
     *
     * Obs: Esta fórmula representa uma expressão simplificada da curva elástica de uma viga e pode
     * ser usada para visualização gráfica da deformação.
     *
     * @param x     Posição ao longo da barra (em metros)
     * @param force Força axial aplicada na barra
     * @param bar   Barra onde a deflexão está sendo avaliada
     * @return Valor da linha elástica (deflexão vertical) em x
     */
    public static double getElasticLineIntegral(double x, double force, Bar bar) {
        double barLength = Vector2D.distance(bar.getNodeStart().getPosition(), bar.getNodeEnd().getPosition());

        // Debug: imprime variáveis de entrada
        System.out.println("x: " + x);
        System.out.println("L: " + barLength);
        System.out.println("F: " + force);

        // Cálculo da equação da linha elástica simbólica
        double elasticLine = (1.0 / 24.0) *
                (2 * force * barLength * Math.pow(x, 3)
                        - force * Math.pow(x, 4)
                        - force * Math.pow(barLength, 3));

        return elasticLine;
    }
}

