package com.server.network;

import com.utilities.Config;

public class StartServer {
	public static void main(String[] argv) {
		System.out.println("Starting server ...");
		new UniversityServer(Config.DEFAULT_PORT);
	}
}
