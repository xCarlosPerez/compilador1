package Compilador;

public interface LenguajesConstants {

	// RegularExpression Id. //

	int EOF = 0;
	int PLUS = 5;
	int MINUS = 6;
	int MULTIPLY = 7;
	int DIVIDE = 8;
	int CONSTANT = 9;
	int DIGIT = 10;

	int DEFAULT = 0;

	String[] tokenImage = { "<EOF>", "\" \"", "\"\\r\"", "\"\\t\"", "\"\\n\"", "\"+\"", "\"-\"", "\"*\"", "\"/\"",
			"<CONSTANT>", "<DIGIT>", "\";\"", "\"(\"", "\")\"", };

}