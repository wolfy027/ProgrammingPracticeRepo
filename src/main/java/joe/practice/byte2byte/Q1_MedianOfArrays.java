package joe.practice.byte2byte;

/*
 * Question : Find the median of two sorted arrays.
 * 	arr1 = [1, 3, 5]
 *	arr2 = [2, 4, 6]
 *	median(arr1, arr2) = 3.5
 */
public class Q1_MedianOfArrays {
	public static double median(int[] arr1, int[] arr2) {
		if (arr1.length == 0 || arr1.length != arr2.length)
			throw new IllegalArgumentException();
		return median(SubArray.fromArray(arr1), SubArray.fromArray(arr2));
	}

	private static double median(SubArray arr1, SubArray arr2) {
		if (arr1.getSize() == 1)
			return (arr1.getFirst() + arr2.getFirst()) / 2.;
		if (arr1.getSize() == 2) {
			return (Math.max(arr1.getFirst(), arr2.getFirst()) + Math.min(arr1.getLast(), arr2.getLast())) / 2.;
		}
		double median1 = arr1.getMedian();
		double median2 = arr2.getMedian();

		if (median1 == median2)
			return median1;
		if (median1 > median2) {
			return median(arr1.subArray(0, arr1.getSize() / 2 + 1),
					arr2.subArray((arr2.getSize() - 1) / 2, arr2.getSize()));
		}
		return median(arr1.subArray((arr1.getSize() - 1) / 2, arr1.getSize()),
				arr2.subArray(0, arr2.getSize() / 2 + 1));
	}

	public static void main(String[] args) {
		int[] arr1 = new int[] { 1, 3, 5 };
		int[] arr2 = new int[] { 2, 4, 6 };
		System.out.println(median(arr1, arr2));

	}

	private static class SubArray {

		private int[] underlying;
		private int start;
		private int size;

		private static SubArray fromArray(int[] array) {
			SubArray subArray = new SubArray();
			subArray.underlying = array;
			subArray.start = 0;
			subArray.size = array.length;
			return subArray;
		}

		private SubArray subArray(int i, int j) {
			if (i > j)
				throw new IllegalArgumentException();
			if (j > this.size)
				throw new IndexOutOfBoundsException();
			SubArray subArray = new SubArray();
			subArray.underlying = this.underlying;
			subArray.start = this.start + i;
			subArray.size = j - i;
			return subArray;
		}

		private int getFirst() {
			return underlying[start];
		}

		private int getLast() {
			return underlying[start + size - 1];
		}

		private int getSize() {
			return this.size;
		}

		private double getMedian() {
			if (this.size % 2 == 0) {
				return this.underlying[start + size / 2 - 1] + this.underlying[start + size / 2] / 2.;
			}
			return this.underlying[start + size / 2];
		}

	}

}
