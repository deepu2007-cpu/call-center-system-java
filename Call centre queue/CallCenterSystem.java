import java.util.*;

// Class representing a Call
class Call {
    private int callId;
    private String customerName;
    private String issue;

    public Call(int callId, String customerName, String issue) {
        this.callId = callId;
        this.customerName = customerName;
        this.issue = issue;
    }

    public int getCallId() {
        return callId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getIssue() {
        return issue;
    }

    @Override
    public String toString() {
        return "Call ID: " + callId +
               ", Customer: " + customerName +
               ", Issue: " + issue;
    }
}

// Class representing an Agent
class Agent {
    private int agentId;
    private boolean isBusy;

    public Agent(int agentId) {
        this.agentId = agentId;
        this.isBusy = false;
    }

    public int getAgentId() {
        return agentId;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void assignCall(Call call) {
        isBusy = true;
        System.out.println("Agent " + agentId + " is handling -> " + call);
    }

    public void completeCall() {
        isBusy = false;
        System.out.println("Agent " + agentId + " is now free.");
    }
}

// Main Call Center System
class CallCenter {
    private Queue<Call> callQueue;
    private List<Agent> agents;

    public CallCenter(int numberOfAgents) {
        callQueue = new LinkedList<>();
        agents = new ArrayList<>();

        for (int i = 1; i <= numberOfAgents; i++) {
            agents.add(new Agent(i));
        }
    }

    // Add incoming call
    public void receiveCall(Call call) {
        System.out.println("Incoming call: " + call);
        callQueue.add(call);
        processCalls();
    }

    // Process calls
    private void processCalls() {
        for (Agent agent : agents) {
            if (!agent.isBusy() && !callQueue.isEmpty()) {
                Call call = callQueue.poll();
                agent.assignCall(call);
            }
        }
    }

    // Complete call for an agent
    public void completeCall(int agentId) {
        for (Agent agent : agents) {
            if (agent.getAgentId() == agentId) {
                agent.completeCall();
                break;
            }
        }
        processCalls();
    }

    // Show waiting calls
    public void displayQueue() {
        System.out.println("\nCurrent Waiting Queue:");
        if (callQueue.isEmpty()) {
            System.out.println("No calls waiting.");
        } else {
            for (Call call : callQueue) {
                System.out.println(call);
            }
        }
    }
}

// Main class
public class CallCenterSystem {
    public static void main(String[] args) {
        CallCenter center = new CallCenter(2);

        // Incoming calls
        center.receiveCall(new Call(1, "Alice", "Billing issue"));
        center.receiveCall(new Call(2, "Bob", "Technical problem"));
        center.receiveCall(new Call(3, "Charlie", "Account login"));
        center.receiveCall(new Call(4, "David", "Payment failure"));

        center.displayQueue();

        // Completing calls
        System.out.println("\n--- Completing Calls ---");
        center.completeCall(1);

        center.displayQueue();

        center.completeCall(2);

        center.displayQueue();

        center.completeCall(1);

        center.displayQueue();
    }
}