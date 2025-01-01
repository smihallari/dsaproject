public class CustomLinL<T> 
{
	private ListNode<T> head;
	private ListNode<T> tail;
	int numItems;

	public CustomLinL()
	{
		head = tail = null;
		numItems = 0;
	}

	public int size()
	{
		return numItems;
	}
	
	public boolean isEmpty()
	{
		return (numItems == 0);
	}

	public void removeAll()
	{
		head = tail = null;
		numItems = 0;
	}

	private ListNode<T> find(int index)
	{
		ListNode<T> curr = head;
		for (int skip = 1; skip < index; skip++)
			curr = curr.getNext();
		return curr;
	}
	public T getFirst(){
		return get(1);
	}
	public T get(int index) 
					  throws ListIndexOutOfBoundsException
	{
		if (index >= 1 && index <= numItems)
		{
			ListNode<T> curr = find(index);
			return curr.getItem();
		} 
		else
		{
			throw new ListIndexOutOfBoundsException(
                     "List index out of bounds exception on get");
		}
	}

	public void add(int index, T newDataItem)
					throws ListIndexOutOfBoundsException
	{
		if (index >= 1 && index <= numItems+1)
		{
			if ( index == 1 )
			{
				ListNode<T> newNode = new ListNode<>(newDataItem, head);
				head = newNode;

				if (tail==null)
					tail = head;
			} 
			else if ( index==numItems+1 )
			{
				ListNode<T> newNode = new ListNode<>(newDataItem);
				tail.setNext(newNode);
				tail = newNode;
			}
			else
			{
				ListNode<T> prev = find(index-1);
				ListNode<T> newNode = new ListNode<>(newDataItem, prev.getNext());
				prev.setNext(newNode);
			}
			numItems++;
		} 
		else
		{
			throw new ListIndexOutOfBoundsException(
                    "List index out of bounds exception on add");
		}
	}

	public void insert(T newDataItem)
	{
		this.add(1,newDataItem);
	}

	public void append(T newDataItem)
	{
		this.add(numItems+1,newDataItem);
	}	

	public T showFront()
	{
		return this.get(1);
	}

	public T showLast()
	{
		return this.get(numItems);
	}
	public void removeFirst(){
		remove(1);
	}

	public T pollFirst(){
		T first = getFirst();
		removeFirst();
		return first;
	}

	public void remove(int index) 
					   throws ListIndexOutOfBoundsException
	{
		if (index >= 1 && index <= numItems)
		{
			if (index == 1)
			{
				head = head.getNext();
				if (head == null)
					tail = null;
			}
			else
			{
				ListNode<T> prev = find(index-1);
				ListNode<T> curr = prev.getNext(); 
				prev.setNext(curr.getNext());
				if (index == numItems)
					tail = prev;
			}
			numItems--;
		}
		else
		{
			throw new ListIndexOutOfBoundsException(
                   "List index out of bounds exception on remove");
		}
	}

	public boolean exists(T dataItem)
	{
		for (ListNode<T> tmp=head; tmp!=null; tmp=tmp.getNext())
			if (tmp.getItem().equals(dataItem))
				return true;
		return false;
	}
}