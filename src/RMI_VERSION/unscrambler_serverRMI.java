package RMI_VERSION;

import javax.swing.*;
import java.awt.*;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class unscrambler_serverRMI{

    String unscrambler_interfaceString = "unscrambler_Interface";
    String ipAddress_port;
    String ipAddress;

    int port;

    Registry registry;

    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("[hh:mm a]");

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            try {
                new unscrambler_serverRMI();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        });
    }

    public unscrambler_serverRMI() throws RemoteException {
        super();

        try{
            ipAddress_port = JOptionPanelMultiInput();

            ipAddress = getIpAddress(ipAddress_port);
            port = getPort(ipAddress_port);

            registry = LocateRegistry.createRegistry(port);
            System.setProperty("java.rmi.server.hostname", ipAddress);
            registry.rebind(unscrambler_interfaceString, new unscrambler_serverRMIServant());

            modifiedPrint(unscrambler_interfaceString + " RMI Initiating . . .");
            modifiedPrint(unscrambler_interfaceString + " IP Address: " + ipAddress);
            modifiedPrint(unscrambler_interfaceString + " Linked to Port: " + port);

        }catch(RemoteException exception){
            exception.printStackTrace();
        }
    }

    private String JOptionPanelMultiInput(){
        JTextField IpAddressField = new JTextField(8);
        IpAddressField.setText("192.168.");
        JTextField PortField = new JTextField(8);
        PortField.setText("8888");
        String IpAddress_Port = "";

        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Ip Address: "));
        jPanel.add(IpAddressField);

        jPanel.add(Box.createHorizontalStrut(10));

        jPanel.add(new JLabel("Port: "));
        jPanel.add(PortField);

        int result = JOptionPane.showConfirmDialog(null, jPanel, "Enter Ip Address and Port of the Server.", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION){
            IpAddress_Port = IpAddressField.getText() + " " + PortField.getText();
        }
        return IpAddress_Port;
    }

    /**
     * Retrieves the Ip Address from the String.
     * @param string unsplitted String.
     * @return string Ip Address.
     */
    private String getIpAddress(String string){
        String[] split = string.split(" ");
        return split[0];
    }

    /**
     * Retrieves the Port from the String.
     * @param string unsplitted String.
     * @return integer Port.
     */
    private int getPort(String string){
        String[] split = string.split(" ");
        int number = 0;

        if(string.equals("")){
            System.exit(0);
        }

        try{
            number = Integer.parseInt(split[1]);
        }catch(Exception exception){
            JOptionPane.showMessageDialog(null, "Port input should be numerical!", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return number;
    }

    public static void modifiedPrint(String logMessage){
        System.out.printf("%s %s\n", dateFormatter.format(new Date()), logMessage);
    }
}
