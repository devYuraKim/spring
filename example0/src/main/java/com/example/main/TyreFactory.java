package com.example.main;

public class TyreFactory {

    public Tyres getTyre(String tyreType){
        if( tyreType== null){
            return null;
        }else if(tyreType.equalsIgnoreCase("bridgestone")){
            return new BridgeStoneTyres();
        }else if(tyreType.equalsIgnoreCase("michelin")){
            return new MichelinTyres();
        }
        return null;
    }
}
