/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author marin
 */
public class ui {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * To accept only both genders, female or male, writing f or m. And do not accept another words.
     * @param reader a buffered reader to accept text from the console
     * @param text The text that is going to be seen by console.
     * @return the string of the gender
     */
    public static String takeGender(BufferedReader reader, String text) {
		String gender = " ";
		String answer;
		try {
			do {
				System.out.println(text + "(m/f)");
				answer = reader.readLine();
				switch (answer) {
				case "M":
				case "m":
					gender = "Male";
					break;
				case "F":
				case "f":
					gender = "Female";
					break;
				default:
					System.out.println("The data introduced is NOT correct.");
					System.out.println("Please introduce m in case of Male or f in case of Female");
					System.out.println("Try again.");
					gender = " ";
					break;
				}
			} while (gender.equals(" "));
		} catch (IOException ex) {
			System.out.println("Error reading");
		}
		return gender;
	}
    
    /**
     * Take a date writen by console and converted it in the format of date LocalDate.
     * @param reader a buffered reader to accept text from the console
     * @param text The text that is going to be seen by console.
     * @return the date that has been introduced by console.
     */
    public static LocalDate takeDate(BufferedReader reader, String text) {
		boolean check = false;
		String data = "";
		LocalDate day = LocalDate.now();
		System.out.println(text);
		while (!check || data.equals("")) {
			try {
				data = reader.readLine();
				day = LocalDate.parse(data, formatter);
				check = true;
			} catch (IOException ex) {
				System.out.println("Error reading");
			} catch (DateTimeParseException nfex) {
				System.out.println("You have not introduced a valid Date. Try again.");
				System.out.println(text);
			}
		}
		return day;
	}

    /**
     * take by console 9 numbers and only integers. Make you to repeat if you introduced another thing or more than 9.
     * @param reader a buffered reader to accept text from the console
     * @param text The text that is going to be seen by console.
     * @return an String of the 9 numbers of the telephone.
     */
    public static String takeTelephone(BufferedReader reader, String text) {
		String num="reader";
		boolean check = true;
		char cad[];
		try {
			do {
				System.out.println(text + " (without spaces)");
				num = reader.readLine();
				cad = num.toCharArray();
				check = false;
				for (int i = 0; i < num.length(); i++) {

					if (Character.isDigit(cad[i]) || cad[i] == '+') {

					} else if (Character.isSpaceChar(cad[i])) {
						check = true;
						break;
					} else {
						check = true;
						break;
					}
				}
				if (check == true) {
					System.out.println("You don't introduce a telephone number.");
					System.out.println("Please introduce numbers.");
				}
			} while (check);
			if (num.substring(0, 1).contains("+")) {
				num = num.substring(3, num.length());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return num;

	}

    /**
     * Makes you sure if you want to do something or not.
     * @param reader a buffered reader to accept text from the console
     * @param text The text that is going to be seen by console.
     * @return a true if you are sure and a false if not.
     */
    public static boolean areYouSure(BufferedReader reader, String text) {
		boolean resp = false;
		boolean loop = false;
		String answer = "";
		try {
			do {
				System.out.println(text + " (yes/no):");
				answer = reader.readLine();
				if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
					loop = false;
					if (answer.contains("yes")||answer.contains("Yes")||answer.contains("YES")) {
						resp = true;
					} else {
						resp = false;
					}
				} else {
					System.out.println("The answer is not correct. Try again.");
					loop = true;
				}

			} while (loop);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}
    
    /**
     * Take by console 8 numbers and only integers. Make you to repeat if you introduced another thing or more than 8.
     * @param reader a buffered reader to accept text from the console
     * @param text The text that is going to be seen by console.
     * @return an String of the 8 numbers of the DNI.
     */
    public static String takeDNI(BufferedReader reader, String text) {
		
		String num = "error in takeDNI()";
		boolean check = true;
		char cad[];
		try {
			do {
				System.out.println(text + " (without spaces)");
				num = reader.readLine();
				cad = num.toCharArray();
				check = false;
				for (int i = 0; i < num.length(); i++) {

					if (Character.isDigit(cad[i]) || cad[i] == '+') {

					} else if (Character.isSpaceChar(cad[i])) {
						check = true;
						break;
					} else {
						check = true;
						break;
					}
				}
				if (check == true) {
					System.out.println("You don't introduce a valid DNI number.");
					System.out.println("Please introduce numbers.");
				}
			} while (check);
			if (num.substring(0, 1).contains("+")) {
				num = num.substring(3, num.length());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return num;

	}
    
    /**
     * Take by console only an integer, if not it will repeat till you insert integer.
     * @param reader a buffered reader to accept text from the console
     * @param text The text that is going to be seen by console.
     * @return the integer that you insert by console..
     */
    public static int takeInteger(BufferedReader reader, String text) {
		boolean check = false;
		int data = 0;

		System.out.println(text);

		while (!check || data < 0) {
			try {
				data = Integer.parseInt(reader.readLine());
				check = true;
			} catch (IOException ex) {
				System.out.println("Error reading");
			} catch (NumberFormatException nfex) {
				System.out.println("You have not introduced a Integer, you must do it.");
				System.out.println(text);
			}

		}
		return data;
	}

    public static boolean CheckOption(int num, int max) {
		if (num > max || num < 0) {
			System.out.println("This number is not an option");
			System.out.println("The number must be between 1 and " + max);
			System.out.println("Try again!");
			return true;
		} else {
			return false;
		}
	}
    

    

}
