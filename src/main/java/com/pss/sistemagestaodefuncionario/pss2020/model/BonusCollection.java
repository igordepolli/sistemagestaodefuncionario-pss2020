package com.pss.sistemagestaodefuncionario.pss2020.model;

import java.util.ArrayList;
import java.util.List;

public class BonusCollection {
    
    private final List<Bonus> listBonus;

    public BonusCollection() {
        listBonus = new ArrayList<>();
    }
    
    public void addBonus(Bonus bonus) {
        listBonus.add(bonus);
    }
    
    public void removeBonus(Bonus bonus) {
        listBonus.remove(bonus);
    }
    
    public boolean isEmpty() {
        return listBonus.isEmpty();
    }

    public List<Bonus> getListBonus() {
        return listBonus;
    }
    
}
