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
 */
public class Trussing {

    static public class Truss {

        private Node NodeA;
        private Node NodeB;
        private Node NodeC;

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

        public Truss(Node NodeA, Node NodeB, Node NodeC) {
            this.NodeA = NodeA;
            this.NodeB = NodeB;
            this.NodeC = NodeC;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Truss)) return false;
            Truss truss = (Truss) o;
            return getNodeA().equals(truss.getNodeA()) && getNodeB().equals(truss.getNodeB()) && getNodeC().equals(truss.getNodeC());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getNodeA(), getNodeB(), getNodeC());
        }

        @Override
        public String toString() {
            return "Truss{" + "NodeA = " + NodeA.getNodeName() + ", NodeB = " + NodeB.getNodeName() + ", NodeC = " + NodeC.getNodeName() + '}';
        }

        public boolean checkExistenceNode(Node node) {
            return this.NodeA.equals(node) || this.NodeB.equals(node) || this.NodeC.equals(node);
        }

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

    static public ArrayList<Truss> TrussFinder(Node node) {
        ArrayList<Truss> truss = new ArrayList<>();

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
                                //break;
                            }
                        }
                    }
                    break;
                }
            }

        }
        return truss;
    }

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
                                    //break;
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
