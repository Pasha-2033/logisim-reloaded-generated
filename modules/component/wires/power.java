package modules.component.wires;

import modules.gui.DrawComponent;

public class power extends DrawComponent {
    public power(){}
    public String fasing;
    public int x;
    public int y;
    public int bitcount;
    public power(String fasing, int x, int y, int bitcount){
        this.fasing = fasing;
        this.x = x;
        this.y = y;
        this.bitcount = bitcount;
    }
}

