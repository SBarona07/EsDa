public class Dog implements  Animal{

    //Atributos
    private String breed ;
    private int size;

    //Constructor
    public Dog(String breed, int size) {
        this.breed = breed;
        this.size = size;
        
    @Override
    public void eat() {
        System.out.println("The dog is eating.");
    }

    @Override
    public void sleep() {
        System.out.println("The dog is sleeping.");

    }

    @Override
    public void makeSound() {
        System.out.println("The dog barks.");
    }

    @Override
    public void move() {
        System.out.println("The dog is running.");
    }   

    @Override
    public void getSpecies() {
        return "Dog";   
    }

    // Métodos específicos de la clase Dog
    wagtail() {
        System.out.println("The dog is wagging its tail.");
    }

    fetch() {
        System.out.println("The dog is fetching the ball.");
    }


}
