package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.factory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * From https://blog.dev-area.net/2015/08/13/android-4-1-enable-tls-1-1-and-tls-1-2/
 */
public class TlsSocketFactory extends SSLSocketFactory {

    private static final String TLSv12 = "TLSv1.2";
    @NonNull
    private SSLSocketFactory delegate;

    TlsSocketFactory(@NonNull SSLSocketFactory sslSocketFactory) {
        delegate = sslSocketFactory;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return delegate.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket() throws IOException {
        return forceTlsVersionOnSocket(delegate.createSocket());
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return forceTlsVersionOnSocket(delegate.createSocket(s, host, port, autoClose));
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return forceTlsVersionOnSocket(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
        return forceTlsVersionOnSocket(delegate.createSocket(host, port, localHost, localPort));
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return forceTlsVersionOnSocket(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return forceTlsVersionOnSocket(delegate.createSocket(address, port, localAddress, localPort));
    }

    private Socket forceTlsVersionOnSocket(@Nullable Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            ((SSLSocket) socket).setEnabledProtocols(new String[]{TLSv12});
        }
        return socket;
    }

}
