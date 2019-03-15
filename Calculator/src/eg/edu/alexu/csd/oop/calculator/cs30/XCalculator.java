package eg.edu.alexu.csd.oop.calculator.cs30;
import eg.edu.alexu.csd.oop.calculator.Calculator;
public class XCalculator implements Calculator{


	/**
	 *
	 * @author kimo only
	 */
	

	    private String[] arr = new String[5];
	    private String[] arr2 = new String[5];
	    private int start = 0, end = 0;
	    private int prevpoint = 0;
	    private int num = 0;
	    private int possibleprev = 0;
	    private int possiblenext = 0;

	    @Override
	    public void input(String s) {
	        {
	            possiblenext = 0;
	        }
	        if (num < 5) {
	            num++;
	        }
	        arr[start] = s;
	        if (possibleprev < 5) {
	            possibleprev++;
	        }
	        end = start;
	        start = (start + 1) % 5;
	    }

	    @Override
	    public String getResult() {
	        String s = current();
	        String str = s.replace(" ", "");
	        int h = 0;
	        Double n1 = (double) 0;
	        Double n2 = (double) 0;
	        Boolean n = false;
	        int op = 0;
	        int i = 0;

	        for (i = 0; i < str.length(); i++) {
	            int j = i;
	            char v = str.charAt(i);
	            Boolean f = (v >= '0' && v <= '9');
	            if (v == '-' && i == 0) {
	                n = true;
	            }
	            if (f) {
	                int numpoint = 0;
	                while ((i < str.length()) && ((str.charAt(i) >= '0' && str.charAt(i) <= '9') || (str.charAt(i) == '.'))) {
	                    if (str.charAt(i) == '.') {
	                        if (numpoint == 0) {
	                            numpoint = 1;
	                        } else {
	                            return null;
	                        }
	                    }
	                    i++;
	                }
	                if (h == 0) {
	                    n1 = Double.parseDouble(str.substring(j, i));
	                    h = 1;
	                    if (n) {
	                        n1 = n1 * -1;
	                    }
	                    n = false;
	                } else if (h == 1) {
	                    n2 = Double.parseDouble(str.substring(j, i));
	                    if (n) {
	                        n2 = n2 * -1;
	                    }
	                    n = false;
	                    h++;
	                }
	                i--;
	            } else if ((v == '+' || v == '-' || v == '*' || v == '/' || v == '+' || v == '-' || v == '*' || v == '/') && h == 1) {
	            	if (v == '-' && op != 0) {
	                    n = true;
	                }
	            	if (v == '+') {
	                    op = 1;
	                }
	                if (v == '-' && op == 0) {
	                    op = 2;
	                }
	                if (v == '*') {
	                    op = 3;
	                }
	                 if (v == '/') {
	                    op = 4;
	                }
	                

	            } else {
	                return null;
	            }

	        }
	        if (h < 2) {
	            return null;
	        }
	        if (op == 1) {
	            return Double.toString(n1 + n2);
	        } else if (op == 2) {
	            return Double.toString(n1 - n2);
	        } else if (op == 3) {
	            return Double.toString(n1 * n2);
	        } else if (op == 4) {
	            return Double.toString(n1 / n2);
	        }

	        return null;
	    }

	    @Override
	    public String current() {
	        if (start == 0) {
	            return arr[4];
	        } else {
	            return arr[start - 1];
	        }
	    }

	    @Override
	    public String prev() {
	        int h = start;
	        if (h == 0) {
	            h = 4;
	        } else {
	            h = (h - 1);
	        }
	        if (arr[h] == (null) || possibleprev < 2) {
	            return null;
	        }
	        if (start == 0) {
	            start = 4;
	        } else {
	            start = (start - 1);
	        }
	        int k;
	        if (start == 0) {
	            k = 4;
	        } else {
	            k = (start - 1);
	        }

	        possibleprev--;
	        possiblenext++;

	        return arr[k];
	    }

	    @Override
	    public String next() {
	        prevpoint--;

	        int h = start;

	        if (possiblenext < 1 || arr[h] == null) {
	            return null;
	        }
	        if (start == 4) {
	            start = 0;
	        } else {
	            start = start + 1;
	        }

	        possiblenext--;
	        possibleprev++;

	        return arr[h];
	    }

	    @Override
	    public void save() {
	    }

	    @Override
	    public void load() {
	    }
	}
