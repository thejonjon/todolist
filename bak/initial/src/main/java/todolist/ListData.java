package todolist;
    
public class ListData {
    private String name;
    private final Integer id;
    
    //Need to figure out how to make a reference arrary to ItemData[] for items
    //private List items = new ArrayList();
    public ListData(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /*
    public List getItems(){
        return this.items;
    }
    
    public void addItem(ItemData item){
        this.itmes.put(item);
    }
    public void removeItem(ItemData item){
        this.items.remove(item);
    }*/
}