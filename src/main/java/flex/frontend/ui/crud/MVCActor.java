/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.crud;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 *
 * @author zua
 */
public class MVCActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    /**
     * Create Props for an actor of this type.
     *
     * @param magicNumber The magic number to be passed to this actor’s
     * constructor.
     * @return a Props for creating this actor, which can then be further
     * configured (e.g. calling `.withDispatcher()` on it)
     */
    static Props props(Integer magicNumber) {
        // You need to specify the actual type of the returned actor
        // since Java 8 lambdas have some runtime type information erased
        return Props.create(MVCActor.class, () -> new MVCActor(magicNumber));
    }

    private final Integer magicNumber;

    
    MVCActor(Integer magicNumber) {
        this.magicNumber = magicNumber;
        receive();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> { log.info("Received String message: {}", s); })
                .matchAny( o -> { log.info("Received unknown message: {}", o); })
                .build();
    }

}
