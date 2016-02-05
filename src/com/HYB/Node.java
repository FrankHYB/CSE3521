package com.HYB;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

/**
 * Created by heyub_000 on 2/4/2016.
 */

public class Node {
    private List<Integer> state;
    private Node parent;
    public enum  actions {UP,DOWN,LEFT,RIGHT}; //Actions are based on zero point
    private Map<actions,Boolean> validActions;
    private int depth;
    private int costToGo;
    private int zeroPos; //Store the current zero state, it will initialize in setActions method
    private int hash; //Using to compare with goal state

    public void Node(List<Integer> _state,Node _parent,int _depth,int _costToGo){
        state=new ArrayList<Integer>(_state);
        parent=_parent;
        validActions=new HashMap<actions,Boolean>();
        depth=_depth;
        costToGo=_costToGo;
        hash=this.state.hashCode();
    }

    public List<Integer> getState(){
        return this.state;
    }
    public Node getParent(){
        return this.parent;
    }
    public int getDepth(){
        return this.depth;
    }
    public int getCostToGo(){
        return this.costToGo;
    }
    /* Initialize the valid actions of current state*/
    public void setActions (Map<actions,Boolean> validActions){
        zeroPos=findIndexOfZero();
        //In the middle
        if(zeroPos==4){
            validActions.put(actions.UP,true);
            validActions.put(actions.DOWN,true);
            validActions.put(actions.LEFT,true);
            validActions.put(actions.RIGHT,true);
        }else if(zeroPos%2!=0){
            //In the middle row
            if(zeroPos==3||zeroPos==5){
                 validActions.put(actions.DOWN,true);
                 validActions.put(actions.UP,true);
                 if(zeroPos==3)
                     validActions.put(actions.RIGHT,true);
                 else
                     validActions.put(actions.LEFT,true);
            }else{
                // In the middle column
                 validActions.put(actions.LEFT,true);
                 validActions.put(actions.RIGHT,true);
                 if(zeroPos==1)
                     validActions.put(actions.DOWN,true);
                 else
                     validActions.put(actions.UP,true);
            }
        }else{
                if(zeroPos==0||zeroPos==2){
                    validActions.put(actions.DOWN,true);
                    if(zeroPos==0){
                        validActions.put(actions.RIGHT,true);
                    }else{
                        validActions.put(actions.LEFT,true);
                    }
                }else{
                    validActions.put(actions.UP,true);
                    if(zeroPos==6){
                        validActions.put(actions.RIGHT,true);
                    }else{
                        validActions.put(actions.LEFT,true);
                    }
                }
        }
    }

    // Find childNode of current Node
    public List<List<Integer>> getChildNode(){
        List<List<Integer>> children=new ArrayList<List<Integer>>();
        List<Integer> child=new ArrayList<Integer>(this.state);
        for(Map.Entry<actions,Boolean> entry:validActions.entrySet()){
            if(entry.getValue()){
                swap(child,entry.getKey());
                children.add(child);
            }
        }
        return children;
    }

    // Generate child based on valid actions
    private void swap(List<Integer> child,actions action){
        switch(action){
            case UP:
                Collections.swap(child,zeroPos,zeroPos-3);
                break;
            case DOWN:
                Collections.swap(child,zeroPos,zeroPos+3);
                break;
            case LEFT:
                Collections.swap(child,zeroPos,zeroPos-1);
                break;
            case RIGHT:
                Collections.swap(child,zeroPos,zeroPos+1);
                break;
        }
    }

    private int findIndexOfZero(){
        int result=Integer.MAX_VALUE;
        for(int i=0;i<this.state.size();i++){
            if(this.state.get(i)==0) {
                result = i;
            }
        }
        return result;
    }
}
