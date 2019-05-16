package ac692x_case.jieli.com.ipcapplication;

public class SingleClass {

    private static SingleClass instance=new SingleClass();
    public static SingleClass getInstance() {
        return instance;
    }



    private SingleClass() {
    }
}
