package nf;

import library.interfaces.Patient;
import library.structure.groupe.messages.Message;
import protocole.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServeurHL7 {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    HL7Writer out = null;
    HL7Reader in = null;
    library.interfaces.Patient patient = null;
    Message message;

    public MyServeurHL7() {
        // ne fait rien car on souhaite uniquement creer l'objet en memoire
        //la connexion au serveur doit se faire a l'aide de la methode connection
    }

    public boolean connection(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException var3) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Could not listen on port: " + port + ".");
            System.exit(1);
        }

        return true;
    }

    public boolean ecoute() {
        try {
            this.clientSocket = this.serverSocket.accept();
        } catch (IOException var2) {
            Logger.getAnonymousLogger().log(Level.WARNING,"Accept failed.");
        }

        return true;
    }

    public String protocole() {
        String inputLine = null;

        try {
            this.out = new MinLLPWriter(this.clientSocket.getOutputStream());
            this.in = new MinLLPReader(this.clientSocket.getInputStream());
            ServeurProtocol servProto = new ServeurProtocol();
            inputLine = this.in.getMessage();
            String outputLine = servProto.processInput(inputLine);
            this.patient = servProto.getParser().getPatient();
            this.message = servProto.getParser().getMessage();
            this.out.writeMessage(outputLine);
        } catch (IOException var7) {
            Logger.getLogger(MyServeurHL7.class.getName()).log(Level.SEVERE, null, var7);
        } finally {
            this.out.close();
        }

        return inputLine;
    }

    public boolean fermer() {
        try {
            this.out.close();
            this.in.close();
            this.clientSocket.close();
        } catch (Exception var2) {
            Logger.getLogger(MyServeurHL7.class.getName()).log(Level.SEVERE, null, var2);
        }

        try {
            serverSocket.close();
        } catch (Exception e){
            Logger.getLogger(MyServeurHL7.class.getName()).log(Level.SEVERE, null, e);
        }

        return true;
    }

    public Patient getPatient() {
        return this.patient;
    }
}
