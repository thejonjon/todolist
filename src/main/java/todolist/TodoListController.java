package todolist;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class TodoListController {
    
    //auto inc. counters to assign list and item ids
    private final AtomicInteger list_counter = new AtomicInteger();
    private final AtomicInteger item_counter = new AtomicInteger();
    
    //List and item storage - id's are Integers
    //LISTS {id:{'name':string}, ...}
    private HashMap<Integer, ListData> LISTS = new HashMap<Integer, ListData>();
    //ITEMS {id:{'description':string,'completed':bool,'list_id':int}, ...}
    private HashMap<Integer, ItemData> ITEMS = new HashMap<Integer, ItemData>();
    
    //ASYNC sample data route
    @RequestMapping("/setup")
    public void setInitData(){
        //Clear the lists first
        LISTS.clear();
        ITEMS.clear();
        
        //Add in sample data
        LISTS.put(new Integer(list_counter.incrementAndGet()),
                        new ListData(new Integer(list_counter.get()),"vacation packing"));
        
        ITEMS.put(new Integer(item_counter.incrementAndGet()),
                        new ItemData(new Integer(item_counter.get()),
                                                "pants",false, new Integer(list_counter.get())));
        
        ITEMS.put(new Integer(item_counter.incrementAndGet()),
                        new ItemData(new Integer(item_counter.get()),
                                                "first aid kit",false,new Integer(list_counter.get())));
        
        LISTS.put(new Integer(list_counter.incrementAndGet()),
                        new ListData(new Integer(list_counter.get()),"Afternoon Activities"));
        
        ITEMS.put(new Integer(item_counter.incrementAndGet()),
                        new ItemData(new Integer(item_counter.get()),
                                                "clean eyes",false, new Integer(list_counter.get())));
    
        ITEMS.put(new Integer(item_counter.incrementAndGet()),
                        new ItemData(new Integer(item_counter.get()),
                                                "mow carpet",false, new Integer(list_counter.get())));
    }
    
    /* ASYNC List routes */
    
    //Fetch all lists
    @RequestMapping("/get_lists")
    public Object[] getLists(){
        //Returns JSONable array of lists
        return LISTS.values().toArray();
    }
    
    //Create list
    @RequestMapping(method=RequestMethod.POST, value="/create_list")
    public Integer createList(@RequestBody HashMap<String,String> new_list){
        LISTS.put(new Integer(list_counter.incrementAndGet()),
            new ListData(
                new Integer(list_counter.get()),new_list.get("name")));
        return new Integer(list_counter.get());
    }
    
    //Update List
    @RequestMapping(method=RequestMethod.POST, value="/update_list/{list_id}")
    public void updateList(@PathVariable Integer list_id, @RequestBody HashMap<String,String> list_data){
        //Returns nothing
        
        //Not sure how in java I could do a python like .update call on the dataset
        //so explicitly looking for the two fields that can be updated. I'd rather program
        //this more abstractly and be able to itterate over a list of acceptable fields and 
        //call their associated method for setting the values.
        if(list_data.containsKey("name")){
            LISTS.get(list_id).setName(list_data.get("name"));
        }
    }
    
    //Delete list
    @RequestMapping(method=RequestMethod.DELETE, value="/delete_list/{list_id}")
    public void deleteList(@PathVariable Integer list_id){
        //Remove any items on the list
        
        //to delete array, since deleting while itterating causes issues
        //There has got to be a better way to do this in java
        List to_del = new ArrayList();
        
        Set <Integer> keys = ITEMS.keySet();
        for (Integer key : keys){
            if (ITEMS.get(key).getListId().equals(list_id) ){
                to_del.add(key);
                //ITEMS.remove(key);
            }
        }
        
        //Actually erase the items
        for (Object key : to_del){
            ITEMS.remove(key);
        }
        
        //Erase the list
        LISTS.remove(list_id);
    }
    
    /* ASYNC item routes */
    
    //Fetch list items
    @RequestMapping("/list_items/{list_id}")
    public List getListItems(@PathVariable Integer list_id){
        //Returns variable ret - ITEMS that match the list_id
        
        //TODO - first check LISTS to make sure the key exists otherwise raise 404 error. Figure out how to raise 404 using spring.
        
        List ret = new ArrayList();
        
        //TODO - use reference array in ListData so wont have to itterate over all items
        
        //Find items and add to return
        Set <Integer> keys = ITEMS.keySet();
        for (Integer key : keys){
            if (ITEMS.get(key).getListId().equals(list_id) ){
                ret.add(ITEMS.get(key));
            }
        }
        
        return ret;
    }
    
    //Create Item
    @RequestMapping(method=RequestMethod.POST, value="/create_item/{list_id}")
    public Integer createItem(@PathVariable Integer list_id, @RequestBody HashMap<String,String> new_item){
        //Returns new item's id
        
        ITEMS.put(new Integer(item_counter.incrementAndGet()),
            new ItemData(new Integer(item_counter.get()),
                                    new_item.get("description"),false,list_id));
        
        return new Integer(item_counter.get());
    }
    
    //Update item
    @RequestMapping(method=RequestMethod.POST, value="/update_item/{item_id}")
    public void updateItem(@PathVariable Integer item_id, @RequestBody HashMap<String,String> item_data){
        //Returns nothing
        
        //Not sure how in java I could do a python like .update call on the dataset
        //so explicitly looking for the two fields that can be updated. I'd rather program
        //this more abstractly and be able to itterate over a list of acceptable fields and 
        //call their associated method for setting the values.
        if(item_data.containsKey("completed")){
            ITEMS.get(item_id).setCompleted(new Boolean(item_data.get("completed")));
        }
        
        if(item_data.containsKey("description")){
            ITEMS.get(item_id).setDescription(item_data.get("description"));
        }
    }
}