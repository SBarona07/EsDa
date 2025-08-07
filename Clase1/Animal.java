public package Clase1;

public interface Animal {
    
    // Every animal must be able to eat
    void eat();
    
    // Every animal must be able to sleep
    void sleep();
    
    // Every animal must be able to make some sound (or no sound!)
    void makeSound();
    
    // Every animal must be able to move somehow
    void move();
    
    // Every animal must be able to tell us its species
    String getSpecies();
}