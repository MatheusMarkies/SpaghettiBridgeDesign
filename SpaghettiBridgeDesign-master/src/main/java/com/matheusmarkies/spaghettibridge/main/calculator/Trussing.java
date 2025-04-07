/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.calculator;

import java.util.ArrayList;
import java.util.Objects;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.objects.node.Node;

/**
 *
 * @author Matheus Markies

 * Classe responsável por identificar subconjuntos triangulares (trusses) em uma estrutura treliçada.
 * Uma "Truss" é um triângulo formado por três nós conectados por barras.
 */
public class Trussing {

    /**
     * Classe interna que representa um triângulo (Truss) formado por três nós conectados.
     */
    static public class Truss {

        private Node NodeA; // Primeiro nó do triângulo
        private Node NodeB; // Segundo nó do triângulo
        private Node NodeC; // Terceiro nó do triângulo

        public Node getNodeA() {
            return NodeA;
        }

        public void setNodeA(Node NodeA) {
            this.NodeA = NodeA;
        }

        public Node getNodeB() {
            return NodeB;
        }

        public void setNodeB(Node NodeB) {
            this.NodeB = NodeB;
        }

        public Node getNodeC() {
            return NodeC;
        }

        public void setNodeC(Node NodeC) {
            this.NodeC = NodeC;
        }

        /**
         * Construtor da classe Truss.
         * @param NodeA Primeiro nó do triângulo.
         * @param NodeB Segundo nó do triângulo.
         * @param NodeC Terceiro nó do triângulo.
         */
        public Truss(Node NodeA, Node NodeB, Node NodeC) {
            this.NodeA = NodeA;
            this.NodeB = NodeB;
            this.NodeC = NodeC;
        }

        /**
         * Compara se dois objetos Truss são iguais com base nos três nós.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Truss)) return false;
            Truss truss = (Truss) o;
            return getNodeA().equals(truss.getNodeA()) && getNodeB().equals(truss.getNodeB()) && getNodeC().equals(truss.getNodeC());
        }

        /**
         * Gera um código hash único com base nos três nós do triângulo.
         */
        @Override
        public int hashCode() {
            return Objects.hash(getNodeA(), getNodeB(), getNodeC());
        }

        /**
         * Retorna uma representação em string do Truss, com os nomes dos nós.
         */
        @Override
        public String toString() {
            return "Truss{" + "NodeA = " + NodeA.getNodeName() + ", NodeB = " + NodeB.getNodeName() + ", NodeC = " + NodeC.getNodeName() + '}';
        }

        /**
         * Verifica se um nó específico faz parte do triângulo atual.
         * @param node nó a verificar.
         * @return true se estiver presente, false caso contrário.
         */
        public boolean checkExistenceNode(Node node) {
            return this.NodeA.equals(node) || this.NodeB.equals(node) || this.NodeC.equals(node);
        }

        /**
         * Adiciona um Truss à lista apenas se ele ainda não estiver presente (evita duplicatas).
         * @param trussList Lista existente de trusses.
         * @param trussToAdded Truss a ser adicionado.
         * @return Lista atualizada com ou sem o novo Truss.
         */
        public static ArrayList<Truss> addTrussInList(ArrayList<Truss> trussList, Truss trussToAdded) {
            int i = 0;
            for (Truss truss : trussList) {
                if (truss.equals(trussToAdded))
                    i++;
            }
            if (i == 0) {
                trussList.add(trussToAdded);
            }
            return trussList;
        }
    }

    /**
     * Busca todos os triângulos (Trusses) possíveis conectados a um único nó.
     * Este método percorre todas as barras ligadas ao nó e busca ciclos de três nós fechados.
     * @param node nó de origem da busca.
     * @return lista de trusses encontrados.
     */
    static public ArrayList<Truss> TrussFinder(Node node) {
        ArrayList<Truss> truss = new ArrayList<>();

        for (Bar bar : node.getConnectedBars()) {

            Node NodeA = bar.getNodeStart();
            Node NodeB = bar.getNodeEnd();

            // Garante que NodeA seja o nó atual
            if (!NodeA.equals(node)) {
                NodeA = bar.getNodeEnd();
                NodeB = bar.getNodeStart();
            }

            // Itera sobre as barras conectadas ao segundo nó (NodeB)
            for (Bar barNodeB : NodeB.getConnectedBars()) {
                if (!barNodeB.equals(bar)) {
                    Node barNodeBNodeB = barNodeB.getNodeStart();
                    Node barNodeBNodeC = barNodeB.getNodeEnd();

                    if (!barNodeBNodeB.equals(NodeB)) {
                        barNodeBNodeC = barNodeB.getNodeStart();
                    }
                    // Itera sobre as barras conectadas ao terceiro nó
                    for (Bar barNodeC : barNodeBNodeC.getConnectedBars()) {
                        if (!barNodeC.equals(bar) && !barNodeC.equals(barNodeB)) {
                            Node barNodeCNodeC = barNodeC.getNodeStart();
                            Node barNodeCNodeD = barNodeC.getNodeEnd();

                            if (!barNodeCNodeC.equals(barNodeBNodeC)) {
                                barNodeCNodeC = barNodeC.getNodeEnd();
                                barNodeCNodeD = barNodeC.getNodeStart();
                            }
                            // Verifica se fecha o ciclo com o nó inicial
                            if (barNodeCNodeD.equals(NodeA)) {
                                Truss.addTrussInList(truss, new Truss(NodeA, NodeB, barNodeCNodeC));
                            }
                        }
                    }
                    break;
                }
            }
        }
        return truss;
    }

    /**
     * Versão do TrussFinder que varre todos os nós de uma estrutura para encontrar trusses.
     * Utiliza lógica similar ao método anterior, mas itera sobre todos os nós fornecidos.
     * @param nodes Lista de nós da estrutura treliçada.
     * @return Lista completa de trusses detectados.
     */
    static public ArrayList<Truss> TrussFinder(ArrayList<Node> nodes) {
        ArrayList<Truss> truss = new ArrayList<>();
        for (Node node : nodes)
            for (Bar bar : node.getConnectedBars()) {

                Node NodeA = bar.getNodeStart();
                Node NodeB = bar.getNodeEnd();

                if (!NodeA.equals(node)) {
                    NodeA = bar.getNodeEnd();
                    NodeB = bar.getNodeStart();
                }

                for (Bar barNodeB : NodeB.getConnectedBars()) {
                    if (!barNodeB.equals(bar)) {
                        Node barNodeBNodeB = barNodeB.getNodeStart();
                        Node barNodeBNodeC = barNodeB.getNodeEnd();

                        if (!barNodeBNodeB.equals(NodeB)) {
                            barNodeBNodeC = barNodeB.getNodeStart();
                        }
                        for (Bar barNodeC : barNodeBNodeC.getConnectedBars()) {
                            if (!barNodeC.equals(bar) && !barNodeC.equals(barNodeB)) {
                                Node barNodeCNodeC = barNodeC.getNodeStart();
                                Node barNodeCNodeD = barNodeC.getNodeEnd();

                                if (!barNodeCNodeC.equals(barNodeBNodeC)) {
                                    barNodeCNodeC = barNodeC.getNodeEnd();
                                    barNodeCNodeD = barNodeC.getNodeStart();
                                }
                                if (barNodeCNodeD.equals(NodeA)) {
                                    Truss.addTrussInList(truss, new Truss(NodeA, NodeB, barNodeCNodeC));
                                }
                            }
                        }
                        break;
                    }
                }

            }
        return truss;
    }

}

