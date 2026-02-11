/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package entappclient;

import ejb.MySessionRemote;
import javax.ejb.EJB;

/**
 *
 * @author lukin
 */
public class Main {

    @EJB
    private static MySessionRemote mySession;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.err.println("result = " + mySession.getResult());
    }
    
}
