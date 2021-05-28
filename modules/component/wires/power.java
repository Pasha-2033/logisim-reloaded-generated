package modules.component.wires;

import modules.gui.DrawElement;

public class power extends DrawElement {
    public power(){super.setscale();}
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

