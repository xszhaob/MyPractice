package bo.zhao.practice.nio.action3.http;

import java.nio.charset.Charset;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/19
 */
public class HttpUtil {

    private static final byte[] GET = new byte[]{'G', 'E', 'T'};
    private static final byte[] POST = new byte[]{'P', 'O', 'S', 'T'};
    private static final byte[] PUT = new byte[]{'P', 'U', 'T'};
    private static final byte[] HEAD = new byte[]{'H', 'E', 'A', 'D'};
    private static final byte[] DELETE = new byte[]{'D', 'E', 'L', 'E', 'T', 'E'};

    private static final byte[] HOST = new byte[]{'H', 'o', 's', 't'};
    private static final byte[] CONTENT_LENGTH = new byte[]{'C', 'o', 'n', 't', 'e',
            'n', 't', '-', 'L', 'e', 'n', 'g', 't', 'h'};


    public static int parseHttpRequest(byte[] src, int startIndex,
                                       int endIndex, HttpHeaders httpHeaders) {
        // parse HTTP request line
        int endOfFirstLine = findNextLineBreak(src, startIndex, endIndex);
        if (endOfFirstLine == -1) {
            return -1;
        }

        // parse HTTP headers
        int prevEndOfHeader = endOfFirstLine + 1;
        int endOfHeader = findNextLineBreak(src, prevEndOfHeader, endIndex);

        while (endOfHeader != -1 && endOfHeader != prevEndOfHeader + 1) {
            if (matches(src, prevEndOfHeader, CONTENT_LENGTH)) {
                findContentLength(src, prevEndOfHeader, endIndex, httpHeaders);
            }

            prevEndOfHeader = endOfHeader + 1;
            endOfHeader = findNextLineBreak(src, prevEndOfHeader, endIndex);
        }

        if (endOfHeader == -1) {
            return -1;
        }

        int bodyStartIndex = endOfHeader + 1;
        int bodyEndIndex = bodyStartIndex + httpHeaders.contentLength;

        if (bodyEndIndex <= endIndex) {
            httpHeaders.bodyStartIndex = bodyStartIndex;
            httpHeaders.bodyEndIndex = bodyEndIndex;
            return bodyEndIndex;
        }

        return -1;
    }

    private static void findContentLength(byte[] src, int startIndex,
                                          int endIndex, HttpHeaders httpHeaders) {
        int indexOfColon = findNext(src, startIndex, endIndex, (byte) ':');

        int index = indexOfColon + 1;
        while (src[index] == ' ') {
            index++;
        }

        int valueStartIndex = index;
        int valueEndIndex = index;
        boolean endOfValueFound = false;

        while (index < endIndex && !endOfValueFound) {
            switch (src[index]) {
                case '0':
                    ;
                case '1':
                    ;
                case '2':
                    ;
                case '3':
                    ;
                case '4':
                    ;
                case '5':
                    ;
                case '6':
                    ;
                case '7':
                    ;
                case '8':
                    ;
                case '9': {
                    index++;
                    break;
                }
                default: {
                    endOfValueFound = true;
                    valueEndIndex = index;
                }
            }
        }

        httpHeaders.contentLength = Integer.parseInt(new String(src, valueStartIndex, valueEndIndex - valueStartIndex, Charset.forName("utf-8")));
    }

    private static boolean matches(byte[] src, int offset, byte[] value) {
        for (int i = offset, n = 0; n < value.length; i++, n++) {
            if (src[i] != value[n]) {
                return false;
            }
        }
        return true;
    }


    private static int findNextLineBreak(byte[] src, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (src[i] == '\n' && src[i - 1] == '\r') {
                return i;
            }
        }
        return -1;
    }

    private static int findNext(byte[] src, int startIndex, int endIndex, byte value) {
        for (int i = startIndex; i < endIndex; i++) {
            if (src[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
