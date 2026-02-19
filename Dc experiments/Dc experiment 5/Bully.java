import java.util.ArrayList;
import java.util.List;

// Class representing a node in the distributed system
class Node {
    private int nodeId;
    private boolean isCoordinator;

    public Node(int nodeId) {
        this.nodeId = nodeId;
        this.isCoordinator = false;
    }

    public int getNodeId() {
        return nodeId;
    }

    public boolean isCoordinator() {
        return isCoordinator;
    }

    public void setCoordinator(boolean coordinator) {
        isCoordinator = coordinator;
    }

    // Method to initiate an election
    public void initiateElection(List<Node> nodes) {
        System.out.println("Node " + nodeId + " initiates election.");

        boolean higherExists = false;

        for (Node node : nodes) {
            if (node.getNodeId() > this.nodeId) {
                higherExists = true;
                node.receiveElectionMessage(this);
            }
        }

        // If no higher node responds → become coordinator
        if (!higherExists) {
            becomeCoordinator();
        }
    }

    // Method to receive election message
    public void receiveElectionMessage(Node sender) {
        System.out.println("Node " + nodeId +
                " receives election message from Node " + sender.getNodeId());

        if (this.nodeId > sender.getNodeId()) {
            System.out.println("Node " + nodeId +
                    " sends OK to Node " + sender.getNodeId());
            sender.receiveResponse(this);

            // Higher node starts its own election
            sender.setCoordinator(false);
        }
    }

    // Method to receive OK response
    public void receiveResponse(Node sender) {
        System.out.println("Node " + nodeId +
                " receives OK from Node " + sender.getNodeId());
    }

    // Become coordinator
    public void becomeCoordinator() {
        System.out.println("Node " + nodeId + " becomes the coordinator.");
        this.isCoordinator = true;
    }
}

public class Bully {
    public static void main(String[] args) {

        // Create nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        // Add to list
        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);

        // Node 3 starts election
        node3.initiateElection(nodes);
    }
}
