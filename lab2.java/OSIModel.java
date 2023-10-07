// Application Layer
class ApplicationLayer {
    public  String processData(String input) {
       
        try {
           Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
         return "Processed: " + input;
    }

    public  String processoutData(String input) {
         try {
            Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        String received = input;
        input = received.replaceAll("Processed: ", "");

        return input;
    }
}

// Presentation Layer
class PresentationLayer {
    public  String encryptData(String input) {
         try {
           Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "Encrypted: " + input;
    }

    public  String decryptData(String input) {

         try {
           Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        String received = input;
        input = received.replaceAll("Encrypted: ", "");

        return input;
    }
}

// Session Layer
class SessionLayer {
    public  String establishSession(String input) {
         try {
            Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "Session Established: " + input;
    }

    public  String deestablishSession(String input) {
         try {
            Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        String received = input;
        input = received.replaceAll("Session Established: ", "");

        return input;
    }
}

// Transport Layer
class TransportLayer {
    public  String segmentData(String input) {
        try {
           Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "Segmented: " + input;
    }

    public  String reassembleData(String input) {
        try {
           Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        String received = input;
        input = received.replaceAll("Segmented: ", "");

        return input;
    }
}

// Network Layer
class NetworkLayer {
    public  String routeinData(String input) {
        try {
           Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "Routed: " + input;
    }

    public  String routeoutData(String input) {
        try {
            Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        String received = input;
        input = received.replaceAll("Routed: ", "");
        // System.out.println(input);

        return input;
    }
}

// Data Link Layer
class DataLinkLayer {
    public  String frameData(String input) {
        try {
            Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "Framed: " + input;
    }

    public  String deframeData(String input) {
        try {
            Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }

        String received = input;
        input = received.replaceAll("Framed: ", "");
        // input=received;

        return input;

    }
}

// Physical Layer
class PhysicalLayer {
    public  String sendOverMedium(String input) {
        try {
           Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "Sent_over_Medium: " + input;
    }

    public  String receiveFromMedium(String input) {
        try {
            Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        String received = input;
        input = received.replaceAll("Sent_over_Medium: ", "");

        // System.out.println(input);

        return input;
    }
}

class OSI {

    OSI() {

        // Simulate data flow from Application to Physical layer
        String inputData = "Hello, OSI Model!";

        // Application Layer
        ApplicationLayer al=new ApplicationLayer();
        String processedData=al.processData(inputData);
        
        System.out.println("step 1: " + inputData);

        // Presentation Layer
        PresentationLayer pl=new PresentationLayer();
        String encryptedData =pl.encryptData(processedData);
        System.out.println("step 2: " + encryptedData);

        // Session Layer
        SessionLayer sl=new SessionLayer();
        String sessioninData = sl.establishSession(encryptedData);
        System.out.println("step 3: " + sessioninData);

        // Transport Layer
        TransportLayer tl=new TransportLayer();
        String segmentedData = tl.segmentData(sessioninData);
        System.out.println("step 4: " + segmentedData);

        // Network Layer
        NetworkLayer nl=new NetworkLayer();
        String routedData = nl.routeinData(segmentedData);
        System.out.println("step 5: " + routedData);

        // Data Link Layer
        DataLinkLayer dl=new DataLinkLayer();
        String framedData = dl.frameData(routedData);
        System.out.println("step 6: " + framedData);

        // Physical Layer
        PhysicalLayer phl=new PhysicalLayer();
        String sentData = phl.sendOverMedium(framedData);
        System.out.println("step 7: " + sentData);
        String receivedData = phl.receiveFromMedium(sentData);
        System.out.println("step 7: " + receivedData);

        // Data Link Layer
        String deframedData = dl.deframeData(framedData);
        System.out.println("step 6: " + deframedData);

        // Network Layer
        String inroutedData = nl.routeoutData(deframedData);
        System.out.println("step 5: " + inroutedData);

        // Transport Layer
        String reassembledData = tl.reassembleData(inroutedData);
        System.out.println("step 4: " + reassembledData);

        // Session Layer
        String sessionoutData = sl.deestablishSession(reassembledData);
        System.out.println("step 3: " + sessionoutData);

        // Presentation Layer
        String decryptedData = pl.decryptData(sessionoutData);
        System.out.println("step 2: " + decryptedData);

        // Application Layer
        String message = al.processoutData(decryptedData);
        System.out.println("step 1: " + message);

        // Display final result
        try {
            Thread.sleep(1200);
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("Received data: " + message);
    }

}

// Main class
public class OSIModel {

    public static void main(String[] args) throws Exception{
        OSI osi = new OSI();
      Thread.sleep(1200);
    }
}
