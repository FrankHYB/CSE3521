package com.HYB;
import java.util.*;
import com.HYB.Node.actions;
public class IDDFS {
    private static List<Integer> goal=new ArrayList<Integer>();
    private static int goalHash;
    private static List<Integer> initial;

    /* initialize goal state*/
    private static void InitializeGoal(){
        for(int i=0;i<9;i++){
            goal.add(i);
        }

        goalHash=goal.hashCode();
    }
    //Using hash value to compare with the goal state
    public static boolean compare(Node node){
        return node.getHash()==goalHash;
    }
    /* return child node corresponding to particular action, return empty if the action is not valid*/
    private static Node getChildNode(Node node,actions action){
        List<Integer> children=new ArrayList<Integer>();
        if(node.getValidActions().get(action)){
            children=new ArrayList<Integer>(node.getState());
            node.swap(children,action);
        }
        if(children.size()!=0) {
            Node child=new Node(children,node,node.getDepth()+1,node.getPathCost()+1,0);
            child.setActions();
            return child;
        }
        else {
            return null;
        }
    }


    public static void main(String[] args) {
        int[] ini={5,0,4,2,1,3,6,7,8};


    }
}
