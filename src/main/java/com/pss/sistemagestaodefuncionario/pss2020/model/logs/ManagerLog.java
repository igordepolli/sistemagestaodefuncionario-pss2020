package com.pss.sistemagestaodefuncionario.pss2020.model.logs;

public class ManagerLog {
    
    private ILog log;

    public ManagerLog(ILog log) {
        this.log = log;
    }

    public void setLog(ILog log) {
        this.log = log;
    }
    
    public void write(LogElement logElement) throws Exception {
        this.log.write(logElement);
    }
}
