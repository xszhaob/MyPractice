package bo.zhao.practice.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/6/28
 */
public class RpcFramework {


    /**
     * 暴露服务
     *
     * @param service 服务实现
     * @param port    端口号
     */
    public static void export(final Object service, int port) throws Exception {
        if (service == null) {
            throw new IllegalArgumentException("service instance == null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println(String.format("Export service %s on port %d", service.getClass().getName(), port));

        ServerSocket server = new ServerSocket(port);

        for (; ; ) {
            final Socket socket = server.accept();

            try {
                new Thread(() -> {
                    try {
                        try {
                            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                            try {
                                String methodName = inputStream.readUTF();
                                Class<?>[] paramTypes = (Class<?>[]) inputStream.readObject();
                                Object[] arguments = (Object[]) inputStream.readObject();
                                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                                try {
                                    Method method = service.getClass().getMethod(methodName, paramTypes);
                                    Object result = method.invoke(service, arguments);
                                    System.out.println("Invoke " + service.getClass() + "#" + method.getName() + ", result -> " + result);
                                    output.writeObject(result);
                                } catch (Throwable t) {
                                    t.printStackTrace();
                                    // 把调用异常返回给调用方
                                    output.writeObject(t);
                                } finally {
                                    output.close();
                                }
                            } finally {
                                inputStream.close();
                            }
                        } finally {
                            socket.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T refer(Class<T> interfaceClass, final String host, final int port) {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("Interface class == null");
        }
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException(String.format("The %s must be interface class!", interfaceClass.getClass()));
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("host == null;");
        }
        if (port <= 0 || port > 65535) {
            String errMsg = String.format("Invalid port %d ", port);
            throw new IllegalArgumentException(errMsg);
        }
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, (proxy, method, args) -> {
            Socket socket = new Socket(host, port);
            try {
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                try {
                    output.writeUTF(method.getName());
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(args);

                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    try {
                        Object result = input.readObject();
                        if (result instanceof Throwable) {
                            throw (Throwable) result;
                        }
                        return result;
                    } finally {
                        input.close();
                    }
                } finally {
                    output.close();
                }
            } finally {
                socket.close();
            }
        });
    }
}
