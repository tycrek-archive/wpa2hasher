package wpa2hasher;

public class RandomHashList {
	
	public static void main(String[] args) {
		int max = Integer.MAX_VALUE;
		int split = max / 4;
		
		int first = split;
		int second = split * 2;
		int third = split * 3;
		int fourth = max;
		
		for(int a = 31; a < 127; a++) {
			for(int b = 31; b < 127; b++) {
				for(int c = 31; c < 127; c++) {
					for(int d = 31; d < 127; d++) {
						for(int e = 31; e < 127; e++) {
							for(int f = 31; f < 127; f++) {
								for(int g = 31; g < 127; g++) {
									for(int h = 31; h < 127; h++) {
										String sa = Character.toString((char)a);if(a==31)sa="";
										String sb = Character.toString((char)b);if(b==31)sb="";
										String sc = Character.toString((char)c);if(c==31)sc="";
										String sd = Character.toString((char)d);if(d==31)sd="";
										String se = Character.toString((char)e);if(e==31)se="";
										String sf = Character.toString((char)f);if(f==31)sf="";
										String sg = Character.toString((char)g);if(g==31)sg="";
										String sh = Character.toString((char)h);if(h==31)sh="";
										System.out.println(sa+sb+sc+sd+se+sf+sg+sh);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}