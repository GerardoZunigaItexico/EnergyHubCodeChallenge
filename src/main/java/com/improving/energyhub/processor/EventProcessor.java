package com.improving.energyhub.processor;

import com.improving.energyhub.bean.Event;

public class EventProcessor {
    private Event currentEventState;

    public void validateIncomingEvent(Event incomingEvent){
        if (currentEventState == null){
            currentEventState = incomingEvent;
        }else{

        }
    }
}
