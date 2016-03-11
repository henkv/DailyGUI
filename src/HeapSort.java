import java.util.Collections;
import java.util.Vector;

public class HeapSort<T extends Compares<T>>
{
    private void percolateUp(Vector<T> elements, int position)
    {
        if (position > 0)
        {
            int parent = (position - 1) / 2;

            if (elements.elementAt(position).greaterThan(elements.elementAt(parent)))
            {
                Collections.swap(elements, parent, position);
                percolateUp(elements, parent);
            }
        }
    }

    private void percolateDown(Vector<T> elements, int position, int size)
    {
        int left = position * 2 + 1;
        int right = left + 1;
        int max;

        if (left < size)
        {
            max = left;
            if (right < size)
            {
                if (elements.elementAt(right).greaterThan(elements.elementAt(left)))
                {
                    max = right;
                }
            }

            if (elements.elementAt(max).greaterThan(elements.elementAt(position)))
            {
                Collections.swap(elements, max, position);
                percolateDown(elements, max, size);
            }
        }
    }

    public void sort(Vector<T> elements)
    {
        for (int i = elements.size() / 2, size = elements.size(); i >= 0; i--)
        {
            percolateDown(elements, i, size);
        }

        for (int i = elements.size() - 1; i > 0; i--)
        {
            Collections.swap(elements, 0, i);
            percolateDown(elements, 0, i);
        }
    }
}
