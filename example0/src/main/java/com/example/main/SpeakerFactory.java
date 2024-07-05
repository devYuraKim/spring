package com.example.main;

public class SpeakerFactory {

    public Speakers getSpeaker(String speakerType){
        if(speakerType == null){
            return null;
        }else if(speakerType.equalsIgnoreCase("sony")){
            return new SonySpeakers();
        }else if(speakerType.equalsIgnoreCase("bose")){
            return new BoseSpeakers();
        }
        return null;
    }
}
