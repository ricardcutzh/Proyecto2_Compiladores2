/* The following code was generated by JFlex 1.4.1 on 10/8/18 9:36 PM */

package GUI.TextEditorPanel.SyntaxDpp;
import java.io.*;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 10/8/18 9:36 PM from the specification file
 * <tt>DppSyntax.jflex</tt>
 */
public class DppSyntax extends AbstractJFlexCTokenMaker {

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
    "\11\0\1\7\1\10\1\0\1\7\1\6\22\0\1\7\1\40\1\4"+
    "\1\0\1\0\1\40\1\44\1\3\2\13\1\12\1\36\1\14\1\37"+
    "\1\14\1\11\12\2\1\6\1\14\1\42\1\41\1\43\1\6\1\0"+
    "\32\1\1\13\1\5\1\13\1\40\1\1\1\0\1\23\1\34\1\22"+
    "\1\31\1\27\1\35\2\1\1\16\2\1\1\24\1\26\1\21\1\25"+
    "\1\17\1\1\1\20\1\15\1\30\1\32\1\33\4\1\1\13\1\45"+
    "\1\13\1\6\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\2\10\1\11\16\2\4\10\3\1\1\12\1\13\1\12"+
    "\1\14\2\4\1\0\1\15\2\5\1\16\1\17\1\20"+
    "\22\2\1\0\1\21\1\22\1\23\25\2\1\20\26\2"+
    "\1\24\11\2\1\25\2\2\1\26\23\2";

  private static int [] zzUnpackAction() {
    int [] result = new int[145];
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
    "\0\0\0\46\0\114\0\162\0\230\0\276\0\344\0\u010a"+
    "\0\114\0\u0130\0\114\0\114\0\114\0\u0156\0\u017c\0\u01a2"+
    "\0\u01c8\0\u01ee\0\u0214\0\u023a\0\u0260\0\u0286\0\u02ac\0\u02d2"+
    "\0\u02f8\0\u031e\0\u0344\0\u036a\0\u0390\0\u03b6\0\u03dc\0\u0402"+
    "\0\u0428\0\u044e\0\114\0\u0474\0\u049a\0\u04c0\0\u04e6\0\u050c"+
    "\0\114\0\u0532\0\u0558\0\u057e\0\114\0\u05a4\0\u05ca\0\u05f0"+
    "\0\u0616\0\u063c\0\u0662\0\u0688\0\u06ae\0\u06d4\0\u06fa\0\u0720"+
    "\0\u0746\0\u076c\0\u0792\0\u07b8\0\u07de\0\u0804\0\u082a\0\u0850"+
    "\0\u0390\0\114\0\114\0\114\0\u0876\0\u089c\0\u08c2\0\u08e8"+
    "\0\u090e\0\u0934\0\u095a\0\u0980\0\u09a6\0\u09cc\0\u09f2\0\u0a18"+
    "\0\u0a3e\0\u0a64\0\u0a8a\0\u0ab0\0\u0ad6\0\u0afc\0\u0b22\0\u0b48"+
    "\0\u0b6e\0\162\0\u0b94\0\u0bba\0\u0be0\0\u0c06\0\u0c2c\0\u0c52"+
    "\0\u0c78\0\u0c9e\0\u0cc4\0\u0cea\0\u0d10\0\u0d36\0\u0d5c\0\u0d82"+
    "\0\u0da8\0\u0dce\0\u0df4\0\u0e1a\0\u0e40\0\u0e66\0\u0e8c\0\u0eb2"+
    "\0\162\0\u0ed8\0\u0efe\0\u0f24\0\u0f4a\0\u0f70\0\u0f96\0\u0fbc"+
    "\0\u0fe2\0\u1008\0\162\0\u102e\0\u1054\0\162\0\u107a\0\u10a0"+
    "\0\u10c6\0\u10ec\0\u1112\0\u1138\0\u115e\0\u1184\0\u11aa\0\u11d0"+
    "\0\u11f6\0\u121c\0\u1242\0\u1268\0\u128e\0\u12b4\0\u12da\0\u1300"+
    "\0\u1326";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[145];
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
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\4\1\22\1\4\1\23\1\24\1\25\1\26\1\4"+
    "\1\27\1\4\1\30\1\31\1\32\1\33\1\34\1\13"+
    "\1\35\1\36\1\37\1\40\1\41\10\42\1\43\1\42"+
    "\1\44\33\42\47\0\2\4\12\0\21\4\10\0\2\45"+
    "\1\5\2\0\1\45\7\0\21\45\10\0\3\46\1\3"+
    "\1\46\1\47\2\46\1\50\35\46\4\7\1\51\1\52"+
    "\2\7\1\53\35\7\7\0\1\10\47\0\1\54\1\55"+
    "\34\0\2\4\12\0\1\4\1\56\17\4\11\0\2\4"+
    "\12\0\11\4\1\57\7\4\11\0\2\4\12\0\3\4"+
    "\1\60\2\4\1\61\6\4\1\62\3\4\11\0\2\4"+
    "\12\0\12\4\1\63\6\4\11\0\2\4\12\0\6\4"+
    "\1\64\1\4\1\65\4\4\1\66\3\4\11\0\2\4"+
    "\12\0\1\4\1\67\17\4\11\0\2\4\12\0\16\4"+
    "\1\70\2\4\11\0\2\4\12\0\1\4\1\71\17\4"+
    "\11\0\2\4\12\0\1\72\3\4\1\73\14\4\11\0"+
    "\2\4\12\0\12\4\1\74\6\4\11\0\2\4\12\0"+
    "\6\4\1\75\3\4\1\76\6\4\11\0\2\4\12\0"+
    "\10\4\1\77\10\4\11\0\2\4\12\0\6\4\1\100"+
    "\12\4\46\0\1\13\46\0\1\13\47\0\1\13\47\0"+
    "\1\13\43\0\1\13\1\101\47\0\1\13\46\0\1\13"+
    "\10\42\1\0\1\42\1\0\33\42\11\0\1\102\34\0"+
    "\3\45\2\0\1\45\7\0\21\45\10\0\3\47\1\103"+
    "\4\47\1\0\40\47\1\3\4\47\1\0\35\47\3\0"+
    "\1\103\42\0\10\53\1\0\41\53\1\104\1\52\40\53"+
    "\10\54\1\0\35\54\1\0\2\4\12\0\4\4\1\105"+
    "\14\4\11\0\2\4\12\0\2\4\1\106\16\4\11\0"+
    "\2\4\12\0\1\4\1\107\17\4\11\0\2\4\12\0"+
    "\3\4\1\110\15\4\11\0\2\4\12\0\4\4\1\111"+
    "\14\4\11\0\2\4\12\0\13\4\1\112\5\4\11\0"+
    "\2\4\12\0\3\4\1\113\10\4\1\114\4\4\11\0"+
    "\2\4\12\0\4\4\1\115\14\4\11\0\2\4\12\0"+
    "\6\4\1\116\12\4\11\0\2\4\12\0\4\4\1\117"+
    "\14\4\11\0\2\4\12\0\6\4\1\120\12\4\11\0"+
    "\2\4\12\0\12\4\1\121\6\4\11\0\2\4\12\0"+
    "\13\4\1\122\5\4\11\0\2\4\12\0\13\4\1\123"+
    "\5\4\11\0\2\4\12\0\5\4\1\124\5\4\1\125"+
    "\5\4\11\0\2\4\12\0\5\4\1\126\13\4\11\0"+
    "\2\4\12\0\3\4\1\127\15\4\11\0\2\4\12\0"+
    "\10\4\1\130\10\4\11\0\2\4\12\0\7\4\1\131"+
    "\11\4\11\0\2\4\12\0\10\4\1\132\10\4\11\0"+
    "\2\4\12\0\3\4\1\133\4\4\1\134\10\4\11\0"+
    "\2\4\12\0\4\4\1\135\14\4\11\0\2\4\12\0"+
    "\6\4\1\132\12\4\11\0\2\4\12\0\13\4\1\136"+
    "\5\4\11\0\2\4\12\0\10\4\1\137\10\4\11\0"+
    "\2\4\12\0\6\4\1\140\12\4\11\0\2\4\12\0"+
    "\12\4\1\141\6\4\11\0\2\4\12\0\13\4\1\142"+
    "\5\4\11\0\2\4\12\0\14\4\1\143\4\4\11\0"+
    "\2\4\12\0\12\4\1\144\6\4\11\0\2\4\12\0"+
    "\7\4\1\136\11\4\11\0\2\4\12\0\4\4\1\145"+
    "\14\4\11\0\2\4\12\0\3\4\1\146\15\4\11\0"+
    "\2\4\12\0\12\4\1\147\6\4\11\0\2\4\12\0"+
    "\1\4\1\150\17\4\11\0\2\4\12\0\12\4\1\151"+
    "\6\4\11\0\2\4\12\0\1\4\1\152\17\4\11\0"+
    "\2\4\12\0\14\4\1\153\4\4\11\0\2\4\12\0"+
    "\7\4\1\154\11\4\11\0\2\4\12\0\1\155\20\4"+
    "\11\0\2\4\12\0\1\4\1\156\17\4\11\0\2\4"+
    "\12\0\3\4\1\157\15\4\11\0\2\4\12\0\5\4"+
    "\1\160\13\4\11\0\2\4\12\0\10\4\1\161\10\4"+
    "\11\0\2\4\12\0\3\4\1\162\15\4\11\0\2\4"+
    "\12\0\5\4\1\163\13\4\11\0\2\4\12\0\4\4"+
    "\1\164\14\4\11\0\2\4\12\0\1\4\1\165\17\4"+
    "\11\0\2\4\12\0\3\4\1\166\15\4\11\0\2\4"+
    "\12\0\6\4\1\161\12\4\11\0\2\4\12\0\13\4"+
    "\1\167\5\4\11\0\2\4\12\0\15\4\1\170\3\4"+
    "\11\0\2\4\12\0\3\4\1\152\15\4\11\0\2\4"+
    "\12\0\11\4\1\171\7\4\11\0\2\4\12\0\4\4"+
    "\1\172\14\4\11\0\2\4\12\0\10\4\1\173\10\4"+
    "\11\0\2\4\12\0\6\4\1\174\12\4\11\0\2\4"+
    "\12\0\12\4\1\175\6\4\11\0\2\4\12\0\10\4"+
    "\1\176\10\4\11\0\2\4\12\0\11\4\1\177\7\4"+
    "\11\0\2\4\12\0\13\4\1\200\5\4\11\0\2\4"+
    "\12\0\1\4\1\201\17\4\11\0\2\4\12\0\4\4"+
    "\1\200\14\4\11\0\2\4\12\0\13\4\1\202\5\4"+
    "\11\0\2\4\12\0\6\4\1\173\12\4\11\0\2\4"+
    "\12\0\4\4\1\203\14\4\11\0\2\4\12\0\6\4"+
    "\1\204\12\4\11\0\2\4\12\0\3\4\1\205\15\4"+
    "\11\0\2\4\12\0\5\4\1\206\13\4\11\0\2\4"+
    "\12\0\6\4\1\207\12\4\11\0\2\4\12\0\12\4"+
    "\1\210\6\4\11\0\2\4\12\0\14\4\1\211\4\4"+
    "\11\0\2\4\12\0\6\4\1\212\12\4\11\0\2\4"+
    "\12\0\1\4\1\213\17\4\11\0\2\4\12\0\6\4"+
    "\1\210\12\4\11\0\2\4\12\0\2\4\1\214\16\4"+
    "\11\0\2\4\12\0\12\4\1\215\6\4\11\0\2\4"+
    "\12\0\15\4\1\200\3\4\11\0\2\4\12\0\14\4"+
    "\1\136\4\4\11\0\2\4\12\0\6\4\1\216\12\4"+
    "\11\0\2\4\12\0\13\4\1\217\5\4\11\0\2\4"+
    "\12\0\7\4\1\173\11\4\11\0\2\4\12\0\3\4"+
    "\1\132\15\4\11\0\2\4\12\0\12\4\1\220\6\4"+
    "\11\0\2\4\12\0\4\4\1\152\14\4\11\0\2\4"+
    "\12\0\3\4\1\161\15\4\11\0\2\4\12\0\6\4"+
    "\1\221\12\4\11\0\2\4\12\0\3\4\1\173\15\4"+
    "\11\0\2\4\12\0\1\132\20\4\11\0\2\4\12\0"+
    "\15\4\1\61\3\4\11\0\2\4\12\0\3\4\1\155"+
    "\15\4\11\0\2\4\12\0\7\4\1\132\11\4\10\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4940];
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
    "\2\0\1\11\5\1\1\11\1\1\3\11\25\1\1\11"+
    "\4\1\1\0\1\11\3\1\1\11\23\1\1\0\3\11"+
    "\115\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[145];
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
   public DppSyntax() {
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
  public DppSyntax(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public DppSyntax(java.io.InputStream in) {
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
    while (i < 126) {
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
        case 7: 
          { addNullToken(); return firstToken;
          }
        case 23: break;
        case 18: 
          { addToken(Token.LITERAL_CHAR);
          }
        case 24: break;
        case 15: 
          { start = zzMarkedPos-2; yybegin(MLC);
          }
        case 25: break;
        case 6: 
          { addToken(Token.WHITESPACE);
          }
        case 26: break;
        case 19: 
          { addToken(Token.ERROR_STRING_DOUBLE);
          }
        case 27: break;
        case 16: 
          { addToken(Token.RESERVED_WORD);
          }
        case 28: break;
        case 9: 
          { addToken(Token.SEPARATOR);
          }
        case 29: break;
        case 2: 
          { addToken(Token.IDENTIFIER);
          }
        case 30: break;
        case 20: 
          { addToken(Token.FUNCTION);
          }
        case 31: break;
        case 4: 
          { addToken(Token.ERROR_CHAR); addNullToken(); return firstToken;
          }
        case 32: break;
        case 5: 
          { addToken(Token.ERROR_STRING_DOUBLE); addNullToken(); return firstToken;
          }
        case 33: break;
        case 21: 
          { addToken(Token.DATA_TYPE);
          }
        case 34: break;
        case 17: 
          { yybegin(YYINITIAL); addToken(start,zzStartRead+2-1, Token.COMMENT_MULTILINE);
          }
        case 35: break;
        case 1: 
          { addToken(Token.ERROR_CHAR);
          }
        case 36: break;
        case 22: 
          { addToken(Token.LITERAL_BOOLEAN);
          }
        case 37: break;
        case 13: 
          { addToken(Token.LITERAL_STRING_DOUBLE_QUOTE);
          }
        case 38: break;
        case 14: 
          { addToken(Token.COMMENT_EOL); addNullToken(); return firstToken;
          }
        case 39: break;
        case 12: 
          { addToken(Token.ERROR_NUMBER_FORMAT);
          }
        case 40: break;
        case 3: 
          { addToken(Token.LITERAL_NUMBER_DECIMAL_INT);
          }
        case 41: break;
        case 8: 
          { addToken(Token.OPERATOR);
          }
        case 42: break;
        case 10: 
          { 
          }
        case 43: break;
        case 11: 
          { addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
          }
        case 44: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              addNullToken(); return firstToken;
            }
            case 146: break;
            case MLC: {
              addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
            }
            case 147: break;
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
