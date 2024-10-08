package utilities;

import java.io.*;
import java.util.*;

/**
 * <code>Console provides a set of methods to support ease of console reading
 * and printing. <br>
 * Printing is supported by static functions printMessage & printErrorMessage
 * <br>
 * Reading is supported by a set of static read methods. Common to all read
 * methods is: <br>
 * - every read method prints a prompt & reads of complete input line <br>
 * - every read method checks input to be IN line with format expectations (if
 * any) <br>
 * - error message & repeated input request are generated if input is not IN
 * line with format expectations (if any)
 * 
 * @author Peter Ruckmann
 * @version 1.0
 */
public class Console {

	private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
	private static final int INDENTATION = 4;
	private static int indent = 0;

	/**
	 * Opens new dialogue section by increasing dialogue indentation space by one indentation level
	 */
	public static void openSection() {
		indent += INDENTATION;
	}

	/**
	 * Closes current dialogue section decreasing dialogue indentation space by one indentation level
	 */
	public static void closeSection() {
		indent -= INDENTATION;
	}

	private static String getIndentation() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < indent; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}

	/**
	 * displays message string on console
	 * 
	 * @param string
	 *          to be displayed
	 */
	public static void print(String string) {
		System.out.print(getIndentation() + string);
	}

	/**
	 * displays string on console and then terminates the line
	 * 
	 * @param string
	 *          to be displayed
	 */
	public static void println(String string) {
		System.out.println(getIndentation() + string);
	}
	
	public static <T> void printList(String listTypeName, T[] list) {
		if (list == null || list.length==0) {
			Console.println("No " + listTypeName + "s available");
		} else {
			Console.println("Available " + listTypeName+ "s are:");
			for (int i=0; i<list.length; i++) {
				Console.println("(" + i + ") " + list[i]);
			}	
		}
	}
	
	public static void printlnError(String string) {
		System.out.println(getIndentation() + "*** Error: " + string + " ***");
	}

	/**
	 * displays prompt and reads complete line from console
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return line read from console
	 */
	public static String readString(String prompt) {
		String line = "";
		print(prompt + ": ");
		try {
			line = IN.readLine();
		} catch (IOException e) {
			System.err.println("" + e + "\n Program terminates ...\n");
			System.exit(1);
		}
		return line;
	}

	/**
	 * displays prompt, offers set of choice options
	 * 
	 * @param prompt
	 *          to be displayed + choice options (strings)
	 * @return selected choice option
	 */
	public static <T>T readChoice(String typeName, T... choiceOptions) {
		printList(typeName, choiceOptions);
		if (choiceOptions.length==0) {
			return null;
		}
		int choice = readInt("Select " + typeName, 0, choiceOptions.length-1);
		return choiceOptions[choice];
	}

	/**
	 * displays prompt, reads complete line from console and returns tokens read
	 * from console
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return tokens found IN line read from console
	 */
	public static String[] readTokens(String prompt) {
		String s = readString(prompt);
		StringTokenizer st = new StringTokenizer(s);
		String[] tokens = new String[st.countTokens()];
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = st.nextToken();
		}
		return tokens;
	}

	/**
	 * Displays prompt & returns token read from console Expects exactly one
	 * token. New request if no token or more than one token.
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return token read from console
	 */
	public static String readToken(String prompt) {
		String[] tokens = readTokens(prompt);
		if (tokens.length == 1) {
			return tokens[0];
		} else {
			error();
			return readToken(prompt);
		}
	}

	/**
	 * Displays prompt & returns <code>int</code>-number read from console
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return <code>int</code>-number read from console
	 */
	public static int readInt(String prompt) {
		String[] tokens = readTokens(prompt);
		if (tokens.length == 1) {
			try {
				return Integer.parseInt(tokens[0]);
			} catch (NumberFormatException e) {
			}
		}
		error();
		return readInt(prompt);
	}

	/**
	 * Displays prompt & returns <code>int</code>-number read from console
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return <code>int</code>-number read from console
	 */
	public static int readInt(String prompt, int min, int max) {
		int result = readInt(prompt);
		while (result < min || result > max) {
			error();
			result = readInt(prompt);
		}
		return result;
	}

	/**
	 * Displays prompt & returns <code>long</code>-number read from console
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return <code>long</code>-number read from console
	 */
	public static long readLong(String prompt) {
		String[] tokens = readTokens(prompt);
		if (tokens.length == 1) {
			try {
				return Long.parseLong(tokens[0]);
			} catch (NumberFormatException e) {
			}
		}
		error();
		return readLong(prompt);
	}

	/**
	 * Displays prompt & returns <code>double</code>-number read from console
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return <code>double</code>-number read from console
	 */
	public static double readDouble(String prompt) {
		String[] tokens = readTokens(prompt);
		if (tokens.length == 1) {
			try {
				return Double.valueOf(tokens[0]).doubleValue();
			} catch (NumberFormatException e) {
			}
		}
		error();
		return readDouble(prompt);
	}

	/**
	 * Displays prompt & returns <code>float</code>-number read from console
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return <code>float</code>-number read from console
	 */
	public static float readFloat(String prompt) {
		String[] tokens = readTokens(prompt);
		if (tokens.length == 1) {
			try {
				return Float.valueOf(tokens[0]).floatValue();
			} catch (NumberFormatException e) {
			}
		}
		error();
		return readFloat(prompt);
	}

	/**
	 * Displays prompt & returns <code>short</code>-number read from console
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return <code>short</code>-number read from console
	 */
	public static short readShort(String prompt) {
		String[] tokens = readTokens(prompt);
		if (tokens.length == 1) {
			try {
				return Short.valueOf(tokens[0]).shortValue();
			} catch (NumberFormatException e) {
			}
		}
		error();
		return readShort(prompt);
	}

	/**
	 * Displays prompt & returns <code>boolean</code>-value read from console
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return <code>boolean</code>-number read from console
	 */
	public static boolean readBoolean(String prompt) {
		String[] tokens = readTokens(prompt);
		if (tokens.length == 1) {
			switch (tokens[0].toLowerCase()) {
				case "false":
				case "falsch":
					return false;
				case "true":
				case "wahr":
					return true;
			}
		}
		error();
		return readBoolean(prompt);
	}

	/**
	 * Displays prompt & returns <code>boolean</code>-value depending of yes/no
	 * answer
	 * 
	 * @param prompt
	 *          to be displayed
	 * @return <code>boolean</code>-number read from console
	 */
	public static boolean readYesNo(String prompt) {
		String[] tokens = readTokens(prompt);
		if (tokens.length >= 1) {
			switch (tokens[0].toLowerCase()) {
				case "n":
				case "nein":
				case "nee":
				case "nöh":
				case "njet":
				case "no":
					return false;
				case "j":
				case "ja":
				case "jawohl":
				case "ok":
				case "y":
				case "yes":
					return true;
				default:
					error();
					return readYesNo(prompt);
			}
		} else {
			error();
			return readYesNo(prompt);
		}
	}

	/* generates error message and asked expected input to be repeated */
	private static void error() {
		printlnError("Input error! Please repeat ...");
	}

}

