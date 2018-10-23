/* The following code was generated by JFlex 1.4.3 on 10/21/18 8:00 PM */

/*CODIGO DE USUARIO*/
package DppPackage.Analizador;
import java_cup.runtime.*;
import ErrorManager.TError;
import InfoEstatica.Estatico;
/*OPCIONES Y DECLARACION*/

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 10/21/18 8:00 PM from the specification file
 * <tt>lexico.jflex</tt>
 */
public class DPPLex implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int LINECOMMENT = 4;
  public static final int MULTICOMMENT = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\51\1\5\1\0\2\51\22\0\1\51\1\30\1\7\3\0"+
    "\1\26\1\4\1\14\1\15\1\11\1\21\1\16\1\22\1\2\1\10"+
    "\12\1\1\0\1\12\1\24\1\13\1\25\2\0\32\3\1\0\1\6"+
    "\1\0\1\23\1\3\1\0\1\42\1\44\1\37\1\36\1\31\1\46"+
    "\2\3\1\40\2\3\1\43\1\41\1\32\1\35\1\50\1\3\1\34"+
    "\1\47\1\33\1\3\1\45\4\3\1\17\1\27\1\20\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\1\1\1\4\1\1\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\2\1\1\23\12\3"+
    "\1\4\1\24\4\0\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\11\3\1\40"+
    "\1\3\1\41\1\42\1\43\26\3\1\44\11\3\1\45"+
    "\1\46\1\3\1\47\3\3\1\50\5\3\1\51\5\3"+
    "\1\52\1\53\1\54\1\55\2\3\1\56\1\57";

  private static int [] zzUnpackAction() {
    int [] result = new int[126];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\52\0\124\0\176\0\250\0\322\0\374\0\176"+
    "\0\u0126\0\u0150\0\176\0\176\0\u017a\0\176\0\176\0\176"+
    "\0\176\0\176\0\u01a4\0\u01ce\0\176\0\u01f8\0\u0222\0\u024c"+
    "\0\u0276\0\176\0\u02a0\0\u02ca\0\u02f4\0\u031e\0\u0348\0\u0372"+
    "\0\u039c\0\u03c6\0\u03f0\0\u041a\0\u0444\0\176\0\u046e\0\u0498"+
    "\0\u04c2\0\u0126\0\176\0\176\0\176\0\176\0\176\0\176"+
    "\0\176\0\176\0\176\0\176\0\176\0\u04ec\0\u0516\0\u0540"+
    "\0\u056a\0\u0594\0\u05be\0\u05e8\0\u0612\0\u063c\0\u0666\0\u0690"+
    "\0\u046e\0\176\0\176\0\u06ba\0\u06e4\0\u070e\0\u0738\0\u0762"+
    "\0\u078c\0\u07b6\0\u07e0\0\u080a\0\u0834\0\u085e\0\u0888\0\u08b2"+
    "\0\u08dc\0\u0906\0\u0930\0\u095a\0\u0984\0\u09ae\0\u09d8\0\u0a02"+
    "\0\u0a2c\0\322\0\u0a56\0\u0a80\0\u0aaa\0\u0ad4\0\u0afe\0\u0b28"+
    "\0\u0b52\0\u0b7c\0\u0ba6\0\322\0\322\0\u0bd0\0\322\0\u0bfa"+
    "\0\u0c24\0\u0c4e\0\322\0\u0c78\0\u0ca2\0\u0ccc\0\u0cf6\0\u0d20"+
    "\0\322\0\u0d4a\0\u0d74\0\u0d9e\0\u0dc8\0\u0df2\0\322\0\322"+
    "\0\322\0\322\0\u0e1c\0\u0e46\0\322\0\322";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[126];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\4\1\6\1\7\1\10\1\4\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\2\6\1\34\1\6\1\35\1\36\1\37"+
    "\3\6\1\40\1\41\1\42\1\43\1\44\12\10\1\45"+
    "\45\10\1\46\44\10\53\0\1\5\1\47\50\0\1\6"+
    "\1\0\1\6\25\0\20\6\1\0\5\50\1\51\44\50"+
    "\5\52\2\0\1\53\42\52\10\0\1\54\1\55\53\0"+
    "\1\56\57\0\1\57\52\0\1\60\42\0\1\61\11\0"+
    "\1\62\37\0\1\63\64\0\1\64\52\0\1\65\23\0"+
    "\1\6\1\0\1\6\25\0\1\6\1\66\16\6\2\0"+
    "\1\6\1\0\1\6\25\0\1\67\17\6\2\0\1\6"+
    "\1\0\1\6\25\0\1\70\17\6\2\0\1\6\1\0"+
    "\1\6\25\0\11\6\1\71\6\6\2\0\1\6\1\0"+
    "\1\6\25\0\10\6\1\72\7\6\2\0\1\6\1\0"+
    "\1\6\25\0\4\6\1\73\13\6\2\0\1\6\1\0"+
    "\1\6\25\0\1\74\10\6\1\75\6\6\2\0\1\6"+
    "\1\0\1\6\25\0\11\6\1\76\6\6\2\0\1\6"+
    "\1\0\1\6\25\0\7\6\1\77\10\6\2\0\1\6"+
    "\1\0\1\6\25\0\3\6\1\100\14\6\11\0\1\46"+
    "\42\0\1\101\54\0\1\102\51\0\1\103\46\0\1\6"+
    "\1\0\1\6\25\0\2\6\1\104\15\6\2\0\1\6"+
    "\1\0\1\6\25\0\2\6\1\105\15\6\2\0\1\6"+
    "\1\0\1\6\25\0\6\6\1\106\11\6\2\0\1\6"+
    "\1\0\1\6\25\0\3\6\1\107\1\6\1\110\12\6"+
    "\2\0\1\6\1\0\1\6\25\0\17\6\1\111\2\0"+
    "\1\6\1\0\1\6\25\0\4\6\1\112\13\6\2\0"+
    "\1\6\1\0\1\6\25\0\3\6\1\113\14\6\2\0"+
    "\1\6\1\0\1\6\25\0\6\6\1\114\11\6\2\0"+
    "\1\6\1\0\1\6\25\0\12\6\1\115\5\6\2\0"+
    "\1\6\1\0\1\6\25\0\1\6\1\116\16\6\2\0"+
    "\1\6\1\0\1\6\25\0\7\6\1\117\10\6\2\0"+
    "\1\6\1\0\1\6\25\0\1\120\17\6\2\0\1\6"+
    "\1\0\1\6\25\0\4\6\1\121\13\6\2\0\1\6"+
    "\1\0\1\6\25\0\7\6\1\122\10\6\2\0\1\6"+
    "\1\0\1\6\25\0\11\6\1\123\6\6\2\0\1\6"+
    "\1\0\1\6\25\0\1\124\17\6\2\0\1\6\1\0"+
    "\1\6\25\0\4\6\1\125\13\6\2\0\1\6\1\0"+
    "\1\6\25\0\12\6\1\126\5\6\2\0\1\6\1\0"+
    "\1\6\25\0\5\6\1\127\12\6\2\0\1\6\1\0"+
    "\1\6\25\0\7\6\1\130\10\6\2\0\1\6\1\0"+
    "\1\6\25\0\16\6\1\131\1\6\2\0\1\6\1\0"+
    "\1\6\25\0\4\6\1\132\13\6\2\0\1\6\1\0"+
    "\1\6\25\0\1\6\1\133\16\6\2\0\1\6\1\0"+
    "\1\6\25\0\3\6\1\134\14\6\2\0\1\6\1\0"+
    "\1\6\25\0\3\6\1\135\14\6\2\0\1\6\1\0"+
    "\1\6\25\0\10\6\1\136\7\6\2\0\1\6\1\0"+
    "\1\6\25\0\6\6\1\137\11\6\2\0\1\6\1\0"+
    "\1\6\25\0\1\6\1\140\16\6\2\0\1\6\1\0"+
    "\1\6\25\0\3\6\1\141\14\6\2\0\1\6\1\0"+
    "\1\6\25\0\1\142\17\6\2\0\1\6\1\0\1\6"+
    "\25\0\11\6\1\143\6\6\2\0\1\6\1\0\1\6"+
    "\25\0\4\6\1\144\13\6\2\0\1\6\1\0\1\6"+
    "\25\0\4\6\1\145\13\6\2\0\1\6\1\0\1\6"+
    "\25\0\6\6\1\146\11\6\2\0\1\6\1\0\1\6"+
    "\25\0\4\6\1\147\13\6\2\0\1\6\1\0\1\6"+
    "\25\0\1\6\1\150\16\6\2\0\1\6\1\0\1\6"+
    "\25\0\11\6\1\151\6\6\2\0\1\6\1\0\1\6"+
    "\25\0\2\6\1\152\15\6\2\0\1\6\1\0\1\6"+
    "\25\0\11\6\1\153\6\6\2\0\1\6\1\0\1\6"+
    "\25\0\2\6\1\154\15\6\2\0\1\6\1\0\1\6"+
    "\25\0\11\6\1\155\6\6\2\0\1\6\1\0\1\6"+
    "\25\0\5\6\1\156\12\6\2\0\1\6\1\0\1\6"+
    "\25\0\7\6\1\157\10\6\2\0\1\6\1\0\1\6"+
    "\25\0\11\6\1\160\6\6\2\0\1\6\1\0\1\6"+
    "\25\0\12\6\1\161\5\6\2\0\1\6\1\0\1\6"+
    "\25\0\1\162\17\6\2\0\1\6\1\0\1\6\25\0"+
    "\11\6\1\163\6\6\2\0\1\6\1\0\1\6\25\0"+
    "\1\6\1\164\16\6\2\0\1\6\1\0\1\6\25\0"+
    "\1\165\17\6\2\0\1\6\1\0\1\6\25\0\17\6"+
    "\1\166\2\0\1\6\1\0\1\6\25\0\3\6\1\167"+
    "\14\6\2\0\1\6\1\0\1\6\25\0\3\6\1\170"+
    "\14\6\2\0\1\6\1\0\1\6\25\0\3\6\1\171"+
    "\14\6\2\0\1\6\1\0\1\6\25\0\4\6\1\172"+
    "\13\6\2\0\1\6\1\0\1\6\25\0\3\6\1\173"+
    "\14\6\2\0\1\6\1\0\1\6\25\0\11\6\1\174"+
    "\6\6\2\0\1\6\1\0\1\6\25\0\4\6\1\175"+
    "\13\6\2\0\1\6\1\0\1\6\25\0\12\6\1\176"+
    "\5\6\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3696];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\3\1\1\11\2\1\2\11\1\1\5\11"+
    "\2\1\1\11\4\1\1\11\13\1\1\11\4\0\13\11"+
    "\14\1\2\11\73\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[126];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    String nombreArchivo;
    public void setNombreArchivo(String nombreArchivo)
    {
      this.nombreArchivo = nombreArchivo;
    }

    private String quitarDeTexto(String quita, String cadena)
    {
      cadena = cadena.replace(quita, "");
      return cadena;
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public DPPLex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public DPPLex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 118) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 46: 
          { return new Symbol(DppPackage.Analizador.Simbolos.verdadero, yycolumn, yyline, yytext());
          }
        case 48: break;
        case 21: 
          { return new Symbol(DppPackage.Analizador.Simbolos.dataString, yycolumn, yyline, yytext());
          }
        case 49: break;
        case 11: 
          { return new Symbol(DppPackage.Analizador.Simbolos.coma, yycolumn, yyline, yytext());
          }
        case 50: break;
        case 8: 
          { return new Symbol(DppPackage.Analizador.Simbolos.asigna, yycolumn, yyline, yytext());
          }
        case 51: break;
        case 36: 
          { return new Symbol(DppPackage.Analizador.Simbolos.sino, yycolumn, yyline, yytext());
          }
        case 52: break;
        case 5: 
          { return new Symbol(DppPackage.Analizador.Simbolos.div, yycolumn, yyline, yytext());
          }
        case 53: break;
        case 16: 
          { return new Symbol(DppPackage.Analizador.Simbolos.pot, yycolumn, yyline, yytext());
          }
        case 54: break;
        case 47: 
          { return new Symbol(DppPackage.Analizador.Simbolos.principal, yycolumn, yyline, yytext());
          }
        case 55: break;
        case 28: 
          { return new Symbol(DppPackage.Analizador.Simbolos.diff, yycolumn, yyline, yytext());
          }
        case 56: break;
        case 6: 
          { return new Symbol(DppPackage.Analizador.Simbolos.mult, yycolumn, yyline, yytext());
          }
        case 57: break;
        case 13: 
          { return new Symbol(DppPackage.Analizador.Simbolos.cKey, yycolumn, yyline, yytext());
          }
        case 58: break;
        case 43: 
          { return new Symbol(DppPackage.Analizador.Simbolos.caracter, yycolumn, yyline, yytext());
          }
        case 59: break;
        case 31: 
          { return new Symbol(DppPackage.Analizador.Simbolos.or, yycolumn, yyline, yytext());
          }
        case 60: break;
        case 41: 
          { return new Symbol(DppPackage.Analizador.Simbolos.decimal, yycolumn, yyline, yytext());
          }
        case 61: break;
        case 20: 
          { yybegin(YYINITIAL);
          }
        case 62: break;
        case 45: 
          { return new Symbol(DppPackage.Analizador.Simbolos.booleano, yycolumn, yyline, yytext());
          }
        case 63: break;
        case 26: 
          { return new Symbol(DppPackage.Analizador.Simbolos.dec, yycolumn, yyline, yytext());
          }
        case 64: break;
        case 2: 
          { return new Symbol(DppPackage.Analizador.Simbolos.numEntero, yycolumn, yyline, yytext());
          }
        case 65: break;
        case 9: 
          { return new Symbol(DppPackage.Analizador.Simbolos.oParent, yycolumn, yyline, yytext());
          }
        case 66: break;
        case 19: 
          { return new Symbol(DppPackage.Analizador.Simbolos.not, yycolumn, yyline, yytext());
          }
        case 67: break;
        case 4: 
          { /*NO HAGO NADA*/
          }
        case 68: break;
        case 23: 
          { yybegin(MULTICOMMENT);
          }
        case 69: break;
        case 44: 
          { return new Symbol(DppPackage.Analizador.Simbolos.importar, yycolumn, yyline, yytext());
          }
        case 70: break;
        case 29: 
          { return new Symbol(DppPackage.Analizador.Simbolos.mayorigual, yycolumn, yyline, yytext());
          }
        case 71: break;
        case 37: 
          { return new Symbol(DppPackage.Analizador.Simbolos.vacio, yycolumn, yyline, yytext());
          }
        case 72: break;
        case 34: 
          { return new Symbol(DppPackage.Analizador.Simbolos.dataChar, yycolumn, yyline, quitarDeTexto("\'",yytext()));
          }
        case 73: break;
        case 22: 
          { yybegin(LINECOMMENT);
          }
        case 74: break;
        case 32: 
          { return new Symbol(DppPackage.Analizador.Simbolos.si, yycolumn, yyline, yytext());
          }
        case 75: break;
        case 7: 
          { return new Symbol(DppPackage.Analizador.Simbolos.ptComa, yycolumn, yyline, yytext());
          }
        case 76: break;
        case 39: 
          { return new Symbol(DppPackage.Analizador.Simbolos.entero, yycolumn, yyline, yytext());
          }
        case 77: break;
        case 24: 
          { return new Symbol(DppPackage.Analizador.Simbolos.igual, yycolumn, yyline, yytext());
          }
        case 78: break;
        case 42: 
          { return new Symbol(DppPackage.Analizador.Simbolos.retornar, yycolumn, yyline, yytext());
          }
        case 79: break;
        case 33: 
          { return new Symbol(DppPackage.Analizador.Simbolos.numDecimal, yycolumn, yyline, yytext());
          }
        case 80: break;
        case 14: 
          { return new Symbol(DppPackage.Analizador.Simbolos.mas, yycolumn, yyline, yytext());
          }
        case 81: break;
        case 25: 
          { return new Symbol(DppPackage.Analizador.Simbolos.inc, yycolumn, yyline, yytext());
          }
        case 82: break;
        case 30: 
          { return new Symbol(DppPackage.Analizador.Simbolos.and, yycolumn, yyline, yytext());
          }
        case 83: break;
        case 38: 
          { return new Symbol(DppPackage.Analizador.Simbolos.falso, yycolumn, yyline, yytext());
          }
        case 84: break;
        case 10: 
          { return new Symbol(DppPackage.Analizador.Simbolos.cParent, yycolumn, yyline, yytext());
          }
        case 85: break;
        case 3: 
          { return new Symbol(DppPackage.Analizador.Simbolos.identificador, yycolumn, yyline, yytext());
          }
        case 86: break;
        case 17: 
          { return new Symbol(DppPackage.Analizador.Simbolos.menor, yycolumn, yyline, yytext());
          }
        case 87: break;
        case 40: 
          { return new Symbol(DppPackage.Analizador.Simbolos.cadena, yycolumn, yyline, yytext());
          }
        case 88: break;
        case 35: 
          { return new Symbol(DppPackage.Analizador.Simbolos.dataChar, yycolumn, yyline, quitarDeTexto("\"",yytext()));
          }
        case 89: break;
        case 15: 
          { return new Symbol(DppPackage.Analizador.Simbolos.menos, yycolumn, yyline, yytext());
          }
        case 90: break;
        case 18: 
          { return new Symbol(DppPackage.Analizador.Simbolos.mayor, yycolumn, yyline, yytext());
          }
        case 91: break;
        case 27: 
          { return new Symbol(DppPackage.Analizador.Simbolos.menorigual, yycolumn, yyline, yytext());
          }
        case 92: break;
        case 1: 
          { TError error = new TError("Caracter: "+yytext(), "Error Lexico, Caracter no reconocido", "Lexico", yyline, yycolumn, false, this.nombreArchivo);
                                                                Estatico.agregarError(error);
          }
        case 93: break;
        case 12: 
          { return new Symbol(DppPackage.Analizador.Simbolos.oKey, yycolumn, yyline, yytext());
          }
        case 94: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(Simbolos.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
