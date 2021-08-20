package modules.methods;
import modules.workenvironment.SettingsManager;
import modules.workenvironment.WorkEnvironmentMain;
import modules.basecomponent.wire;
public class WireShadowDrawing {
    public WireShadowDrawing(){}
    public static void drawshadows(int dx, int dy){
        if (SettingsManager.griding()){
            if (WorkEnvironmentMain.wireoder){
                if (dx >= 0){
                    WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, dx - dx % 10, 0);
                    WorkEnvironmentMain.wireShadow[0].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0], WorkEnvironmentMain.portforwireshadow.location[1] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[1] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[1]);
                } else {
                    if (dx % 10 != 0){
                        WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, Math.abs(dx - (10 + dx % 10)), 0);
                        WorkEnvironmentMain.wireShadow[0].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0] - Math.abs(dx - (10 + dx % 10)), WorkEnvironmentMain.portforwireshadow.location[1] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[1] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[1]);
                    } else {
                        WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, Math.abs(dx), 0);
                        WorkEnvironmentMain.wireShadow[0].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0] - Math.abs(dx - dx % 10), WorkEnvironmentMain.portforwireshadow.location[1] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[1] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[1]);
                    }
                }
                if (dy >= 0){
                    WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, 0, dy - dy % 10);
                    if (dx >= 0){
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0] + dx - dx % 10, WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                    } else {
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0], WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                    }
                } else {
                    WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, 0, Math.abs(dy - dy % 10));
                    if (dx >= 0){
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0] + dx - dx % 10, WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1] - Math.abs(dy - dy % 10));
                    } else {
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0], WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1] - Math.abs(dy - dy % 10));
                    }
                }
            } else {
                if (dy >= 0){
                    WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, 0, dy - dy % 10);
                    WorkEnvironmentMain.wireShadow[0].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0], WorkEnvironmentMain.portforwireshadow.location[1] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[1] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[1]);
                } else {
                    WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, 0, Math.abs(dy - dy % 10));
                    WorkEnvironmentMain.wireShadow[0].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0], WorkEnvironmentMain.portforwireshadow.location[1] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[1] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[1] - Math.abs(dy - dy % 10));
                }
                if (dx >= 0){
                    if (dy >= 0){
                        WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, dx - dx % 10, 0);
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0], WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1] + dy - dy % 10);
                    } else {
                        WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, dx - dx % 10, 0);
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0], WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                    }
                } else {
                    if (dy >= 0){
                        WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, Math.abs(dx - dx % 10), 0);
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0] - Math.abs(dx - dx % 10), /*Math.round(y / WorkEnvironmentMain.Scale) -  Math.round(y / WorkEnvironmentMain.Scale) % 10*/ WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1] + (dy - dy % 10));
                    } else {
                        WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, Math.abs(dx - dx % 10), 0);
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0] - Math.abs(dx - dx % 10), WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                    }
                }
            }
        } else {
            if (WorkEnvironmentMain.wireoder){
                if (dx >= 0){
                    WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, dx, 0);
                    WorkEnvironmentMain.wireShadow[0].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0], WorkEnvironmentMain.portforwireshadow.location[1] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[1] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[1]);
                } else {
                    WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, Math.abs(dx), 0);
                    WorkEnvironmentMain.wireShadow[0].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0] - Math.abs(dx), WorkEnvironmentMain.portforwireshadow.location[1] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[1] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[1]);
                }
                if (dy >= 0){
                    WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, 0, dy);
                    if (dx >= 0){
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0] + dx, WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                    } else {
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0], WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                    }
                } else {
                    WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, 0, Math.abs(dy));
                    if (dx >= 0){
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0] + dx, WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1] - Math.abs(dy));
                    } else {
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0], WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1] - Math.abs(dy));
                    }
                }
            } else {
                if (dy >= 0){
                    WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, 0, dy);
                    WorkEnvironmentMain.wireShadow[0].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0], WorkEnvironmentMain.portforwireshadow.location[1] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[1] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[1]);
                } else {
                    WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, 0, Math.abs(dy));
                    WorkEnvironmentMain.wireShadow[0].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0], WorkEnvironmentMain.portforwireshadow.location[1] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[1] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[1] - Math.abs(dy));
                }
                if (dx >= 0){
                    if (dy >= 0){
                        WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, dx, 0);
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0], WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1] + dy);
                    } else {
                        WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, dx, 0);
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0], WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                    }
                } else {
                    if (dy >= 0){
                        WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, Math.abs(dx), 0);
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.portforwireshadow.location[0] - WorkEnvironmentMain.portforwireshadow.belongsto.getRotationFlag()[0] + WorkEnvironmentMain.portforwireshadow.belongsto.getComponentLocation()[0] - Math.abs(dx), WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1] + dy);
                    } else {
                        WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, Math.abs(dx), 0);
                        WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0] - Math.abs(dx), WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                    }
                }
            }
        }
        ((wire) WorkEnvironmentMain.wireShadow[0]).setselfcolor(WorkEnvironmentMain.portforwireshadow.color);
        ((wire) WorkEnvironmentMain.wireShadow[1]).setselfcolor(WorkEnvironmentMain.portforwireshadow.color);
    }
}