// ����������� �������� ������������� ���������� (HeapSort)

public class Lwsson2 {
    public static void main(String[] args) {

        int[] arr = {10, 4, 20, 14, 7,12, 19, 11, 9, 1};
        int len = arr.length;

        view_arr(arr);

        // ��������� ���������.
        // ������� � ����� ��������� ���� �� ����������� ��������� �� ������ (i != 0) ���� ����, ��������� �� ����� �������� �������.
        for (int i = len-1; i >= 0; i--) {
            HeapSort (arr, len, i);

        }

        view_arr(arr);

        // ������� i0 � iend
        swap(arr, 0, len-1);

        while (len > 2) {
            // ��������� len �� 1
            len--;
            // �������� i0 �� ����� �����
            HeapSort (arr, len, 0);
            // ������� i0 � iend
            swap(arr, 0, len-1);
        }
        view_arr(arr);
    }

    static void HeapSort (int[] arr, int len, int i) { // ������ ����, �������� �������� �������� ������� ����������� ������� ���� �� ������
        // ��������� ���� �� � �������� �������� �������� 1
        int max_i = i;

        if (i*2+1 <= len-1) {
            // ���� ���� ���������� ������ �� ��, ���������� ������ ��������
            if (arr[i] < arr [i*2+1]) {
                max_i = i*2+1;
            }
            // ��������� ���� �� �������� 2 (����� ���������� ���� ��� �������)
            if (i*2+2 <= len-1) {
                // ���������� � ������� ���� � ���������� ������
                if (arr[max_i] < arr [i*2+2]) {
                    max_i = i*2+2;
                }
            }
        }
        // ���� ������ �������� ����� �������� �� ������ �� ������.
        // ���� �����, ������ ������� � ������� �������� � ��������� ���������� �� ����� ������������� �������.
        if (max_i != i) {
            swap(arr, i, max_i);
            HeapSort(arr, len, max_i);
        }

    }

    static void swap (int arr[],int i, int max_i) {
        int temp = arr [i];
        arr [i] = arr [max_i];
        arr [max_i] = temp;
    }

    static void view_arr (int arr[]) {
        for (int item : arr) {
            System.out.printf("%3d", item);
        }
        System.out.println();
    }


}