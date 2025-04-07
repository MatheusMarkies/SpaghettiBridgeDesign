/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.calculator;

import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.objects.node.Node;
import com.matheusmarkies.spaghettibridge.objects.node.ReactionForces;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies

 * Classe responsável por montar as equações de equilíbrio de forças
 * para cada nó da estrutura, tanto na direção X quanto na direção Y.
 * Essas equações serão posteriormente resolvidas para encontrar as forças
 * internas nas barras e nas reações de apoio.
 */
public class EquationAssembler {

    /**
     * Gera as equações simbólicas de equilíbrio para um nó específico,
     * utilizando apenas os nomes das barras e funções trigonométricas genéricas (cos e sin).
     *
     * @param node Nó da estrutura a ser analisado.
     * @param isCargoReceiver Indica se o nó recebe carga (verdadeiro para nós com carga aplicada).
     * @return Um vetor de Strings com duas equações: [equação em X, equação em Y].
     */
    public static String[] getForcePerNode(Node node, boolean isCargoReceiver) {
        String[] equation = new String[]{"", ""};

        // Para cada barra conectada ao nó atual
        for (Bar bar : node.getConnectedBars()) {
            Node NodeA = bar.getNodeStart();
            Node NodeB = bar.getNodeEnd();

            // Garantir que NodeA seja o próprio nó analisado
            if (!NodeA.equals(node)) {
                NodeA = bar.getNodeEnd();
                NodeB = bar.getNodeStart();
            }

            // Montagem da parcela da força na direção X: T*cos(ângulo)
            String forceX = (bar.getBarName()) + " cos(" + NodeA.getNodeName() + "," + NodeB.getNodeName() + ")";
            if (equation[0].length() > 0) equation[0] += " + ";
            equation[0] += forceX;

            // Montagem da parcela da força na direção Y: T*sin(ângulo)
            String forceY = (bar.getBarName()) + " sin(" + NodeA.getNodeName() + "," + NodeB.getNodeName() + ")";
            if (equation[1].length() > 0) equation[1] += " + ";
            equation[1] += forceY;
        }

        // Adiciona as forças externas (reações de apoio)
        for (ReactionForces reation : node.getExternalForces()) {
            if (reation.getForceDirection().x() != 0)
                equation[0] += reation.getForceDirection().x() + reation.getReactionName();
            if (reation.getForceDirection().y() != 0)
                equation[1] += reation.getForceDirection().y() + reation.getReactionName();
        }

        // Considera a carga aplicada se for um nó que a recebe
        if (isCargoReceiver) {
            equation[1] += " = P / 2"; // Distribui metade da carga em cada nó receptor
        } else {
            equation[1] += " = 0";
        }

        equation[0] += " = 0"; // Equilíbrio na direção X

        return equation;
    }

    /**
     * Gera as equações de equilíbrio para um nó, mas agora incluindo
     * os valores reais de seno e cosseno com base na geometria da estrutura.
     *
     * @param node Nó da estrutura a ser analisado.
     * @param isCargoReceiver Indica se o nó recebe carga aplicada.
     * @return Vetor de Strings com as equações de equilíbrio considerando os ângulos reais.
     */
    public static String[] getForcePerNodeWithAngles(Node node, boolean isCargoReceiver) {
        String[] equation = new String[]{"", ""};

        // Vetor usado para definir a direção do vetor resultante
        Vector2D vectorNormal = new Vector2D(1, 1);

        for (Bar bar : node.getConnectedBars()) {
            Node NodeA = bar.getNodeStart();
            Node NodeB = bar.getNodeEnd();

            if (!NodeA.equals(node)) {
                NodeA = bar.getNodeEnd();
                NodeB = bar.getNodeStart();
            }

            // Obtém o vetor direção entre os nós conectados pela barra
            Vector2D directionVector = Vector2D.subtract(NodeB.getPosition(), NodeA.getPosition());

            // Normaliza os sinais do vetor para manter consistência dos sinais trigonométricos
            if (directionVector.x() != 0)
                directionVector.x(directionVector.x() / Math.abs(directionVector.x()));
            if (directionVector.y() != 0)
                directionVector.y(directionVector.y() / Math.abs(directionVector.y()));

            // Calcula o cosseno do ângulo entre a barra e o eixo X
            double cos = (NodeA.getPosition().x() - NodeB.getPosition().x()) / Vector2D.distance(NodeA.getPosition(), NodeB.getPosition());
            cos = Math.abs(cos) * directionVector.x();

            String forceX = (bar.getBarName()) + " cos(" + cos + ")";
            if (equation[0].length() > 0) equation[0] += " + ";
            equation[0] += forceX;

            // Calcula o seno do ângulo entre a barra e o eixo Y
            double sin = (NodeA.getPosition().y() - NodeB.getPosition().y()) / Vector2D.distance(NodeA.getPosition(), NodeB.getPosition());
            sin = Math.abs(sin) * directionVector.y();

            String forceY = (bar.getBarName()) + " sin(" + sin + ")";
            if (equation[1].length() > 0) equation[1] += " + ";
            equation[1] += forceY;
        }

        // Adiciona as forças externas no nó (reações de apoio)
        for (ReactionForces reation : node.getExternalForces()) {
            if (reation.getForceDirection().x() != 0)
                equation[0] += " + " + reation.getForceDirection().x() + reation.getReactionName();
            if (reation.getForceDirection().y() != 0)
                equation[1] += " + " + reation.getForceDirection().y() + reation.getReactionName();
        }

        // Considera a carga aplicada verticalmente no nó, se houver
        if (isCargoReceiver) {
            equation[1] += " = P / 2";
        } else {
            equation[1] += " = 0";
        }
        equation[0] += " = 0";

        return equation;
    }
}