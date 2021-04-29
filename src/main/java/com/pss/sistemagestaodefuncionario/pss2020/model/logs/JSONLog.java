package com.pss.sistemagestaodefuncionario.pss2020.model.logs;

import java.io.FileWriter;

public class JSONLog implements ILog {
    
    private FileWriter file;

    public JSONLog() throws Exception {
    }

    @Override
    public void write(LogElement logElement) throws Exception {
    }
    
}
