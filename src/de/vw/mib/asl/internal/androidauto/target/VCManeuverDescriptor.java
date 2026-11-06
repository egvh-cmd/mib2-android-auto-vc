package de.vw.mib.asl.internal.androidauto.target;

/**
 * Собственный контейнер дескриптора манёвра.
 *
 * Заменяет org.dsi.ifc.navigation.BapManeuverDescriptor из прошивки:
 * в части трейнов (например MU1367) тот класс не содержит полей
 * direction/roundaboutExitNumber и 4-аргументного конструктора,
 * из-за чего обращение к ним приводило бы к NoSuchFieldError/NoSuchMethodError.
 * Свой класс компилируется внутрь VCAndroidAuto.jar и не зависит
 * от версии DSI-классов прошивки.
 */
public class VCManeuverDescriptor {
    public int mainElement;
    public int direction;
    public int zLevelGuidance;
    public byte[] sideStreets;
    public int roundaboutExitNumber;

    public VCManeuverDescriptor() {
        this.mainElement = 0;
        this.direction = 0;
        this.zLevelGuidance = 0;
        this.sideStreets = new byte[0];
        this.roundaboutExitNumber = 0;
    }

    public String toString() {
        return "VCManeuverDescriptor{mainElement=" + this.mainElement
                + ", direction=" + this.direction
                + ", zLevelGuidance=" + this.zLevelGuidance
                + ", roundaboutExitNumber=" + this.roundaboutExitNumber + "}";
    }
}
