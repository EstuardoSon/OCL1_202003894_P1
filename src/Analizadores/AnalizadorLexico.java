/* The following code was generated by JFlex 1.4.3 on 7/03/22 11:13 */

package Analizadores;

import ocl1_proyecto1.*;
import static ocl1_proyecto1.OCL1_Proyecto1.ListaErrores;
import java_cup.runtime.Symbol;
import java.util.*; 


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 7/03/22 11:13 from the specification file
 * <tt>src/Analizadores/Lexico.jflex</tt>
 */
public class AnalizadorLexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COUNI = 4;
  public static final int COMULTI = 6;
  public static final int LEX = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3, 3
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0, 25, 22,  0, 25, 25,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    16, 28, 20,  0,  0,  7,  0, 30,  0,  0, 24, 24, 23,  5, 24, 26, 
    19, 19, 19, 19, 19, 19, 19, 19, 19, 19,  3,  4, 27,  0,  6, 24, 
     0, 18, 18,  8, 18, 18, 18, 18, 18, 18, 11, 18, 18, 18, 10,  9, 
    18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,  0, 21,  0,  0, 12, 
     0, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 29, 15, 
    15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,  1, 24,  2, 17,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0, 13,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0, 14,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\1\1\1\2\1\3\1\4\1\5\1\1\1\6"+
    "\2\7\1\1\2\7\1\10\2\1\1\11\1\10\3\1"+
    "\1\12\1\13\1\12\1\14\1\10\1\15\4\0\1\16"+
    "\1\6\2\7\15\0\2\17\1\0\1\20\1\21\1\22"+
    "\1\0\3\22\1\7\4\0\1\22\1\0\4\22\3\0"+
    "\1\22\3\0\1\23\5\22";

  private static int [] zzUnpackAction() {
    int [] result = new int[85];
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
    "\0\0\0\37\0\76\0\135\0\174\0\174\0\174\0\174"+
    "\0\174\0\233\0\272\0\331\0\370\0\u0117\0\u0136\0\u0155"+
    "\0\u0174\0\u0193\0\u01b2\0\u01d1\0\174\0\u01f0\0\u020f\0\u022e"+
    "\0\u0193\0\u0193\0\u024d\0\u0193\0\u0193\0\u0193\0\u026c\0\u028b"+
    "\0\u02aa\0\u02c9\0\u0193\0\u02e8\0\u0307\0\u0326\0\u0345\0\u0364"+
    "\0\u0383\0\u03a2\0\u03c1\0\u03e0\0\u03ff\0\u041e\0\u043d\0\u045c"+
    "\0\u047b\0\u049a\0\u04b9\0\u04d8\0\u04f7\0\u0516\0\u0193\0\u0193"+
    "\0\u0193\0\u0535\0\u0174\0\u0554\0\u0573\0\u0592\0\u05b1\0\u05d0"+
    "\0\u05ef\0\u0174\0\u041e\0\u060e\0\u047b\0\u062d\0\u064c\0\u066b"+
    "\0\u068a\0\u06a9\0\u06c8\0\u06e7\0\u0706\0\u0725\0\u0744\0\u0307"+
    "\0\u0763\0\u0782\0\u07a1\0\u07c0\0\u07df";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[85];
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
    "\1\5\1\6\1\7\1\10\1\11\1\12\1\5\1\13"+
    "\1\14\3\15\1\16\1\17\1\5\1\20\1\21\1\22"+
    "\1\15\1\23\1\24\1\5\1\25\1\5\1\26\1\25"+
    "\1\27\1\30\1\5\1\20\1\5\24\31\1\32\1\33"+
    "\1\34\10\31\26\35\1\36\16\35\1\0\25\35\1\37"+
    "\2\35\20\0\1\40\1\41\5\0\1\42\15\0\1\43"+
    "\11\0\1\40\1\41\5\0\1\42\16\0\1\44\10\0"+
    "\1\40\1\41\5\0\1\42\17\0\1\45\1\46\6\45"+
    "\1\47\1\50\2\45\3\0\1\42\5\0\1\45\11\0"+
    "\10\45\1\47\1\50\2\45\3\0\1\42\5\0\1\45"+
    "\11\0\4\45\1\51\1\45\1\0\1\45\1\40\1\41"+
    "\1\45\4\0\1\42\5\0\1\45\11\0\10\45\1\40"+
    "\1\41\2\45\3\0\1\42\5\0\1\45\11\0\10\45"+
    "\1\52\1\53\2\45\3\0\1\42\5\0\1\45\30\0"+
    "\1\42\66\0\1\54\1\55\5\0\1\42\7\0\20\56"+
    "\1\57\1\60\2\56\1\0\1\61\1\0\1\62\7\56"+
    "\1\0\1\63\6\0\4\64\1\0\3\64\1\65\1\41"+
    "\2\64\1\66\2\0\1\42\1\64\4\0\1\64\21\0"+
    "\1\40\1\41\5\0\1\42\2\0\1\67\24\0\1\40"+
    "\1\41\5\0\1\42\4\0\1\70\26\0\1\31\10\0"+
    "\2\31\6\0\1\36\51\0\1\41\15\0\10\71\4\0"+
    "\3\71\1\0\1\72\3\0\11\71\1\0\1\71\20\73"+
    "\1\74\1\0\2\73\1\75\12\73\7\0\1\44\37\0"+
    "\10\45\2\0\2\45\11\0\1\45\11\0\2\45\1\76"+
    "\5\45\2\0\2\45\11\0\1\45\22\0\1\50\25\0"+
    "\4\71\4\0\1\77\1\0\1\71\24\0\4\45\1\51"+
    "\1\45\1\0\1\45\2\0\1\45\12\0\1\45\22\0"+
    "\1\53\34\0\1\71\1\100\14\0\1\71\22\0\1\55"+
    "\35\0\1\101\2\0\1\71\13\0\24\56\1\102\1\61"+
    "\1\0\31\56\1\60\2\56\1\102\1\61\1\0\10\56"+
    "\10\103\4\56\3\103\1\56\1\104\3\56\1\73\1\105"+
    "\1\71\6\103\1\56\1\103\26\56\1\0\10\56\20\106"+
    "\1\107\1\56\2\106\1\75\1\110\1\73\10\106\10\0"+
    "\4\111\1\63\1\111\1\0\1\111\2\0\1\111\12\0"+
    "\1\111\2\0\1\63\6\0\4\64\1\0\4\64\1\0"+
    "\2\64\1\66\3\0\1\64\4\0\1\64\2\0\1\63"+
    "\6\0\4\64\1\0\4\64\1\41\2\64\1\66\3\0"+
    "\1\64\4\0\1\64\1\0\24\112\1\0\1\113\1\0"+
    "\10\112\10\71\4\0\3\71\5\0\11\71\1\0\1\71"+
    "\21\73\1\0\2\73\1\75\2\73\1\114\7\73\24\115"+
    "\1\0\1\116\1\0\1\117\7\115\10\0\3\45\1\120"+
    "\4\45\2\0\2\45\11\0\1\45\11\0\4\71\6\0"+
    "\1\71\33\0\1\71\15\0\1\71\24\0\1\71\13\0"+
    "\10\103\4\56\3\103\5\56\1\73\1\105\1\71\6\103"+
    "\1\56\1\103\24\56\1\102\1\61\1\0\1\62\7\56"+
    "\21\106\1\56\2\106\1\75\1\110\1\73\1\121\7\106"+
    "\26\56\1\0\1\62\7\56\2\0\1\64\5\0\10\111"+
    "\2\0\2\111\11\0\1\111\1\0\24\112\1\64\1\113"+
    "\1\0\36\112\1\0\10\112\20\73\1\74\1\0\2\73"+
    "\1\75\2\73\1\114\7\73\24\115\1\73\1\116\1\0"+
    "\36\115\1\0\10\115\20\122\1\123\1\115\2\122\1\75"+
    "\1\124\1\73\10\122\20\106\1\107\1\56\2\106\1\75"+
    "\1\110\1\73\1\121\7\106\24\115\1\73\1\116\1\0"+
    "\1\117\7\115\21\122\1\115\2\122\1\75\1\124\1\73"+
    "\1\125\7\122\26\115\1\0\1\117\7\115\20\122\1\123"+
    "\1\115\2\122\1\75\1\124\1\73\1\125\7\122";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2046];
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
    "\4\0\15\1\1\11\6\1\2\11\1\1\3\11\4\0"+
    "\1\11\3\1\15\0\2\1\1\0\3\11\1\0\4\1"+
    "\4\0\1\1\1\0\4\1\3\0\1\1\3\0\6\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[85];
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
    String cadena = "";


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public AnalizadorLexico(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public AnalizadorLexico(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
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
        case 4: 
          { return new Symbol(sym.DOS_PTS, yyline, yycolumn, yytext());
          }
        case 20: break;
        case 15: 
          { return new Symbol(sym.EXPRESION, yyline, yycolumn, yytext());
          }
        case 21: break;
        case 18: 
          { return new Symbol(sym.CONJUNTO, yyline, yycolumn, yytext());
          }
        case 22: break;
        case 5: 
          { return new Symbol(sym.PT_COMA, yyline, yycolumn, yytext());
          }
        case 23: break;
        case 19: 
          { return new Symbol(sym.CONJ, yyline, yycolumn, yytext());
          }
        case 24: break;
        case 11: 
          { String tmp=cadena; cadena=""; yybegin(YYINITIAL); return new Symbol(sym.FRASE, yyline, yycolumn, tmp);
          }
        case 25: break;
        case 14: 
          { return new Symbol(sym.ASIGNACION, yyline, yycolumn, yytext());
          }
        case 26: break;
        case 13: 
          { yybegin(YYINITIAL);
          }
        case 27: break;
        case 9: 
          { yybegin(LEX);
          }
        case 28: break;
        case 3: 
          { return new Symbol(sym.LLAVE_CIERRE, yyline, yycolumn, yytext());
          }
        case 29: break;
        case 12: 
          { String tmp=cadena; cadena=""; ListaErrores.add(new ArrayList(){{add("Lexico"); add("Cadena: "+tmp+" se esperaba una \"");add(yyline+1);add(yycolumn+1);}}); yybegin (YYINITIAL);
          }
        case 30: break;
        case 17: 
          { yybegin(COMULTI);
          }
        case 31: break;
        case 6: 
          { return new Symbol(sym.SEPARADOR, yyline, yycolumn, yytext());
          }
        case 32: break;
        case 16: 
          { yybegin(COUNI);
          }
        case 33: break;
        case 7: 
          { return new Symbol(sym.IDENTIFICADOR, yyline, yycolumn, yytext());
          }
        case 34: break;
        case 10: 
          { cadena+=yytext();
          }
        case 35: break;
        case 1: 
          { ListaErrores.add(new ArrayList(){{add("Lexico"); add("Caracter: "+yytext()+" No pertence al lenguaje");add(yyline+1);add(yycolumn+1);}});
          }
        case 36: break;
        case 2: 
          { return new Symbol(sym.LLAVE_APERTURA, yyline, yycolumn, yytext());
          }
        case 37: break;
        case 8: 
          { 
          }
        case 38: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
