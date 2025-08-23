public class MyArrayList {
    private Object[] myArray;
    private int size;
    private static int default_size = 10;

    public MyArrayList() {
        myArray = new Object[default_size];
        size = 0;
    }

    public void add(Object object) {
        if (size == myArray.length) {
            increaseSize();
        }
        myArray[size] = object;
        size++;
    }
    public Object get(int index) {
        return myArray[index];
    }

    /*
     * borra una posicion del arreglo
     * @param i la posicion a borrar
     */
    public void delete(int index) {
        for (int i= index; i < myArray.length - 1; i++) {
            if (i + 1 != size)
            myArray[i] = myArray[i + 1];
            else
            myArray[i] = null;
        }
        size--;


    }
        /*
     * metodo que genera una cadena del arreglo en formato
     * valor1 valor2 valor3
     */
    public String toString() {
        String retuStringn = "";
        for (int i = 0; i < myArray.length; i++) {
            retuStringn += myArray[i] + " ";
        }
        return retuStringn;
    }

    private void increaseSize() {
        T[] newArray = (T[]) new Object[myArray.length * 2];
        for (int i = 0; i < myArray.length; i++) {
            newArray[i] = myArray[i];
        }
        myArray = newArray;
    }

}