package com.margin.model;

public interface ModelHolder <Model extends AbstractModel>{

    default Model getModel(){
        return null;
    }

    default void setModel(Model model){

    }
}
