public class Tuplo{

    private Object o1;
    private Object o2;

    Tuplo(){
        o1 = new Object();
        o2 = new Object();
    }

    Tuplo(Object o1,Object o2){
        this.setO1(o1);
        this.setO2(o2);
    }

    public void setO1(Object o1){
        this.o1 = o1;
    }

    public Object getO1() {
        return o1;
    }

    public Object getO2() {
        return o2;
    }

    public void setO2(Object o2) {
        this.o2 = o2;
    }

}
