package Compilador;

import java.util.ArrayList;

public class Lenguajes implements LenguajesProyConstants {
	
	static ArrayList<Identificador> Datos = new ArrayList<Identificador>();

	public static void main(String args[]) throws ParseException {

		try {

			Lenguajes analizador = new Lenguajes(System.in);
			analizador.Programa();

		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
		System.out.println(" ----------------> Tabla de simbolos  ");
		for (int i = 0; i < Datos.size(); i++) {
			System.out.println("----->Nombre: " + Datos.get(i).getNombre());
			System.out.println("----->Tipo: " + Datos.get(i).getTipo());
			System.out.println("----->Valor: " + Datos.get(i).getValor());
			System.out.println("--------------------------------------------------");
		}

	}

	static final public void Programa() throws ParseException {
		MODIFICADOR();
		jj_consume_token(CLASS);
		jj_consume_token(IDENTIFIER);
		jj_consume_token(LBRACE);
		Principal();
		jj_consume_token(RBRACE);
	}

	static final public void MODIFICADOR() throws ParseException {
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case PUBLIC:
			jj_consume_token(PUBLIC);
			break;
		case PRIVATE:
			jj_consume_token(PRIVATE);
			break;
		default:
			jj_la1[0] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	static final public void DeclaracionCampo() throws ParseException {
		DeclaracionDeVariable();
	}

	static final public void DeclaracionDeVariable() throws ParseException {
		MODIFICADOR();
		String tipo = TIPO();
		DeclaradorVariable(tipo);
		jj_consume_token(SEMICOLON);
	}

	static final public String TIPO() throws ParseException {
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case BOOLEAN:
			return jj_consume_token(BOOLEAN).image;
		case INT:
			return jj_consume_token(INT).image;

		default:
			jj_la1[1] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	static final public void statement() throws ParseException {
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case PUBLIC:
		case PRIVATE:
			DeclaracionDeVariable();
			break;
		case IF:
			DeclaracionIF();
			break;
		case WHILE:
			DeclaracionWhile();
			break;
		default:
			jj_la1[2] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	static final public void DeclaradorVariable(String tipo) throws ParseException {
		String identifi = jj_consume_token(IDENTIFIER).image;
		jj_consume_token(ASIGNACION);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case INTEGER_LITERAL:
			Datos.add(new Identificador(identifi, jj_consume_token(INTEGER_LITERAL).image, tipo));
			break;
		case TRUE:
		case FALSE:
			Datos.add(new Identificador(identifi, boolean_literal(), tipo));
			break;
		default:
			jj_la1[3] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	static final public String boolean_literal() throws ParseException {
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case TRUE:
			return jj_consume_token(TRUE).image;

		case FALSE:
			return jj_consume_token(FALSE).image;

		default:
			jj_la1[4] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	static final public void DeclaracionIF() throws ParseException {
		jj_consume_token(IF);
		jj_consume_token(LPAREN);
		Comparaciones();
		jj_consume_token(RPAREN);
		statement();
	}

	static final public void DeclaracionWhile() throws ParseException {
		jj_consume_token(WHILE);
		jj_consume_token(LPAREN);
		Comparaciones();
		jj_consume_token(RPAREN);
		statement();
	}

	static final public void Principal() throws ParseException {
		Sentencias();
	}

	static final public void Comparaciones() throws ParseException {
		Valor();
		Operador();
		Valor();
	}

	static final public void Valor() throws ParseException {
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case INTEGER_LITERAL:
			jj_consume_token(INTEGER_LITERAL);
			break;
		case IDENTIFIER:
			jj_consume_token(IDENTIFIER);
			break;
		default:
			jj_la1[5] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	static final public void Sentencias() throws ParseException {
		DeclaracionCampo();
		statement();
	}

	static final public void Operador() throws ParseException {
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case MAYORQUE:
			jj_consume_token(MAYORQUE);
			break;
		case MENORQUE:
			jj_consume_token(MENORQUE);
			break;
		case MAYORIGUALQUE:
			jj_consume_token(MAYORIGUALQUE);
			break;
		case MENORIGUALQUE:
			jj_consume_token(MENORIGUALQUE);
			break;
		case IGUALQUE:
			jj_consume_token(IGUALQUE);
			break;
		case DIFERENTEDE:
			jj_consume_token(DIFERENTEDE);
			break;
		default:
			jj_la1[6] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	static private boolean jj_initialized_once = false;
	/** Generated Token Manager. */
	static public LenguajesProyTokenManager token_source;
	static SimpleCharStream jj_input_stream;
	/** Current token. */
	static public Token token;
	/** Next token. */
	static public Token jj_nt;
	static private int jj_ntk;
	static private int jj_gen;
	static final private int[] jj_la1 = new int[7];
	static private int[] jj_la1_0;
	static {
		jj_la1_init_0();
	}

	private static void jj_la1_init_0() {
		jj_la1_0 = new int[] { 0x6, 0xc0000, 0x36, 0x700000, 0x600000, 0x900000, 0x3f000, };
	}

	/** Constructor with InputStream. */
	public Lenguajes(java.io.InputStream stream) {
		this(stream, null);
	}

	/** Constructor with InputStream and supplied encoding */
	public Lenguajes(java.io.InputStream stream, String encoding) {
		if (jj_initialized_once) {
			System.out.println("ERROR: Segunda llamada al constructor del analizador est�tico. ");
			System.out.println("     	 Usted debe usar ReInit() o configurar el JavaCC option STATIC para falso");
			System.out.println("       durante la generaci�n del analizador.");
			throw new Error();
		}
		jj_initialized_once = true;
		try {
			jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source = new LenguajesProyTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 7; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	static public void ReInit(java.io.InputStream stream) {
		ReInit(stream, null);
	}

	/** Reinitialise. */
	static public void ReInit(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream.ReInit(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 7; i++)
			jj_la1[i] = -1;
	}

	/** Constructor. */
	public Lenguajes(java.io.Reader stream) {
		if (jj_initialized_once) {
			System.out.println("ERROR: Segunda llamada al constructor del analizador est�tico. ");
			System.out.println("     	 Usted debe usar ReInit() o configurar el JavaCC option STATIC para falso");
			System.out.println("       durante la generaci�n del analizador.");
			throw new Error();
		}
		jj_initialized_once = true;
		jj_input_stream = new SimpleCharStream(stream, 1, 1);
		token_source = new LenguajesProyTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 7; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	static public void ReInit(java.io.Reader stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 7; i++)
			jj_la1[i] = -1;
	}

	/** Constructor with generated Token Manager. */
	public Lenguajes(LenguajesProyTokenManager tm) {
		if (jj_initialized_once) {
			System.out.println("ERROR: Segunda llamada al constructor del analizador est�tico. ");
			System.out.println("     	 Usted debe usar ReInit() o configurar el JavaCC option STATIC para falso");
			System.out.println("       durante la generaci�n del analizador.");
			throw new Error();
		}
		jj_initialized_once = true;
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 7; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	public void ReInit(LenguajesProyTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 7; i++)
			jj_la1[i] = -1;
	}

	static private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		if (token.kind == kind) {
			jj_gen++;
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	/** Get the next Token. */
	static final public Token getNextToken() {
		if (token.next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	/** Get the specific Token. */
	static final public Token getToken(int index) {
		Token t = token;
		for (int i = 0; i < index; i++) {
			if (t.next != null)
				t = t.next;
			else
				t = t.next = token_source.getNextToken();
		}
		return t;
	}

	static private int jj_ntk() {
		if ((jj_nt = token.next) == null)
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		else
			return (jj_ntk = jj_nt.kind);
	}

	static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
	static private int[] jj_expentry;
	static private int jj_kind = -1;

	/** Generate ParseException. */
	static public ParseException generateParseException() {
		jj_expentries.clear();
		boolean[] la1tokens = new boolean[28];
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 7; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1 << j)) != 0) {
						la1tokens[j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 28; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.add(jj_expentry);
			}
		}
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++) {
			exptokseq[i] = jj_expentries.get(i);
		}
		return new ParseException(token, exptokseq, tokenImage);
	}

	/** Enable tracing. */
	static final public void enable_tracing() {
	}

	/** Disable tracing. */
	static final public void disable_tracing() {
	}

}
