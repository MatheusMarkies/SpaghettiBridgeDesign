/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.calculator;

import com.matheusmarkies.spaghettibridge.objects.node.Node;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
/**
 * Classe responsável pelo cálculo de ângulos entre barras conectadas em um nó central.
 * Esses ângulos são úteis para análise de estruturas treliçadas, especialmente no cálculo
 * de seno e cosseno que aparecem nas equações de equilíbrio nos nós.
 */
public class Angle {

    /**
     * Calcula o ângulo interno entre dois vetores (barras) conectados a um mesmo nó central.
     * Usa a Lei dos Cossenos para determinar o ângulo formado por três nós (como se fosse um triângulo).
     *
     * @param center Nó central comum entre os dois vetores (vértice do ângulo)
     * @param nodeA  Primeiro nó extremo conectado ao centro
     * @param nodeB  Segundo nó extremo conectado ao centro
     * @return Ângulo em graus formado entre os vetores center->nodeA e center->nodeB
     */
    public static double getTrussAngles(Node center, Node nodeA, Node nodeB) {
        // Vetores direcionais do centro até os outros dois nós
        Vector2D dirA = Vector2D.subtract(nodeA.getPosition(), center.getPosition());
        Vector2D dirB = Vector2D.subtract(nodeB.getPosition(), center.getPosition());

        // Vetor entre os dois nós extremos, formando o lado oposto do triângulo
        Vector2D closeVectorC = Vector2D.subtract(nodeB.getPosition(), nodeA.getPosition());

        // Cálculo dos módulos (comprimentos dos lados do triângulo)
        double A = Vector2D.magnetude(closeVectorC); // lado oposto ao ângulo
        double B = Vector2D.magnetude(dirB);         // lado adjacente
        double C = Vector2D.magnetude(dirA);         // lado adjacente

        // Aplicação da Lei dos Cossenos: cos(θ) = (C² + B² - A²) / (2 * C * B)
        double cos = (C * C + B * B - A * A) / (2 * C * B);

        // Conversão do ângulo de radianos para graus
        return Math.toDegrees(Math.acos(cos));
    }

    /**
     * Tenta determinar o "ângulo de início" entre dois vetores partindo de um mesmo ponto fixo.
     * Também analisa a posição relativa dos vetores para determinar o quadrante.
     *
     * @param nodeFix         Nó fixo (base dos vetores)
     * @param nodeAngleTargetA Primeiro nó formando um vetor com o fixo
     * @param nodeAngleTargetB Segundo nó formando outro vetor com o fixo
     * @return Ângulo base para iniciar alguma análise de direção (ex: ordenação angular)
     */
    public static double getTrussStartAngle(Node nodeFix, Node nodeAngleTargetA, Node nodeAngleTargetB) {
        double angle = 0;

        // Direções dos vetores em relação ao ponto fixo
        Vector2D dirA = Vector2D.subtract(nodeAngleTargetA.getPosition(), nodeFix.getPosition());
        Vector2D dirB = Vector2D.subtract(nodeAngleTargetB.getPosition(), nodeFix.getPosition());

        // Cálculo do ângulo entre cada vetor e o eixo X
        double angleA = Math.toDegrees(Math.acos(Vector2D.dot(dirA, new Vector2D(1, 0))));
        double angleB = Math.toDegrees(Math.acos(Vector2D.dot(dirB, new Vector2D(1, 0))));

        // O ângulo inicial é o menor entre os dois
        double startAngle = Math.min(angleA, angleB);

        // Soma dos vetores normalizados para estimar uma direção "média"
        Vector2D C = Vector2D.add(Vector2D.normalize(nodeAngleTargetA.getPosition()), Vector2D.normalize(nodeAngleTargetB.getPosition()));

        // Impressão para depuração
        System.out.println();
        System.out.println(C);

        // Determinação do quadrante com base nas coordenadas de C
        if (C.x() < 0 && -C.y() > 0) {
            // Terceiro Quadrante
            System.out.println("Terceiro Quadrante");
            angle = 180;
        }
        if (C.x() < 0 && -C.y() < 0) {
            // Segundo Quadrante
            System.out.println("Segundo Quadrante");
            angle = 180;
        }
        if (C.x() > 0 && -C.y() > 0) {
            // Quarto Quadrante
            System.out.println("Quarto Quadrante");
        }
        if (C.x() > 0 && -C.y() < 0) {
            // Primeiro Quadrante
            System.out.println("Primeiro Quadrante");
        }

        return angle;
    }
}

