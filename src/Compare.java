
public interface Compare<T>
{
    public boolean lessThan(T other);
    public boolean greaterThan(T other);
    public boolean equalsTo(T other);
}
