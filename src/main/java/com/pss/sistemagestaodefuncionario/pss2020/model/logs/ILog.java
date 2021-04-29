package com.pss.sistemagestaodefuncionario.pss2020.model.logs;

public interface ILog {
    
    public void write(LogElement logElement) throws Exception;
    
}
