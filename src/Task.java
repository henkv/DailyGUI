public class Task extends RepeatingTime
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
        int nameIndex = name.indexOf(str);
        int descIndex = desc.indexOf(str);

        return nameIndex >= 0 || descIndex >= 0;
    }
}
