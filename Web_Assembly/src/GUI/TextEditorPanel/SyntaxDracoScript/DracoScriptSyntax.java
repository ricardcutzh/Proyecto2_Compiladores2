/* The following code was generated by JFlex 1.4.1 on 9/26/18 4:04 PM */

package GUI.TextEditorPanel.SyntaxDracoScript;
import java.io.*;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 9/26/18 4:04 PM from the specification file
 * <tt>DracoSyntax.jflex</tt>
 */
public class DracoScriptSyntax extends AbstractJFlexCTokenMaker {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int MLC = 1;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\7\1\10\1\0\1\7\1\6\22\0\1\7\1\46\1\4"+
    "\1\0\1\11\1\44\1\51\1\3\2\13\1\12\1\42\1\6\1\43"+
    "\1\6\1\44\12\2\1\6\1\6\1\47\1\45\1\50\1\6\1\0"+
    "\1\15\1\33\1\35\1\36\1\21\1\20\1\41\1\30\1\17\2\1"+
    "\1\22\1\27\1\23\1\24\1\37\1\40\1\16\1\26\1\25\1\31"+
    "\1\14\1\32\1\1\1\34\1\1\1\13\1\5\1\13\1\44\1\1"+
    "\1\0\1\15\1\33\1\35\1\36\1\21\1\20\1\41\1\30\1\17"+
    "\2\1\1\22\1\27\1\23\1\24\1\37\1\40\1\16\1\26\1\25"+
    "\1\31\1\14\1\32\1\1\1\34\1\1\1\13\1\52\1\13\1\6"+
    "\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\2\1\1\2\1\3\1\4\1\5\1\6\1\1"+
    "\1\7\1\10\20\1\2\7\1\1\1\7\3\1\1\11"+
    "\1\12\1\11\1\13\1\3\1\14\1\3\1\0\1\15"+
    "\2\4\1\16\1\17\2\1\1\20\20\1\1\0\1\21"+
    "\1\22\1\23\1\1\1\24\20\1\1\25\20\1";

  private static int [] zzUnpackAction() {
    int [] result = new int[106];
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
    "\0\0\0\53\0\126\0\201\0\254\0\327\0\u0102\0\u012d"+
    "\0\126\0\u0158\0\126\0\126\0\u0183\0\u01ae\0\u01d9\0\u0204"+
    "\0\u022f\0\u025a\0\u0285\0\u02b0\0\u02db\0\u0306\0\u0331\0\u035c"+
    "\0\u0387\0\u03b2\0\u03dd\0\u0408\0\u0433\0\u045e\0\u0489\0\u0489"+
    "\0\u04b4\0\u04df\0\u050a\0\u0535\0\126\0\u0560\0\u058b\0\u05b6"+
    "\0\126\0\u05e1\0\u060c\0\126\0\u0637\0\u0662\0\u068d\0\126"+
    "\0\u06b8\0\u06e3\0\201\0\u070e\0\u0739\0\u0764\0\u078f\0\u07ba"+
    "\0\u07e5\0\u0810\0\u083b\0\u0866\0\u0891\0\u08bc\0\u08e7\0\u0912"+
    "\0\u093d\0\u0968\0\u0993\0\u0489\0\126\0\126\0\126\0\u09be"+
    "\0\201\0\u09e9\0\u0a14\0\u0a3f\0\u0a6a\0\u0a95\0\u0ac0\0\u0aeb"+
    "\0\u0b16\0\u0b41\0\u0b6c\0\u0b97\0\u0bc2\0\u0bed\0\u0c18\0\u0c43"+
    "\0\u0c6e\0\201\0\u0c99\0\u0cc4\0\u0cef\0\u0d1a\0\u0d45\0\u0d70"+
    "\0\u0d9b\0\u0dc6\0\u0df1\0\u0e1c\0\u0e47\0\u0e72\0\u0e9d\0\u0ec8"+
    "\0\u0ef3\0\u0f1e";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[106];
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
    "\1\3\1\4\1\5\1\6\1\7\2\3\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\4\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\24\1\25\1\26\3\4\1\27"+
    "\1\30\1\4\1\31\1\32\1\33\1\34\1\4\1\35"+
    "\1\36\1\13\1\37\1\40\1\13\1\41\1\42\1\43"+
    "\10\44\1\45\1\44\1\46\40\44\54\0\2\4\11\0"+
    "\26\4\11\0\2\47\1\5\2\0\1\47\3\0\1\47"+
    "\2\0\26\47\11\0\3\50\1\51\1\50\1\52\2\50"+
    "\1\53\42\50\4\7\1\54\1\55\2\7\1\56\42\7"+
    "\7\0\1\10\54\0\1\57\1\60\41\0\2\4\11\0"+
    "\1\4\1\61\24\4\12\0\2\4\11\0\15\4\1\62"+
    "\10\4\12\0\2\4\11\0\4\4\1\63\2\4\1\64"+
    "\16\4\12\0\2\4\11\0\1\4\1\65\4\4\1\66"+
    "\1\4\1\67\15\4\12\0\2\4\11\0\6\4\1\70"+
    "\17\4\12\0\2\4\11\0\3\4\1\71\22\4\12\0"+
    "\2\4\11\0\10\4\1\72\15\4\12\0\2\4\11\0"+
    "\1\73\25\4\12\0\2\4\11\0\2\4\1\74\23\4"+
    "\12\0\2\4\11\0\11\4\1\75\14\4\12\0\2\4"+
    "\11\0\14\4\1\76\11\4\12\0\2\4\11\0\20\4"+
    "\1\77\5\4\12\0\2\4\11\0\14\4\1\100\11\4"+
    "\12\0\2\4\11\0\10\4\1\101\15\4\12\0\2\4"+
    "\11\0\2\4\1\102\5\4\1\102\15\4\12\0\2\4"+
    "\11\0\15\4\1\103\10\4\53\0\1\13\53\0\1\13"+
    "\54\0\1\13\52\0\1\13\1\0\1\104\54\0\1\13"+
    "\53\0\1\13\10\44\1\0\1\44\1\0\40\44\11\0"+
    "\1\105\41\0\3\47\2\0\1\47\3\0\1\47\2\0"+
    "\26\47\11\0\3\52\1\106\4\52\1\0\45\52\1\51"+
    "\4\52\1\0\42\52\3\0\1\106\47\0\10\56\1\0"+
    "\46\56\1\107\1\55\45\56\10\57\1\0\42\57\1\0"+
    "\2\4\11\0\2\4\1\63\23\4\12\0\2\4\11\0"+
    "\7\4\1\110\16\4\12\0\2\4\11\0\11\4\1\111"+
    "\14\4\12\0\2\4\11\0\6\4\1\112\17\4\12\0"+
    "\2\4\11\0\10\4\1\113\15\4\12\0\2\4\11\0"+
    "\2\4\1\114\23\4\12\0\2\4\11\0\3\4\1\115"+
    "\22\4\12\0\2\4\11\0\7\4\1\116\16\4\12\0"+
    "\2\4\11\0\11\4\1\63\14\4\12\0\2\4\11\0"+
    "\1\4\1\117\24\4\12\0\2\4\11\0\15\4\1\120"+
    "\10\4\12\0\2\4\11\0\2\4\1\121\23\4\12\0"+
    "\2\4\11\0\3\4\1\122\22\4\12\0\2\4\11\0"+
    "\11\4\1\123\14\4\12\0\2\4\11\0\1\4\1\124"+
    "\24\4\12\0\2\4\11\0\15\4\1\125\10\4\12\0"+
    "\2\4\11\0\3\4\1\126\22\4\12\0\2\4\11\0"+
    "\1\4\1\127\24\4\12\0\2\4\11\0\13\4\1\130"+
    "\12\4\12\0\2\4\11\0\12\4\1\120\13\4\12\0"+
    "\2\4\11\0\1\4\1\64\24\4\12\0\2\4\11\0"+
    "\12\4\1\131\13\4\12\0\2\4\11\0\4\4\1\63"+
    "\21\4\12\0\2\4\11\0\5\4\1\132\20\4\12\0"+
    "\2\4\11\0\6\4\1\132\17\4\12\0\2\4\11\0"+
    "\5\4\1\63\20\4\12\0\2\4\11\0\3\4\1\133"+
    "\22\4\12\0\2\4\11\0\6\4\1\120\17\4\12\0"+
    "\2\4\11\0\5\4\1\111\20\4\12\0\2\4\11\0"+
    "\2\4\1\111\23\4\12\0\2\4\11\0\17\4\1\134"+
    "\6\4\12\0\2\4\11\0\7\4\1\135\16\4\12\0"+
    "\2\4\11\0\22\4\1\136\3\4\12\0\2\4\11\0"+
    "\15\4\1\137\10\4\12\0\2\4\11\0\13\4\1\140"+
    "\12\4\12\0\2\4\11\0\7\4\1\141\16\4\12\0"+
    "\2\4\11\0\6\4\1\123\17\4\12\0\2\4\11\0"+
    "\11\4\1\132\14\4\12\0\2\4\11\0\2\4\1\142"+
    "\23\4\12\0\2\4\11\0\6\4\1\143\17\4\12\0"+
    "\2\4\11\0\1\4\1\144\24\4\12\0\2\4\11\0"+
    "\25\4\1\132\12\0\2\4\11\0\1\4\1\145\24\4"+
    "\12\0\2\4\11\0\11\4\1\146\14\4\12\0\2\4"+
    "\11\0\12\4\1\147\13\4\12\0\2\4\11\0\11\4"+
    "\1\116\14\4\12\0\2\4\11\0\22\4\1\150\3\4"+
    "\12\0\2\4\11\0\14\4\1\63\11\4\12\0\2\4"+
    "\11\0\1\4\1\151\24\4\12\0\2\4\11\0\12\4"+
    "\1\152\13\4\12\0\2\4\11\0\13\4\1\132\12\4"+
    "\11\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3913];
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
    "\2\0\1\11\5\1\1\11\1\1\2\11\30\1\1\11"+
    "\3\1\1\11\1\1\1\0\1\11\3\1\1\11\23\1"+
    "\1\0\3\11\43\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[106];
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

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

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

  /* user code: */

   /**
    * Constructor.  This must be here because JFlex does not generate a
    * no-parameter constructor.
    */
   public DracoScriptSyntax() {
   }

   /**
    * Adds the token specified to the current linked list of tokens.
    *
    * @param tokenType The token's type.
    * @see #addToken(int, int, int)
    */
   private void addHyperlinkToken(int start, int end, int tokenType) {
      int so = start + offsetShift;
      addToken(zzBuffer, start,end, tokenType, so, true);
   }

   /**
    * Adds the token specified to the current linked list of tokens.
    *
    * @param tokenType The token's type.
    */
   private void addToken(int tokenType) {
      addToken(zzStartRead, zzMarkedPos-1, tokenType);
   }

   /**
    * Adds the token specified to the current linked list of tokens.
    *
    * @param tokenType The token's type.
    * @see #addHyperlinkToken(int, int, int)
    */
   private void addToken(int start, int end, int tokenType) {
      int so = start + offsetShift;
      addToken(zzBuffer, start,end, tokenType, so, false);
   }

   /**
    * Adds the token specified to the current linked list of tokens.
    *
    * @param array The character array.
    * @param start The starting offset in the array.
    * @param end The ending offset in the array.
    * @param tokenType The token's type.
    * @param startOffset The offset in the document at which this token
    *        occurs.
    * @param hyperlink Whether this token is a hyperlink.
    */
   public void addToken(char[] array, int start, int end, int tokenType,
                  int startOffset, boolean hyperlink) {
      super.addToken(array, start,end, tokenType, startOffset, hyperlink);
      zzStartRead = zzMarkedPos;
   }

   /**
    * Returns the text to place at the beginning and end of a
    * line to "comment" it in a this programming language.
    *
    * @return The start and end strings to add to a line to "comment"
    *         it out.
    */
   public String[] getLineCommentStartAndEnd() {
      return new String[] { "//", null };
   }

   /**
    * Returns the first token in the linked list of tokens generated
    * from <code>text</code>.  This method must be implemented by
    * subclasses so they can correctly implement syntax highlighting.
    *
    * @param text The text from which to get tokens.
    * @param initialTokenType The token type we should start with.
    * @param startOffset The offset into the document at which
    *        <code>text</code> starts.
    * @return The first <code>Token</code> in a linked list representing
    *         the syntax highlighted text.
    */
   public Token getTokenList(Segment text, int initialTokenType, int startOffset) {

      resetTokenList();
      this.offsetShift = -text.offset + startOffset;

      // Start off in the proper state.
      int state = Token.NULL;
      switch (initialTokenType) {
                  case Token.COMMENT_MULTILINE:
            state = MLC;
            start = text.offset;
            break;

         /* No documentation comments */
         default:
            state = Token.NULL;
      }

      s = text;
      try {
         yyreset(zzReader);
         yybegin(state);
         return yylex();
      } catch (IOException ioe) {
         ioe.printStackTrace();
         return new TokenImpl();
      }

   }

   /**
    * Refills the input buffer.
    *
    * @return      <code>true</code> if EOF was reached, otherwise
    *              <code>false</code>.
    */
   private boolean zzRefill() {
      return zzCurrentPos>=s.offset+s.count;
   }

   /**
    * Resets the scanner to read from a new input stream.
    * Does not close the old reader.
    *
    * All internal variables are reset, the old input stream
    * <b>cannot</b> be reused (internal buffer is discarded and lost).
    * Lexical state is set to <tt>YY_INITIAL</tt>.
    *
    * @param reader   the new input stream
    */
   public final void yyreset(Reader reader) {
      // 's' has been updated.
      zzBuffer = s.array;
      /*
       * We replaced the line below with the two below it because zzRefill
       * no longer "refills" the buffer (since the way we do it, it's always
       * "full" the first time through, since it points to the segment's
       * array).  So, we assign zzEndRead here.
       */
      //zzStartRead = zzEndRead = s.offset;
      zzStartRead = s.offset;
      zzEndRead = zzStartRead + s.count - 1;
      zzCurrentPos = zzMarkedPos = zzPushbackPos = s.offset;
      zzLexicalState = YYINITIAL;
      zzReader = reader;
      zzAtBOL  = true;
      zzAtEOF  = false;
   }



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public DracoScriptSyntax(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public DracoScriptSyntax(java.io.InputStream in) {
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
    while (i < 182) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
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
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public org.fife.ui.rsyntaxtextarea.Token yylex() throws java.io.IOException {
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

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


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
        case 6: 
          { addNullToken(); return firstToken;
          }
        case 22: break;
        case 18: 
          { addToken(Token.LITERAL_CHAR);
          }
        case 23: break;
        case 15: 
          { start = zzMarkedPos-2; yybegin(MLC);
          }
        case 24: break;
        case 5: 
          { addToken(Token.WHITESPACE);
          }
        case 25: break;
        case 19: 
          { addToken(Token.ERROR_STRING_DOUBLE);
          }
        case 26: break;
        case 16: 
          { addToken(Token.RESERVED_WORD);
          }
        case 27: break;
        case 8: 
          { addToken(Token.SEPARATOR);
          }
        case 28: break;
        case 1: 
          { addToken(Token.IDENTIFIER);
          }
        case 29: break;
        case 21: 
          { addToken(Token.FUNCTION);
          }
        case 30: break;
        case 3: 
          { addToken(Token.ERROR_CHAR); addNullToken(); return firstToken;
          }
        case 31: break;
        case 4: 
          { addToken(Token.ERROR_STRING_DOUBLE); addNullToken(); return firstToken;
          }
        case 32: break;
        case 20: 
          { addToken(Token.DATA_TYPE);
          }
        case 33: break;
        case 17: 
          { yybegin(YYINITIAL); addToken(start,zzStartRead+2-1, Token.COMMENT_MULTILINE);
          }
        case 34: break;
        case 12: 
          { addToken(Token.ERROR_CHAR);
          }
        case 35: break;
        case 13: 
          { addToken(Token.LITERAL_STRING_DOUBLE_QUOTE);
          }
        case 36: break;
        case 14: 
          { addToken(Token.COMMENT_EOL); addNullToken(); return firstToken;
          }
        case 37: break;
        case 11: 
          { addToken(Token.ERROR_NUMBER_FORMAT);
          }
        case 38: break;
        case 2: 
          { addToken(Token.LITERAL_NUMBER_DECIMAL_INT);
          }
        case 39: break;
        case 7: 
          { addToken(Token.OPERATOR);
          }
        case 40: break;
        case 9: 
          { 
          }
        case 41: break;
        case 10: 
          { addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
          }
        case 42: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              addNullToken(); return firstToken;
            }
            case 107: break;
            case MLC: {
              addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
            }
            case 108: break;
            default:
            return null;
            }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
