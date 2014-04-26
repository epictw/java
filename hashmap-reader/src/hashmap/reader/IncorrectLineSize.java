/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hashmap.reader;

/**
 *
 * @author bogdan
 */
public class IncorrectLineSize extends Exception{
    
    public IncorrectLineSize() { 
        super(); 
    }
    
    public IncorrectLineSize(String message) { 
        super(message); 
    }
    
    @Override
    public String getMessage() {
        return "IncorrectLineSize: " + super.getMessage();
    }
}
