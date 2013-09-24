package com.actimind.diagnostic;

import com.actimind.diagnostic.date.Scheduler;
import com.actimind.diagnostic.db.Stat;
import com.actimind.diagnostic.listeners.StatProcessorListener;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SingleStatProcessor<T> {

    private StatProducer<T> producer;
    private Scheduler scheduler;
    private Scheduler paranoidScheduler = null;
    private List<StatProcessorListener> listeners = new ArrayList<StatProcessorListener>();

    private Stat previousStat;
    private Date nextDate;
    private Logger logger = Logger.getLogger(getClass());

    public SingleStatProcessor(StatProducer<T> producer,
                               Scheduler scheduler) {
        this.producer = producer;
        this.scheduler = scheduler;
        previousStat = null;
        nextDate = new Date();
        state = NORMAL;
    }

    public void setParanoidScheduler(Scheduler paranoidScheduler) {
        this.paranoidScheduler = paranoidScheduler;
    }

    private boolean isParanoid() {
        return paranoidScheduler != null;
    }

    public void checkDateAndGetStat(boolean force) {
        if (force || nextDate.before(new Date())) {
            logger.info(getName() + " collecting now...");
            collectStat(force);
            nextDate = state.getNextDate();
        }
        else {
            logger.debug(getName() + " going to collect " + nextDate);
        }
    }


    private State state;

    public void collectStat(boolean force) {
        if (force) previousStat = null;
        Stat newStat = state.collectStat();
        previousStat = newStat;
        state.switchState(newStat);
    }


    public void addListener(StatProcessorListener listener) {
        this.listeners.add(listener);
    }

    private void onNewStatCollected(Stat newStat) {
        for (StatProcessorListener listener: listeners) {
            listener.onNewStatCollected(newStat);
        }
    }

    private void onStatStateChanged(Stat newStat) {
        for (StatProcessorListener listener: listeners) {
            listener.onStatStateChanged(newStat);
        }
    }

    private void onFirstStatChanged(Stat newStat) {
        for (StatProcessorListener listener: listeners) {
            listener.onFirstStat(newStat);
        }
    }

    public String getName() {
        return producer.getName();
    }

    interface State {
        public Stat collectStat();

        public void switchState(Stat newStat);

        Date getNextDate();
    }

    /*
    TODO: previously there were 2 states - normal and paranoid one.
    Now only normal state leaves, so this state pattern implementation can be removed and NORMAL state methods may be returned to SingleStatProcessor class.
     */
    private final State NORMAL = new State() {
        public Stat collectStat() {
            Stat newStat = producer.produceStat();
            onNewStatCollected(newStat);
            if (previousStat == null) {
                onFirstStatChanged(newStat);
            }
            else if (newStat.wasChanged(previousStat)) {
                onStatStateChanged(newStat);
            }
            if (newStat.valueWasChanged(previousStat)) {
                onStatValueChanged(newStat);
            }
            return newStat;
        }

        public void switchState(Stat newStat) {
            /*if (!newStat.isNormalState() && isParanoid())
                setState(PARANOID);*/
        }

        public Date getNextDate() {
            return scheduler.getNextDate();
        }

        public String toString() {return "NORMAL";}
    };

    private void onStatValueChanged(Stat newStat) {
        for (StatProcessorListener listener: listeners) {
            listener.onStatValueChanged(newStat);
        }
    }

    /*
    private final State PARANOID = new State() {
        public Stat collectStat() {
            Stat newStat = producer.produceStat();
            if (newStat.wasChanged(previousStat)) {
                onNewStatCollected(newStat);
                onStatStateChanged(newStat);
            }
            return newStat;

        }

        public void switchState(Stat newStat) {
            if (newStat.isNormalState())
                setState(NORMAL);
        }

        public Date getNextDate() {
            return paranoidScheduler.getNextDate();
        }

        public String toString() {return "PARANOID";}
    };
    */

    private void setState(State st) {
        state = st;
        logger.info(getName() + ": switch to state " + st);
    }


}
