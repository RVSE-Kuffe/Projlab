package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;

import logarlecTheGame.Model.Interfaces.CycleBased;
import logarlecTheGame.Skeleton.Skeleton;

public class CycleUsage {
    private int cycleNumber;
    private List<CycleBased> cbList;
    private Skeleton sk;

    public CycleUsage(Skeleton s, String n){
        sk = s;
        sk.names.put(this, n);
        cycleNumber = 0;
        cbList = new ArrayList<>();
    }

    public void destroy(CycleBased c){
        System.out.println(sk.names.get(this) + " destroy(" + sk.names.get(c) + ")" );

        System.out.println(sk.names.get(this) + " return");
    }

    public void add(CycleUsage c){
        System.out.println(sk.names.get(this) + " add(" + sk.names.get(c) + ")");

        System.out.println(sk.names.get(this) + " return");
        return;
    }
}
