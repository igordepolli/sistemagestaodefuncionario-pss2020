package com.pss.sistemagestaodefuncionario.pss2020.model.observer;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import java.util.List;

public interface IObserver {

    public void update(List<Employee> employees);

}
