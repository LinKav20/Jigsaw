package com.flinkou.demo.network;

import com.flinkou.demo.network.server.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServerClientTest {
    @Test
    public void StartConnection_WrongConnection_ExitGame(){
        Server server = new Server();
        server.start();

    }
}
