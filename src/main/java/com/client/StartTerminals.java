package com.client;

import com.utilities.Config;

public class StartTerminals {
	public static void main(String[] argv) {
		new UniversityClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
	}
}
 