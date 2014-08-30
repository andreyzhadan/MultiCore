package com.zhadan.golovach.lesson15.sum;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewzhadan on 8/21/14.
 */
public class SumKernel extends UntypedActor {
    private final ActorRef master;
    private ActorRef slave1;
    private ActorRef slave2;
    private List<Integer> state = new ArrayList<>(2);

    public SumKernel(ActorRef master) {
        this.master = master;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof int[]) {
            int from = ((int[]) msg)[0];
            int to = ((int[]) msg)[1];
            if (to - from > 10) {
                slave1 = getContext().actorOf(Props.create(SumKernel.class, getSelf()));
                slave1.tell(new int[]{from, (from + to) >>> 1}, getSelf());

                slave2 = getContext().actorOf(Props.create(SumKernel.class, getSelf()));
                slave2.tell(new int[]{((from + to) >>> 1) + 1, to}, getSelf());
            } else {
                master.tell(calc(from, to), getSelf());
            }
        } else if (msg instanceof Integer) {
            state.add((Integer) msg);
            if (state.size() == 2) {
                master.tell(state.get(0) + state.get(1), getSelf());
            }
            slave1.tell(PoisonPill.getInstance(), getSelf());
            slave2.tell(PoisonPill.getInstance(), getSelf());
        } else {
            unhandled(msg);
        }
    }

    private int calc(int from, int to) {
        int result = 0;
        for (int k = from; k < to; k++) {
            if (k % 3 != 0 && k % 5 != 0) {
                result++;
            }
        }
        return result;
    }
}
