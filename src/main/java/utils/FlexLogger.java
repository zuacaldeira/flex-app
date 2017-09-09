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
    private Class<?> aClass;

    public FlexLogger(Class<?> aClass) {
        this.aClass = aClass;
    }
    
    public void log(String format, Object... values) {
        if(isOn) {
            sout(format, values);
        }
    }

    public void info(String format, Object... values) {
        sout(format, values);
    }

    public void error(String format, Object... values) {
        if(isOn) {
            serr(format, values);
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

    private void sout(String format, Object[] values) {
        String result = "[" + aClass.getSimpleName() + "] ";
        result += String.format(format, values);
        System.out.println(result);
    }

    private void serr(String format, Object[] values) {
        String result = "[" + aClass.getSimpleName() + "] ";
        result += String.format(format, values);
        System.out.println(result);
    }
}
