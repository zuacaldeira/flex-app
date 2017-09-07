/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author zua
 */
public class FlexLogger {
    
    private boolean isOn = false;

    public FlexLogger() {}
    
    public void log(String format, Object... values) {
        if(isOn) {
            System.out.printf(format, values);
        }
    }

    public void info(String format, Object... values) {
        System.out.printf(format, values);
    }

    public void error(String format, Object... values) {
        if(isOn) {
            System.err.printf(format, values);
        }
    }
    
    public void on() {
        isOn = true;
    }
    
    public void off() {
        isOn = false;
    }
    
    public boolean isOn() {
        return isOn;
    }
}
