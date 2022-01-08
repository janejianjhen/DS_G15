
public class ConsoleProgressBar {
	private int max_size;
	private int barLen;
	private char showChar;
	
	
	public ConsoleProgressBar(int barLen, char showChar, int max) {
		this.max_size = max;
		this.barLen = barLen;
		this.showChar = showChar;
	}
	
	//畫指定長度個showChar
	public void show(int value) {
		reset();
		float rate = (float) (value*1.0 / this.max_size);
		draw(barLen, rate);
		System.out.print(" | (" + value + "/" + max_size + ")");
		
		if (value == this.max_size) {
			afterComplete();
		}
	}
	
	private void draw(int barLen, float rate) {
		int len = (int) (rate*barLen);
		
		System.out.print("Search Main Page : ");
		for(int i=0; i<len; i++) {
			System.out.print(showChar);
		}
		for(int i=0; i<barLen-len; i++) {
			System.out.print(' ');
		}
	}
	//光標移動到行首
	private void reset() {
		System.out.print("\r");
	}
	
	
	private void afterComplete() {
		System.out.print('\n');
	}
}
