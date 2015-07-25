package todolist;
    
public class ItemData {
    private final Integer id;
    private String description;
    private boolean completed;
    private final Integer list_id;
    
    public ItemData(Integer id, String description, boolean completed, Integer list_id) {
        this.id = id;
        this.description = description;
        this.completed = completed;
        this.list_id = list_id;
    }

    public Integer getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean getCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public Integer getListId() {
        return list_id;
    }
}