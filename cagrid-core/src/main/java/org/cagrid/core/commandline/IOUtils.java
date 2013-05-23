package org.cagrid.core.commandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

public class IOUtils {

	public static int readInteger(String prompt) {
		return readInteger(prompt, false);
	}

	public static long readLong(String prompt) {
		return readLong(prompt, false);
	}

	public static int readInteger(String prompt, boolean force) {
		String s = readLine(prompt);
		try {
			return Integer.valueOf(s).intValue();
		} catch (Exception e) {
			System.err.println("Please try again, this time enter a Integer!!!");
			return readInteger(prompt, force);
		}

	}

	public static long readLong(String prompt, boolean force) {
		String s = readLine(prompt, force);
		try {
			return Long.valueOf(s).longValue();
		} catch (Exception e) {
			System.err.println("Please try again, this time enter a Long!!!");
			return readInteger(prompt, force);
		}

	}

	public static String readLine(String prompt, boolean force) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = null;

		try {
			System.out.print(prompt + ":");
			System.out.flush();
			s = br.readLine();
			while (force && ((s == null) || (s.trim().length() == 0))) {
				System.out.print(prompt + ":");
				s = br.readLine();
			}
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your response!");
			System.exit(1);
		}
		return s;
	}

	public static String readLine(String prompt) {
		return readLine(prompt, false);
	}

	public static String readLine(String prompt, String defaultValue) {
		String line = readLine(prompt + "[" + defaultValue + "]", false);
		if (StringUtils.isBlank(line)) {
			line = defaultValue;
		}
		return line;
	}

	public static boolean readLineConfirmation(String prompt, boolean b) {
		String response = readLine(prompt, b);
		while (!validResponse(response)[0]) {
			System.out.println("Please enter (y)es or (n)o.");
			response = readLine(prompt, b);
		}
		return validResponse(response)[1];
	}

	private static boolean[] validResponse(String response) {
		if (response == null || response.length() < 1) {
			return new boolean[] { false, false };
		}

		if ("y".equalsIgnoreCase(response) || "yes".equalsIgnoreCase(response)) {
			return new boolean[] { true, true };
		}

		if ("n".equalsIgnoreCase(response) || "no".equalsIgnoreCase(response)) {
			return new boolean[] { true, false };
		}

		// how nerdy do we want to be?
		if ("affirmative".equalsIgnoreCase(response)) {
			return new boolean[] { true, true };
		}

		if ("negative".equalsIgnoreCase(response)) {
			return new boolean[] { true, false };
		}

		return new boolean[] { false, false };
	}

}
