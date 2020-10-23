import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO: Need to update

public class SleepSort {
	static int index = 0;
	
	public static void main(String args[]) throws InterruptedException {
		Integer nums[] = { 3, 1, 5, 4, 2 };
		int len = nums.length;
		int sorted[] = new int[len];
		
		Thread thread;
		for (int i = 0; i < len; i++) {
			final int sleep = i;
			thread = new Thread(() -> {
				try {
					Thread.sleep(nums[sleep]);
					sorted[index++] = nums[sleep];
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			thread.start();
		}
		
		List<Integer> list = new ArrayList<>();
		Collections.addAll(list, nums);
		Collections.max(list);
		Thread.sleep(Collections.max(list) + 1);
		
		for (int i = 0; i < len; i++) {
			System.out.print(sorted[i] + " ");
		}
		
		
	}
}
