package de.vw.mib.asl.internal.androidauto.target;

import de.adi961.miblogger.MIBLogger;
import de.vw.mib.asl.framework.internal.framework.GenericCollector;
import generated.de.vw.mib.asl.internal.ListManager;

import java.util.Vector;

public class ManeuverDescriptorSender {

    private VCManeuverDescriptor lastManeuverDescriptor;

    public void reset() {
        lastManeuverDescriptor = null;
    }

    public void send(VCManeuverDescriptor maneuverDescriptor) {
        if (lastManeuverDescriptor != null && lastManeuverDescriptor.toString().equals(maneuverDescriptor.toString())) {
            MIBLogger.getInstance().debug("same maneuver descriptor. Not sending.");
            return;
        }

        sendManeuverDescriptor(new VCManeuverDescriptor[]{maneuverDescriptor});
        this.lastManeuverDescriptor = maneuverDescriptor;
    }

    private void sendManeuverDescriptor(VCManeuverDescriptor[] maneuverDescriptorArray) {
        VCManeuverDescriptor[] descriptors = maneuverDescriptorArray;
        if (descriptors == null || descriptors.length == 0) {
            descriptors = new VCManeuverDescriptor[]{new VCManeuverDescriptor()};
        }

        Vector object = new Vector();
        GenericCollector[] genericCollectorArray = new GenericCollector[descriptors.length];
        for (int i = 0; i < descriptors.length; ++i) {
            GenericCollector genericCollector = new GenericCollector();
            genericCollector.setIntItem(0, descriptors[i].mainElement);
            genericCollector.setIntItem(1, descriptors[i].direction);
            genericCollector.setIntItem(2, descriptors[i].zLevelGuidance);
            String string = "";
            byte[] byArray = descriptors[i].sideStreets;
            if (byArray != null) {
                string = new String(ManeuverDescriptorSender.bytesToCharacters(byArray));
            }
            genericCollector.setStringItem(3, string);
            ((Vector) object).add(genericCollector);
            genericCollectorArray[i] = genericCollector;
        }

        ListManager.getGenericASLList(751).updateList(genericCollectorArray);
        ListManager.getGenericASLList(838).updateList(genericCollectorArray);
    }

    private static char[] bytesToCharacters(byte[] byArray) {
        int n = byArray.length;
        char[] cArray = new char[n];
        for (int i = 0; i < n; ++i) {
            cArray[i] = (char) (byArray[i] & 0xFF);
        }
        return cArray;
    }
}
