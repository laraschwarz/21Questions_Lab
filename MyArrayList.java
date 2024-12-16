import java.io.Serializable;

public class MyArrayList<E extends Serializable> implements Serializable{

	private Object[] list;
	private int size;
	public MyArrayList() {
		list = new Object[10];
		size = 0;
	}

	public boolean add(E obj) {
		// already full, need to creat bigger list

		try {
			if (list.length==size()) {
				Object[] tempList = new Object[list.length * 2];

				for (int i = 0;i<list.length;i++) {
					tempList[i] = list[i];
				}
				list = tempList;
			}
			list[size()] = obj;
			size++;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

    @SuppressWarnings("unchecked")
	public E get(int n) {
		return (E)list[n];
	}

    @SuppressWarnings("unchecked")
	public E remove(int n) {
		E temp = (E)(list[n]);
		for (int i = n; i < list.length - 1; i++) {
			list[i] = list[i + 1];
		}
		size--;
		return temp;
	}

    @SuppressWarnings("unchecked")
	public E remove(E obj) {
		for (int i = 0; i < list.length; i++) {
			if (list[i].equals(obj)) {
				E temp = (E)list[i];

				if (i == list.length-1) {
					list[i]=null;
				} 
				else {
					list[i] = list[i + 1];
				}
				size--;
				return temp;
			}
		}
		return null;
	}

	public void set(int n, E obj) {
		list[n] = obj;
	}

    @SuppressWarnings("unchecked")
	public String toString() {
		String result = "";
		for (int i = 0; i < size(); i++) {
			result = result + ((E)(list[i])) + ", ";
		}
		return "{" + result + "}";
	}

	public int size(){
		return size;
	}
	public boolean isEmpty() {
		// boolean b=false;
		// for (int i =0;i<size();i++) {
		// 	if (list[i]!=null) {
		// 		return b;
		// 	}
		// }
		// return true;
		return size == 0;
	}
	public void clear() {
		for (int i =0;i<size();i++) {
			list[i] = null;
		}
		size=0;
	}

}