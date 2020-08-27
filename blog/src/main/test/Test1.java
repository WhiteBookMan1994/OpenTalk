/**
 * @author dingchenchen
 * @since 2020-06-23
 */
public class Test1 {

    public static void main(String[] args) {
        System.out.println(addBinary("1111", "1111"));
    }


    public static String addBinary(String a, String b) {
        int alen = a.length();
        int blen = b.length();
        byte [] longArray;
        byte [] shortArray;
        if (alen > blen) {
            longArray = new byte [alen];
            shortArray = new byte [blen];
        } else {
            longArray = new byte [blen];
            shortArray = new byte [alen];
        }
        for (int i = 0; i< alen;i ++) {
            char c = a.charAt(alen-i-1);
            if (c == '1') {
                if(alen > blen) {
                    longArray[i] = 1;
                } else {
                    shortArray[i] = 1;
                }
            } else {
                if(alen > blen) {
                    longArray[i] = 0;
                } else {
                    shortArray[i] = 0;
                }
            }
        }
        for (int i = 0; i< blen;i ++) {
            char c = b.charAt(blen-i-1);
            if (c == '1') {
                if(alen > blen) {
                    shortArray[i] = 1;
                } else {
                    longArray[i] = 1;
                }
            } else {
                if(alen > blen) {
                    shortArray[i] = 0;
                } else {
                    longArray[i] = 0;
                }
            }
        }
        for (int i = 0; i<longArray.length && i< shortArray.length; i++) {
            longArray[i] = (byte) (longArray[i] + shortArray[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<longArray.length; i++) {
            if(longArray[i] >= 2 && i < longArray.length-1) {
                longArray[i] = (byte)(longArray[i] & 1);
                longArray[i + 1] = (byte)(longArray[i + 1] + 1);
            }
        }
        for (int i = longArray.length - 1; i>=0; i--) {
            if (i == longArray.length -1) {
                if(longArray[i] >= 2) {
                    sb.append(1).append(longArray[i] & 1);
                } else {
                    sb.append(longArray[i]);
                }
            } else {
                sb.append(longArray[i]);
            }
        }
        return sb.toString();
    }
}
