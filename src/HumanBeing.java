public class HumanBeing implements Comparable<HumanBeing> {
    private static long nextId = 1;

    private long id;
    private String name;

    public HumanBeing(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public HumanBeing(long id, String name) {
        this.id = id;
        if (id > nextId){
            nextId = id + 1;
        }
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(HumanBeing other) {
        return Long.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}