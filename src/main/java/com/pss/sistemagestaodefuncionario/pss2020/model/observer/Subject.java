package com.pss.sistemagestaodefuncionario.pss2020.model.observer;

import java.util.List;

public abstract class Subject {
    
    protected List<IObserver> observers;
    
    public final void registerObserver(IObserver observer) {
        observers.add(observer);
    }
    
    public final void removeObserver(IObserver observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(observer);
        }
    }
    
    protected abstract void notifyObservers();

    public final List<IObserver> getObservers() {
        return observers;
    }

}
