package com.revature.project0;

import java.io.InputStreamReader;

public class MovieApplication {
public static void main(String[] args) {
	Script.start(new InputStreamReader(System.in));
	Record.getInstance().backup();
}
}
