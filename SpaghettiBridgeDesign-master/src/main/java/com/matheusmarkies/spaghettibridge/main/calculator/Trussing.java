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
        public int hashCode() {
            int hash = 7;
            hash = 89 * hash + Objects.hashCode(this.NodeA);
            hash = 89 * hash + Objects.hashCode(this.NodeB);
            hash = 89 * hash + Objects.hashCode(this.NodeC);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            final Truss other = (Truss) obj;

            if (this.checkExistenceNode(other.NodeA)
                    && this.checkExistenceNode(other.NodeB)
                    && this.checkExistenceNode(other.NodeC))
                return true;

            return false;
        }

        @Override
        public String toString() {
            return "Truss{" + "NodeA = " + NodeA.getNodeName() + ", NodeB = " + NodeB.getNodeName() + ", NodeC = " + NodeC.getNodeName() + '}';
        }

        public boolean checkExistenceNode(Node node) {
            if (this.NodeA.equals(node) || this.NodeB.equals(node) || this.NodeC.equals(node))
                return true;
            return false;
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
                        barNodeBNodeB = barNodeB.getNodeEnd();
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
                                truss = Truss.addTrussInList(truss, new Truss(NodeA, NodeB, barNodeCNodeC));
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
        for (int k = 0; k < nodes.size(); k++)
            for (Bar bar : nodes.get(k).getConnectedBars()) {

                Node NodeA = bar.getNodeStart();
                Node NodeB = bar.getNodeEnd();

                if (!NodeA.equals(nodes.get(k))) {
                    NodeA = bar.getNodeEnd();
                    NodeB = bar.getNodeStart();
                }

                for (Bar barNodeB : NodeB.getConnectedBars()) {
                    if (!barNodeB.equals(bar)) {
                        Node barNodeBNodeB = barNodeB.getNodeStart();
                        Node barNodeBNodeC = barNodeB.getNodeEnd();

                        if (!barNodeBNodeB.equals(NodeB)) {
                            barNodeBNodeB = barNodeB.getNodeEnd();
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
                                    truss = Truss.addTrussInList(truss, new Truss(NodeA, NodeB, barNodeCNodeC));
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
