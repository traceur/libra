package com.weizhi.libra.common.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * IPUitls
 * 
 * @author
 */
public final class IPUitls {

    /**
     * hide constructor
     */
    private IPUitls() {
    }


    /**
     * @return IP list of this machine
     * @throws UnknownHostException
     */
    public final static List<String> getLANAddress() throws UnknownHostException {
        try {
            List<String> ips = new ArrayList<String>();
            for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces
                    .hasMoreElements();) {
                NetworkInterface iface = ifaces.nextElement();

                if (iface.isLoopback() && !iface.isUp()) {
                    continue;
                }

                // Iterate all IP addresses assigned to each card...
                for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs
                        .hasMoreElements();) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress() && inetAddr.isSiteLocalAddress()) {
                        ips.add(inetAddr.getHostAddress());
                    }
                }
            }

            if (ips.isEmpty()) {
                InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
                if (jdkSuppliedAddress != null) {
                    ips.add(jdkSuppliedAddress.getHostAddress());
                } else {
                    throw new UnknownHostException(
                            "The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
                }
            }

            return ips;
        } catch (SocketException e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }


    public static void main(String... abc) throws UnknownHostException, IOException {

    }
}
