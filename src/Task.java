import java.io.Serializable;

public class Task extends RepeatingTime implements Serializable
{
    private String name;
    private String desc;

    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean match(String str)
    {
        return (name.indexOf(str) >= 0) || (desc.indexOf(str) >= 0) ;
    }
}
