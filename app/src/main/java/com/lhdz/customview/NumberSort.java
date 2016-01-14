package com.lhdz.customview;

public class NumberSort {

	// Java实现的排序类

	// 私有构造方法，禁止实例化
	private NumberSort() {
		super();
	}


	// 冒泡法排序
	public static void bubbleSort(int[] numbers) {
		int temp; // 记录临时中间值
		int size = numbers.length; // 数组大小
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				if (numbers[i] < numbers[j]) { // 交换两数的位置
					temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
				}
			}
		}
	}

	/*
	 * 快速排序<br/>
	 * 
	 * 从数列中挑出一个元素，称为“基准”
	 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分割之后，
	 * 该基准是它的最后位置。这个称为分割（partition）操作。 
	 * 递归地把小于基准值元素的子数列和大于基准值元素的子数列排序。
	 */
	public static void quickSort(int[] numbers, int start, int end) {
		if (start < end) {
			int base = numbers[start]; // 选定的基准值（第一个数值作为基准值）
			int temp; // 记录临时中间值
			int i = start, j = end;
			do {
				while ((numbers[i] < base) && (i < end))
					i++;
				while ((numbers[j] > base) && (j > start))
					j--;
				if (i <= j) {
					temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
					i++;
					j--;
				}
			} while (i <= j);
			if (start < j)
				quickSort(numbers, start, j);
			if (end > i)
				quickSort(numbers, i, end);
		}
	}

	/*
	 * 选择排序 </br>
	 * 
	 * 在未排序序列中找到最小元素，存放到排序序列的起始位置 再从剩余 未排序元素中继续寻找最小元素，然后放到排序序列末尾。
	 * 以此类推，直到所有元素均排序完毕。
	 */
	public static void selectSort(int[] numbers) {
		int size = numbers.length, temp;
		for (int i = 0; i < size; i++) {
			int k = i;
			for (int j = size - 1; j > i; j--) {
				if (numbers[j] < numbers[k])
					k = j;
			}
			temp = numbers[i];
			numbers[i] = numbers[k];
			numbers[k] = temp;
		}
	}

	/*
	 * 插入排序<br/>
	 * 
	 * 从第一个元素开始，该元素可以认为已经被排序</li>
	 *  取出下一个元素，在已经排序的元素序列中从后向前扫描</li>
	 * 如果该元素（已排序）大于新元素，将该元素移到下一位置</li>
	 *  重复步骤3，直到找到已排序的元素小于或者等于新元素的位置</li>
	 * 将新元素插入到该位置中</li>
	 */
	public static void insertSort(int[] numbers) {
		int size = numbers.length, temp, j;
		for (int i = 1; i < size; i++) {
			temp = numbers[i];
			for (j = i; j > 0 && temp < numbers[j - 1]; j--)
				numbers[j] = numbers[j - 1];
			numbers[j] = temp;
		}
	}

	// 归并排序
	public static void mergeSort(int[] numbers, int left, int right) {
		int t = 1;// 每组元素个数
		int size = right - left + 1;
		while (t < size) {
			int s = t;// 本次循环每组元素个数
			t = 2 * s;
			int i = left;
			while (i + (t - 1) < size) {
				merge(numbers, i, i + (s - 1), i + (t - 1));
				i += t;
			}
			if (i + (s - 1) < right)
				merge(numbers, i, i + (s - 1), right);
		}
	}


	/*
	 * 归并排序<br/>  
 * <ul>  
 * <li>申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列</li>  
 * <li>设定两个指针，最初位置分别为两个已经排序序列的起始位置</li>  
 * <li>比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置</li>  
 * <li>重复步骤3直到某一指针达到序列尾</li>  
 * <li>将另一序列剩下的所有元素直接复制到合并序列尾</li>  
 * </ul>  
 *   
	 */
	private static void merge(int[] data, int p, int q, int r) {
		int[] B = new int[data.length];
		int s = p;
		int t = q + 1;
		int k = p;
		while (s <= q && t <= r) {
			if (data[s] <= data[t]) {
				B[k] = data[s];
				s++;
			} else {
				B[k] = data[t];
				t++;
			}
			k++;
		}
		if (s == q + 1)
			B[k++] = data[t++];
		else
			B[k++] = data[s++];
		for (int i = p; i <= r; i++)
			data[i] = B[i];
	}

}
