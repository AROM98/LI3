public class Triplo {

    private Object o1;
    private Object o2;
    private Object o3;

    Triplo(){
        o1 = new Object();
        o2 = new Object();
        o3 = new Object();
    }
    Triplo(Object o1,Object o2,Object o3){
        this.setO1(o1);
        this.setO2(o2);
        this.setO3(o3);
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

    public void setO3(Object o3){
        this.o3 = o3;
    }

    public Object getO3(Object o3){
        return o3;
    }
}
