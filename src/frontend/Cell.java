/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

/**
 *
 * @author thinhpham
 */
public class Cell {
    private char value;
    private boolean editable;
    
    public Cell() {
        value = 'z';
        editable = true;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
        this.setEditable(false);
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
}
