package ai;

import com.mojang.mario.Agents.IAgent;
import com.mojang.mario.Environments.IEnvironment;

/**
 * Created by IntelliJ IDEA.
 * User: julian
 * Date: Apr 28, 2009
 * Time: 2:09:42 PM
 */
public class SimpleMLPAgent implements IAgent, Evolvable {

    private String name = "SimpleMLPAgent";
    final MLP mlp;
    final int numberOfOutputs = 6;
    final int numberOfInputs = 10;

    public SimpleMLPAgent () {
        mlp = new MLP (numberOfInputs, 6, numberOfOutputs);
    }

    public SimpleMLPAgent (MLP mlp) {
        this.mlp = mlp;
    }

    public Evolvable getNewInstance() {
        return new SimpleMLPAgent(mlp.getNewInstance());
    }

    public Evolvable copy() {
        return new SimpleMLPAgent (mlp.copy ());  //To change body of implemented methods use File | Settings | File Templates.
    }

    public IAgent reset() {
        mlp.reset ();
        return this;
    }

    public void mutate() {
        mlp.mutate ();
    }

    public boolean[] GetAction(IEnvironment observation) {
        double[] inputs = new double[numberOfInputs];
        byte[][] scene = observation.getLevelSceneObservation();
        int[][] enemies = observation.getEnemiesObservation();
        double[] outputs = mlp.propagate (inputs);
        boolean[] action = new boolean[numberOfOutputs];
        for (int i = 0; i < action.length; i++) {
            action[i] = outputs[i] > 0;    
        }
        return action;
    }

    public AGENT_TYPE getType() {
        return AGENT_TYPE.HUMAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;    
    }
}
