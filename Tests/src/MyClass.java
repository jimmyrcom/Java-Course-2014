// Name:      
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      MyClass.java

import java.util.ArrayList;
import static java.lang.System.out;

public class MyClass
{
    public int foo                        = 0;
    public String bar                     = "";
    private Double baz                    = 0.0;
    private ArrayList<Integer> list       = new ArrayList<Integer>();

    /***   Constructors ***********/
    MyClass()
    {
        setFoo(0);
        setBar("");
        setBaz(0.0);
        setList(new ArrayList<Integer>());
    }
    MyClass(int foo, String bar, Double baz, ArrayList<Integer> list)
    {
        setFoo(foo);
        setBar(bar);
        setBaz(baz);
        setList(list);
    }

    /***   Methods ***********/
    public String test(String name, int age)
    {
        return ""; 
    }

    /***   Getters and Setters ***********/
    public String getBar()
    {
        return bar;
    }
    public Double getBaz()
    {
        return baz;
    }
    public int getFoo()
    {
        return foo;
    }
    public ArrayList<Integer> getList()
    {
        return list;
    }
    public void setBar(String bar)
    {
        this.bar = bar;
    }
    public void setBaz(Double baz)
    {
        this.baz = baz;
    }
    public void setFoo(int foo)
    {
        this.foo = foo;
    }
    public void setList(ArrayList<Integer> list)
    {
        this.list = list;
    }

    public void addToList(Integer... integers)
    {
        for(Integer integer: integers) list.add(integer);
    }

    @Override
    public String toString()
    {
        return "MyClass(foo:int = " + foo + ", bar:String = " + "\"" + bar + "\"" + ", baz:Double = " +
               baz + ", list:ArrayList<Integer> = " + list + ")";
    }

    public static void main(String[] args){
        MyClass test = new MyClass();
        out.println(test);
    }

}