/* The following code was generated by JFlex 1.4.3 on 10/17/18 9:37 PM */

/*CODIGO DE USUARIO*/
package DasmPackage.Analizador;
import java_cup.runtime.*;
import ErrorManager.TError;
import InfoEstatica.Estatico;
/*OPCIONES Y DECLARACION*/

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 10/17/18 9:37 PM from the specification file
 * <tt>lexico.jflex</tt>
 */
public class DasmLex implements java_cup.runtime.Scanner {

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
    "\11\0\1\54\1\53\1\0\2\54\22\0\1\54\3\0\1\3\5\0"+
    "\1\7\3\0\1\2\1\6\12\1\7\0\1\10\1\30\1\52\1\11"+
    "\1\23\1\13\1\22\1\4\1\12\2\4\1\16\1\14\1\26\1\21"+
    "\1\46\1\24\1\27\1\51\1\17\1\15\1\20\3\4\1\25\4\0"+
    "\1\5\1\0\1\37\1\41\1\36\1\45\1\32\1\4\1\31\1\4"+
    "\1\44\2\4\1\34\1\4\1\43\1\35\2\4\1\47\1\40\1\33"+
    "\1\42\1\50\4\4\uff85\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\20\1\1\3\2\4\1\5\1\0"+
    "\10\6\1\7\1\10\6\0\1\11\1\12\1\13\3\0"+
    "\1\14\4\0\1\15\7\6\1\16\1\17\1\0\1\20"+
    "\2\0\1\21\1\22\1\23\1\24\1\25\1\26\5\0"+
    "\5\6\1\27\1\6\1\30\1\0\1\31\4\0\1\32"+
    "\1\33\1\34\1\6\1\35\2\6\1\0\1\36\6\0"+
    "\1\6\1\37\1\6\7\0\1\6\1\40\7\0\1\6"+
    "\1\41\6\0\1\42\1\0\1\43\1\0\1\44\1\0"+
    "\1\45\1\46\1\47\1\50";

  private static int [] zzUnpackAction() {
    int [] result = new int[144];
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
    "\0\0\0\55\0\132\0\207\0\264\0\341\0\u010e\0\u013b"+
    "\0\u0168\0\u0195\0\u01c2\0\u01ef\0\u021c\0\u0249\0\u0276\0\u02a3"+
    "\0\u02d0\0\u02fd\0\u032a\0\u0357\0\u0384\0\207\0\207\0\u03b1"+
    "\0\207\0\u03de\0\u040b\0\u0438\0\u0465\0\u0492\0\u04bf\0\u04ec"+
    "\0\u0519\0\u0546\0\207\0\207\0\u0573\0\u05a0\0\u05cd\0\u05fa"+
    "\0\u0627\0\u0654\0\u0681\0\207\0\u06ae\0\u06db\0\u0708\0\u0735"+
    "\0\u0762\0\u078f\0\u07bc\0\u07e9\0\u0816\0\u03de\0\u0843\0\u0870"+
    "\0\u089d\0\u08ca\0\u08f7\0\u0924\0\u0951\0\207\0\207\0\u097e"+
    "\0\207\0\u09ab\0\u09d8\0\207\0\207\0\207\0\207\0\207"+
    "\0\207\0\u0a05\0\u0a32\0\u0a5f\0\u0a8c\0\u0ab9\0\u0ae6\0\u0b13"+
    "\0\u0b40\0\u0b6d\0\u0b9a\0\u040b\0\u0bc7\0\207\0\u0bf4\0\207"+
    "\0\u0c21\0\u0c4e\0\u0c7b\0\u0ca8\0\207\0\u040b\0\u040b\0\u0cd5"+
    "\0\u040b\0\u0d02\0\u0d2f\0\u0d5c\0\207\0\u0d89\0\u0db6\0\u0de3"+
    "\0\u0e10\0\u0e3d\0\u0e6a\0\u0e97\0\u040b\0\u0ec4\0\u0ef1\0\u0f1e"+
    "\0\u0f4b\0\u0f78\0\u0fa5\0\u0fd2\0\u0fff\0\u102c\0\u040b\0\u1059"+
    "\0\u1086\0\u10b3\0\u10e0\0\u110d\0\u113a\0\u1167\0\u1194\0\207"+
    "\0\u11c1\0\u11ee\0\u121b\0\u1248\0\u1275\0\u12a2\0\u040b\0\u12cf"+
    "\0\207\0\u12fc\0\207\0\u1329\0\207\0\207\0\207\0\207";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[144];
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
    "\1\4\1\5\1\4\1\6\2\4\1\7\1\4\1\10"+
    "\1\11\1\4\1\12\1\13\1\4\1\14\2\4\1\15"+
    "\1\16\1\17\2\4\1\20\1\4\1\21\1\22\1\4"+
    "\1\23\4\4\1\24\11\4\1\25\2\26\7\27\1\30"+
    "\43\27\1\26\54\27\1\31\1\27\56\0\1\5\1\32"+
    "\56\0\2\33\2\0\6\33\1\34\2\33\1\35\2\33"+
    "\1\36\11\33\1\37\7\33\1\40\1\41\1\33\1\42"+
    "\1\33\10\0\1\43\1\44\56\0\1\45\14\0\1\46"+
    "\40\0\1\47\104\0\1\50\27\0\1\51\3\0\1\52"+
    "\52\0\1\53\64\0\1\54\44\0\1\55\61\0\1\56"+
    "\16\0\1\57\32\0\1\60\62\0\1\61\57\0\1\62"+
    "\54\0\1\63\54\0\1\64\61\0\1\65\23\0\1\31"+
    "\47\0\1\66\54\0\1\33\2\0\2\33\2\0\43\33"+
    "\3\0\1\33\2\0\2\33\2\0\34\33\1\67\6\33"+
    "\3\0\1\33\2\0\2\33\2\0\40\33\1\70\2\33"+
    "\3\0\1\33\2\0\2\33\2\0\32\33\1\71\10\33"+
    "\3\0\1\33\2\0\2\33\2\0\27\33\1\72\13\33"+
    "\3\0\1\33\2\0\2\33\2\0\25\33\1\73\15\33"+
    "\3\0\1\33\2\0\2\33\2\0\22\33\1\74\20\33"+
    "\3\0\1\33\2\0\2\33\2\0\23\33\1\75\17\33"+
    "\13\0\1\76\54\0\1\77\56\0\1\100\4\0\1\101"+
    "\77\0\1\102\27\0\1\103\47\0\1\104\66\0\1\105"+
    "\54\0\1\106\56\0\1\107\74\0\1\110\26\0\1\111"+
    "\42\0\1\112\102\0\1\113\53\0\1\114\55\0\1\115"+
    "\55\0\1\116\21\0\1\33\2\0\2\33\2\0\33\33"+
    "\1\117\7\33\3\0\1\33\2\0\2\33\2\0\27\33"+
    "\1\120\13\33\3\0\1\33\2\0\2\33\2\0\27\33"+
    "\1\121\13\33\3\0\1\33\2\0\2\33\2\0\24\33"+
    "\1\122\16\33\3\0\1\33\2\0\2\33\2\0\34\33"+
    "\1\123\6\33\3\0\1\33\2\0\2\33\2\0\23\33"+
    "\1\124\17\33\3\0\1\33\2\0\2\33\2\0\37\33"+
    "\1\125\3\33\15\0\1\126\77\0\1\127\35\0\1\130"+
    "\47\0\1\131\47\0\1\132\54\0\1\133\54\0\1\134"+
    "\103\0\1\135\21\0\1\33\2\0\2\33\2\0\22\33"+
    "\1\136\20\33\3\0\1\33\2\0\2\33\2\0\24\33"+
    "\1\137\16\33\3\0\1\33\2\0\2\33\2\0\35\33"+
    "\1\140\5\33\3\0\1\33\2\0\2\33\2\0\26\33"+
    "\1\141\14\33\3\0\1\33\2\0\2\33\2\0\33\33"+
    "\1\142\7\33\3\0\1\33\2\0\2\33\2\0\34\33"+
    "\1\143\6\33\35\0\1\144\34\0\1\145\72\0\1\146"+
    "\2\0\1\147\51\0\1\150\2\0\1\151\51\0\1\152"+
    "\2\0\1\153\21\0\1\33\2\0\2\33\2\0\37\33"+
    "\1\154\3\33\3\0\1\33\2\0\2\33\2\0\23\33"+
    "\1\155\17\33\3\0\1\33\2\0\2\33\2\0\33\33"+
    "\1\156\7\33\46\0\1\157\44\0\1\160\55\0\1\161"+
    "\53\0\1\162\55\0\1\163\53\0\1\164\55\0\1\165"+
    "\20\0\1\33\2\0\2\33\2\0\27\33\1\166\13\33"+
    "\3\0\1\33\2\0\2\33\2\0\21\33\1\167\21\33"+
    "\37\0\1\170\54\0\1\171\55\0\1\172\53\0\1\173"+
    "\55\0\1\174\53\0\1\175\55\0\1\176\17\0\1\33"+
    "\2\0\2\33\2\0\23\33\1\177\17\33\45\0\1\200"+
    "\52\0\1\201\52\0\1\202\56\0\1\203\52\0\1\204"+
    "\56\0\1\205\52\0\1\206\16\0\1\33\2\0\2\33"+
    "\2\0\22\33\1\207\20\33\41\0\1\210\51\0\1\211"+
    "\57\0\1\212\51\0\1\213\57\0\1\214\51\0\1\215"+
    "\54\0\1\216\54\0\1\217\54\0\1\220\20\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4950];
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
    "\3\0\1\11\21\1\2\11\1\1\1\11\1\0\10\1"+
    "\2\11\6\0\1\1\1\11\1\1\3\0\1\1\4\0"+
    "\10\1\2\11\1\0\1\11\2\0\6\11\5\0\7\1"+
    "\1\11\1\0\1\11\4\0\1\11\6\1\1\0\1\11"+
    "\6\0\3\1\7\0\2\1\7\0\1\1\1\11\6\0"+
    "\1\1\1\0\1\11\1\0\1\11\1\0\4\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[144];
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


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public DasmLex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public DasmLex(java.io.InputStream in) {
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
    while (i < 128) {
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
        case 24: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.diff, yycolumn, yyline, yytext());
          }
        case 41: break;
        case 6: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.identificador, yycolumn, yyline, yytext());
          }
        case 42: break;
        case 25: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.mult, yycolumn, yyline, yytext());
          }
        case 43: break;
        case 13: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.decimal, yycolumn, yyline, yytext());
          }
        case 44: break;
        case 37: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.slocal, yycolumn, yyline, yytext());
          }
        case 45: break;
        case 22: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.not, yycolumn, yyline, yytext());
          }
        case 46: break;
        case 33: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.function, yycolumn, yyline, yytext());
          }
        case 47: break;
        case 35: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.glocal, yycolumn, yyline, yytext());
          }
        case 48: break;
        case 32: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.str, yycolumn, yyline, yytext());
          }
        case 49: break;
        case 27: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.line, yycolumn, yyline, yytext());
          }
        case 50: break;
        case 20: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.eqz, yycolumn, yyline, yytext());
          }
        case 51: break;
        case 31: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.point, yycolumn, yyline, yytext());
          }
        case 52: break;
        case 9: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.lt, yycolumn, yyline, yytext());
          }
        case 53: break;
        case 5: 
          { yybegin(YYINITIAL);
          }
        case 54: break;
        case 2: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.entero, yycolumn, yyline, yytext());
          }
        case 55: break;
        case 15: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.and, yycolumn, yyline, yytext());
          }
        case 56: break;
        case 12: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.br, yycolumn, yyline, yytext());
          }
        case 57: break;
        case 3: 
          { /*NO HAGO NADA*/
          }
        case 58: break;
        case 39: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.tglobal, yycolumn, yyline, yytext());
          }
        case 59: break;
        case 11: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.gt, yycolumn, yyline, yytext());
          }
        case 60: break;
        case 8: 
          { yybegin(MULTICOMMENT);
          }
        case 61: break;
        case 19: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.gte, yycolumn, yyline, yytext());
          }
        case 62: break;
        case 10: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.or, yycolumn, yyline, yytext());
          }
        case 63: break;
        case 28: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.oval, yycolumn, yyline, yytext());
          }
        case 64: break;
        case 7: 
          { yybegin(LINECOMMENT);
          }
        case 65: break;
        case 34: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.quadrate, yycolumn, yyline, yytext());
          }
        case 66: break;
        case 18: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.lte, yycolumn,yyline, yytext());
          }
        case 67: break;
        case 14: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.add, yycolumn, yyline, yytext());
          }
        case 68: break;
        case 26: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.llama, yycolumn, yyline, yytext());
          }
        case 69: break;
        case 36: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.tlocal, yycolumn, yyline, yytext());
          }
        case 70: break;
        case 38: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.gglobal, yycolumn, yyline,yytext());
          }
        case 71: break;
        case 17: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.mod, yycolumn, yyline, yytext());
          }
        case 72: break;
        case 30: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.brIf, yycolumn, yyline, yytext());
          }
        case 73: break;
        case 16: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.div, yycolumn, yyline, yytext());
          }
        case 74: break;
        case 29: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.calc, yycolumn, yyline, yytext());
          }
        case 75: break;
        case 4: 
          { /*NO HAY NADA*/
          }
        case 76: break;
        case 1: 
          { TError error = new TError("Caracter: "+yytext(), "Error Lexico, Caracter no reconocido", "Lexico", yyline, yycolumn, false, this.nombreArchivo);
                                                                Estatico.agregarError(error);
          }
        case 77: break;
        case 40: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.sglobal, yycolumn, yyline, yytext());
          }
        case 78: break;
        case 23: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.ret, yycolumn, yyline, yytext());
          }
        case 79: break;
        case 21: 
          { return new Symbol(DasmPackage.Analizador.Simbolos.end, yycolumn, yyline, yytext());
          }
        case 80: break;
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
